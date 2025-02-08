package com.arka.homework.projectArka.Cliente.Infraestructure.Controller;

import com.arka.homework.projectArka.Cliente.Aplication.Dto.CreateClienteDto;
import com.arka.homework.projectArka.Cliente.Aplication.Service.ClienteService;
import com.arka.homework.projectArka.Cliente.Domain.Entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> findById(@PathVariable("id")Long id){
        return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> findByName(@RequestParam()String name){
        return new ResponseEntity<>(clienteService.findByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Cliente> save(@RequestBody CreateClienteDto clienteDto){
        return new ResponseEntity<>(clienteService.save(clienteDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(
            @PathVariable("id")Long id,
            @RequestBody CreateClienteDto clienteDto
    ){
        return new ResponseEntity<>(clienteService.update(id, clienteDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        clienteService.delete(id);
        return new ResponseEntity<>("Se eliminó correctamente", HttpStatus.OK);
    }
}
