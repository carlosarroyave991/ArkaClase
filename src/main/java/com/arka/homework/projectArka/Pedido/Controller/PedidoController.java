package com.arka.homework.projectArka.Pedido.Controller;

import com.arka.homework.projectArka.Cliente.Aplication.Dto.CreateClienteDto;
import com.arka.homework.projectArka.Cliente.Domain.Entity.Cliente;
import com.arka.homework.projectArka.Pedido.Aplication.Dto.CreatePedidoDto;
import com.arka.homework.projectArka.Pedido.Aplication.Dto.HistorialPedidosDto;
import com.arka.homework.projectArka.Pedido.Aplication.Service.PedidoService;
import com.arka.homework.projectArka.Pedido.Domain.Entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping()
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return new ResponseEntity<>(pedidoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/buscar/{reference}")
    public ResponseEntity<Optional<Pedido>> findByReference(@PathVariable("reference")Long reference){
        return new ResponseEntity<>(pedidoService.findByReference(reference), HttpStatus.OK);
    }

    @GetMapping("/{clienteId}/historial-pedidos")
    public List<HistorialPedidosDto> obtenerHistorialPedidos(@PathVariable("clienteId") Long clienteId) {
        return pedidoService.obtenerHistorialPedidos(clienteId);
    }

    /**
     * Obtener carritos abandonados
     */
    @GetMapping("/historial-pedidos")
    public ResponseEntity<List<Pedido>> obtenerPedidosAbandonados(){
        return new ResponseEntity<>(pedidoService.findByEstadoPedido(), HttpStatus.OK);
    }

    /**
     * Obtener pedidos en un rango de fechas
     * ISO recibe este tipo de formato de fecha YYYY-MM-DD
     */
    @GetMapping("/rango-fechas")
    public List<Pedido> obtenerPedidosEnRangoDeFechas(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return pedidoService.findByDateBetween(startDate, endDate);
    }

    @PostMapping()
    public ResponseEntity<Pedido> save(@RequestBody CreatePedidoDto pedidoDto){
        return new ResponseEntity<>(pedidoService.save(pedidoDto), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        pedidoService.deleteById(id);
        return new ResponseEntity<>("Se eliminó correctamente", HttpStatus.OK);
    }
}
