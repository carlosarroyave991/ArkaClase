package com.arka.homework.projectArka.Carrito.Controller;

import com.arka.homework.projectArka.Carrito.Aplication.Dto.CreateCarritoDto;
import com.arka.homework.projectArka.Carrito.Aplication.Service.CarritoService;
import com.arka.homework.projectArka.Carrito.Domain.Entity.Carrito;
import com.arka.homework.projectArka.CarritoProducto.Domain.Entity.CarritoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/carrito")
public class CarritoController {

    @Autowired
    CarritoService carritoService;


    @GetMapping()
    public ResponseEntity<List<Carrito>> getAllCarrito() {
        return new ResponseEntity<>(carritoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Carrito>> findById(@PathVariable("id")Long id){
        return new ResponseEntity<>(carritoService.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carrito> save(
            @RequestBody CreateCarritoDto carritoDto
    ){
        return new ResponseEntity<>(carritoService.save(carritoDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Carrito> update(
            @PathVariable("id")Long id,
            @RequestBody CreateCarritoDto carritoDto
    ){
        return new ResponseEntity<>(carritoService.update(id, carritoDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        carritoService.delete(id);
        return new ResponseEntity<>("Se eliminó correctamente", HttpStatus.OK);
    }

    //Agrego las consultas personalizadas
    @PostMapping("/{carritoId}/productos")
    public ResponseEntity<CarritoProducto> agregarProductoAlCarrito(
            @PathVariable Long carritoId,
            @RequestBody Long productoId,
            @RequestBody Integer cantidad) {
        CarritoProducto carritoProducto = carritoService.agregarProductoAlCarrito(carritoId, productoId, cantidad);
        return new ResponseEntity<>(carritoProducto, HttpStatus.CREATED);
    }

    @GetMapping("/{carritoId}/productos")
    public ResponseEntity<List<CarritoProducto>> listarProductos(@PathVariable Long carritoId) {
        return new ResponseEntity<>(carritoService.listarProductos(carritoId), HttpStatus.OK);
    }
}
