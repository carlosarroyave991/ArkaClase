package com.arka.homework.projectArka.Categoria.Controller;

import com.arka.homework.projectArka.Producto.Controller.OnlyProductoResponse;
import com.arka.homework.projectArka.Producto.Controller.ProductoResponse;
import com.arka.homework.projectArka.Producto.Domain.Entity.Producto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CategoriaWithProductosResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Date activeSince;
    private List<OnlyProductoResponse> productos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OnlyProductoResponse> getProductos() {
        return productos;
    }

    public void setProductos(List<OnlyProductoResponse> productos) {
        this.productos = productos;
    }

    public Date getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Date activeSince) {
        this.activeSince = activeSince;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
