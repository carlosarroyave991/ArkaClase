package com.arka.homework.projectArka.Carrito.Domain.Repository;

import com.arka.homework.projectArka.Carrito.Domain.Entity.Carrito;
import com.arka.homework.projectArka.CarritoProducto.Domain.Entity.CarritoProducto;
import com.arka.homework.projectArka.Producto.Domain.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    @Query("SELECT cp FROM CarritoProducto cp WHERE cp.carrito = :carrito AND cp.producto = :producto")
    Optional<CarritoProducto> findByCarritoAndProducto(@Param("carrito") Carrito carrito, @Param("producto") Producto producto);

    /*CarritoProducto agregarProductoAlCarrito(Long carritoId,Long productoId);*/

    @Query("SELECT cp FROM CarritoProducto cp WHERE cp.carrito.id = :carritoId")
    List<CarritoProducto> listarProductos(@Param("carritoId") Long carritoId);

    /*List<CarritoProducto> listarProductos(Long carritoId);*/
}
