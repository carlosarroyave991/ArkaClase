package com.arka.homework.projectArka.Producto.Controller;

import com.arka.homework.projectArka.CarritoProducto.Domain.Entity.CarritoProducto;
import com.arka.homework.projectArka.Categoria.Controller.OnlyCategoriaResponse;
import com.arka.homework.projectArka.Proveedor.Domain.Entity.Proveedor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {
    private Long id;
    private String name;
    private String stamp;
    private BigDecimal price;
    private Integer stock;
    private List<CarritoProducto> carritoProductoList;
    private OnlyCategoriaResponse categoria;
    private List<Proveedor> proveedores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public OnlyCategoriaResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(OnlyCategoriaResponse categoria) {
        this.categoria = categoria;
    }

    public List<CarritoProducto> getCarritoProductoList() {
        return carritoProductoList;
    }

    public void setCarritoProductoList(List<CarritoProducto> carritoProductoList) {
        this.carritoProductoList = carritoProductoList;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
