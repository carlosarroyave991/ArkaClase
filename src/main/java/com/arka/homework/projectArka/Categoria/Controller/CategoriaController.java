package com.arka.homework.projectArka.Categoria.Controller;

import com.arka.homework.projectArka.Categoria.Aplication.Dto.CreateCategoriaDto;
import com.arka.homework.projectArka.Categoria.Aplication.Service.CategoriaService;
import com.arka.homework.projectArka.Categoria.Domain.Entity.Categoria;
import com.arka.homework.projectArka.Exception.GeneralException;
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
