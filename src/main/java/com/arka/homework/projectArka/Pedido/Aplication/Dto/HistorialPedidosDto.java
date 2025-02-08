package com.arka.homework.projectArka.Pedido.Aplication.Dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
public class HistorialPedidosDto {
     Long clienteId;
     String clienteNombre;
     Long pedidoId;
     Date fechaPedido;
     String productoNombre;
     Integer cantidadProducto;

    public HistorialPedidosDto(Long clienteId, String clienteNombre, Long pedidoId, Date fechaPedido, String productoNombre, Integer cantidadProducto) {
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
        this.pedidoId = pedidoId;
        this.fechaPedido = fechaPedido;
        this.productoNombre = productoNombre;
        this.cantidadProducto = cantidadProducto;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }


    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
