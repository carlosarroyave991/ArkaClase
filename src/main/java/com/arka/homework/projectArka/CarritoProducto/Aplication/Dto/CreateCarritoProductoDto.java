package com.arka.homework.projectArka.CarritoProducto.Aplication.Dto;

import com.arka.homework.projectArka.Carrito.Aplication.Dto.CreateCarritoDto;
import com.arka.homework.projectArka.Carrito.Domain.Entity.Carrito;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoProductoDto {

    Date createdDate;

    Integer amount;

    CreateCarritoDto carrito;

    CreateCarritoProductoDto producto;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public CreateCarritoDto getCarrito() {
        return carrito;
    }

    public void setCarrito(CreateCarritoDto carrito) {
        this.carrito = carrito;
    }

    public CreateCarritoProductoDto getProducto() {
        return producto;
    }

    public void setProducto(CreateCarritoProductoDto producto) {
        this.producto = producto;
    }
}
