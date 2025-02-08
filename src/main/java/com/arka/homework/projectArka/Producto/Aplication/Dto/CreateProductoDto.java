package com.arka.homework.projectArka.Producto.Aplication.Dto;

import com.arka.homework.projectArka.Categoria.Aplication.Dto.CreateCategoriaDto;
import com.arka.homework.projectArka.Categoria.Domain.Entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoDto {

    String name;

    String stamp;

    BigDecimal price;

    Integer stock;

    CreateCategoriaDto categoria;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public CreateCategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CreateCategoriaDto categoria) {
        this.categoria = categoria;
    }
}