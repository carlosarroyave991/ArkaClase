package com.arka.homework.projectArka.CarritoProducto.Domain.Repository;

import com.arka.homework.projectArka.CarritoProducto.Domain.Entity.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {

}
