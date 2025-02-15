package com.arka.homework.projectArka.Categoria.Controller;

import com.arka.homework.projectArka.Categoria.Aplication.Dto.CreateCategoriaDto;
import com.arka.homework.projectArka.Categoria.Aplication.Service.CategoriaService;
import com.arka.homework.projectArka.Categoria.Domain.Entity.Categoria;
import com.arka.homework.projectArka.Exception.GeneralException;
import com.arka.homework.projectArka.Producto.Aplication.Service.ProductoService;
import com.arka.homework.projectArka.Producto.Controller.ProductosByCategoriaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    ProductoService productoService;

    @GetMapping
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<List<Categoria>> findByName(@PathVariable String name) {
        try {
            List<Categoria> categorias = categoriaService.findByName(name);
            return ResponseEntity.ok(categorias);
        } catch (GeneralException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Obtiene todos los productos por el id de la categoria
     * @param id obtiene el id de la categoria
     * @return
     */
    @GetMapping("/{id}/productos")
    public ResponseEntity<Optional<ProductosByCategoriaResponse>> getProductosByCategoria(@PathVariable("id")Long id){
        return new ResponseEntity<>(productoService.getProductosByCategoria(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody CreateCategoriaDto categoryDto) {
        try {
            Categoria savedCategoria = categoriaService.save(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoria);
        } catch (GeneralException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody CreateCategoriaDto categoryDto) {
        try {
            Categoria updatedCategoria = categoriaService.update(id, categoryDto);
            return ResponseEntity.ok(updatedCategoria);
        } catch (GeneralException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
