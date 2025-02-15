package com.arka.homework.projectArka.Producto.Controller;

import com.arka.homework.projectArka.Categoria.Controller.CategoriaWithProductosResponse;
import com.arka.homework.projectArka.Categoria.Controller.OnlyCategoriaResponse;

import java.util.Date;
import java.util.List;

public class ProductosByCategoriaResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Date activeSince;
    private List<OnlyProductoResponse> productosResponseList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OnlyProductoResponse> getProductosResponseList() {
        return productosResponseList;
    }

    public void setProductosResponseList(List<OnlyProductoResponse> productosResponseList) {
        this.productosResponseList = productosResponseList;
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
