package com.arka.homework.projectArka.Carrito.Aplication.Dto;

import com.arka.homework.projectArka.CarritoProducto.Domain.Entity.CarritoProducto;
import com.arka.homework.projectArka.Cliente.Aplication.Dto.CreateClienteDto;
import com.arka.homework.projectArka.Cliente.Domain.Entity.Cliente;
import com.arka.homework.projectArka.Pedido.Domain.Entity.Pedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoDto {

    Long id;

    CreateClienteDto cliente;

    List<CarritoProducto> carritoProductos;

    Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(CreateClienteDto cliente) {
        this.cliente = cliente;
    }

    public List<CarritoProducto> getCarritoProductos() {
        return carritoProductos;
    }

    public void setCarritoProductos(List<CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
