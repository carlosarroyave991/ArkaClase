package com.arka.homework.projectArka.Pedido.Domain.Repository;

import com.arka.homework.projectArka.Pedido.Aplication.Dto.CreatePedidoDto;
import com.arka.homework.projectArka.Pedido.Aplication.Dto.HistorialPedidosDto;
import com.arka.homework.projectArka.Pedido.Domain.Entity.Enums.EstadoPedido;
import com.arka.homework.projectArka.Pedido.Domain.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByReference(Long reference);

    @Query("SELECT new com.arka.homework.projectArka.Pedido.Aplication.Dto.HistorialPedidosDto(c.id, c.name, p.id, p.date, pr.name, cp.amount) " +
            "FROM Cliente c " +
            "JOIN c.carrito ca " +
            "JOIN ca.carritoProductos cp " +
            "JOIN cp.producto pr " +
            "JOIN ca.pedido p " +
            "WHERE c.id = :clienteId")
    List<HistorialPedidosDto> findHistorialPedidosByClienteId(@Param("clienteId") Long clienteId);

    List<Pedido> findByEstadoPedido(EstadoPedido estadoPedido);

    List<Pedido> findByDateBetween(Date startDate, Date endDate);

}
