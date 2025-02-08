package com.arka.homework.projectArka.Proveedor.Domain.Entity;

import com.arka.homework.projectArka.Producto.Domain.Entity.Producto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @ManyToMany(mappedBy = "proveedores", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Producto> productos;

    public void addProducto(Producto producto) {
        productos.add(producto);
        producto.getProveedores().add(this);
    }

    public void removeProducto(Producto producto) {
        productos.remove(producto);
        producto.getProveedores().remove(this);
    }
}
