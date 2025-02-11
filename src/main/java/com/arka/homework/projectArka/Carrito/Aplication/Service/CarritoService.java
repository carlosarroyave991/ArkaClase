package com.arka.homework.projectArka.Carrito.Aplication.Service;

import com.arka.homework.projectArka.Carrito.Aplication.Dto.CreateCarritoDto;
import com.arka.homework.projectArka.Carrito.Domain.Entity.Carrito;
import com.arka.homework.projectArka.Carrito.Domain.Repository.CarritoRepository;
import com.arka.homework.projectArka.CarritoProducto.Domain.Entity.CarritoProducto;
import com.arka.homework.projectArka.CarritoProducto.Domain.Repository.CarritoProductoRepository;
import com.arka.homework.projectArka.Categoria.Domain.Entity.Categoria;
import com.arka.homework.projectArka.Cliente.Domain.Entity.Cliente;
import com.arka.homework.projectArka.Cliente.Domain.Repository.ClienteRepository;
import com.arka.homework.projectArka.Exception.GeneralException;
import com.arka.homework.projectArka.Pedido.Domain.Entity.Pedido;
import com.arka.homework.projectArka.Pedido.Domain.Repository.PedidoRepository;
import com.arka.homework.projectArka.Producto.Domain.Entity.Producto;
import com.arka.homework.projectArka.Producto.Domain.Repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CarritoService {
    private final static String CARRITO_YA_EXISTE = "El carrito ya existe en la base de datos";
    private final static String CARRITO_NO_ENCONTRADO = "El carrito no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    CarritoProductoRepository carritoProductoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public List<Carrito> getAll(){
        return carritoRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<Carrito> findById(Long id){
        Optional<Carrito> producto = carritoRepository.findById(id);
        if(producto.isPresent()){
            return producto;
        }else{
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }



    /**
     * Funcion que llenara el carrito de compras
     * @return retorna la entidad carritoProductos con los datos necesarios
     * @throws GeneralException Indica que el id no ha sido encontrado
     */
    public CarritoProducto agregarProductoAlCarrito(Long carritoId,Long productoId, Integer cantidad){
        Optional<Carrito> carritoOptional = carritoRepository.findById(carritoId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        if (carritoOptional.isEmpty()) {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }

        if (productoOptional.isEmpty()) {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }

        Carrito carrito = carritoOptional.get();
        Producto producto = productoOptional.get();

        //Verifica si el producto ya esta en el carrito
        Optional<CarritoProducto> carritoProductoOptional = carritoRepository.findByCarritoAndProducto(carrito, producto);
        CarritoProducto carritoProducto;
        if (carritoProductoOptional.isPresent()) {
            // Si el producto ya está en el carrito, actualizar la cantidad
            carritoProducto = carritoProductoOptional.get();
            carritoProducto.setAmount(carritoProducto.getAmount() + cantidad);
        } else {
            // Si el producto no está en el carrito, crear una nueva relación
            carritoProducto = new CarritoProducto();
            carritoProducto.setProducto(producto);
            carritoProducto.setCarrito(carrito);
            carritoProducto.setAmount(cantidad);
            carritoProducto.setCreatedDate(new Date());
        }
        return carritoProductoRepository.save(carritoProducto);
    }

    /**
     * Funcion que lista los productos del carrito
     * @param carritoId tiene el id del carrito a listarle los productos
     * @throws GeneralException trae un mensaje de advertencia
     * */
    public List<CarritoProducto> listarProductos(Long carritoId) {
        Optional<Carrito> carritoOptional = carritoRepository.findById(carritoId);
        if (carritoOptional.isEmpty()) {
            throw new GeneralException("ID de carrito no encontrado");
        }
        /*return carritoOptional.get().getCarritoProductos();*/
        return carritoRepository.listarProductos(carritoId);
    }


    public Carrito save(CreateCarritoDto carritoDto){
        //1) buscar cliente y lo toma
        Optional<Cliente> clienteOptional = clienteRepository.findById(carritoDto.getCliente().getId());
        if(clienteOptional.isEmpty()){
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
        Cliente cliente = clienteOptional.get();

        //Crea el objeto carrito y copiar las propiedades
        Carrito carrito = new Carrito();
        BeanUtils.copyProperties(carritoDto, carrito);
        carrito.setCliente(cliente);

        //Crea y agrega los productos al carrito
        List<CarritoProducto> carritoProductosList = carritoDto.getCarritoProductos().stream()
                .map(dto -> {
                    CarritoProducto carritoProducto = new CarritoProducto();
                    carritoProducto.setProducto(dto.getProducto());
                    carritoProducto.setAmount(dto.getAmount());
                    carritoProducto.setCarrito(carrito);
                    return carritoProducto;
                })
                .collect(Collectors.toList());

        carrito.setCarritoProductos(carritoProductosList);

        // Guardar el carrito y los productos
        Carrito savedCarrito = carritoRepository.save(carrito);

        // Guardar los productos del carrito
        carritoProductosList.forEach(carritoProductoRepository::save);

        return savedCarrito;

    }

    public Carrito update(Long id,CreateCarritoDto carritoDto){
        Optional<Carrito> carritoOptional = carritoRepository.findById(id);
        if(carritoOptional.isPresent()){
            Carrito carrito = new Carrito();
            BeanUtils.copyProperties(carritoDto, carrito);
            return carritoRepository.save(carrito);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }


    public void delete(Long id){
        Optional<Carrito> carritoOptional = carritoRepository.findById(id);
        if(carritoOptional.isPresent()){
            carritoRepository.deleteById(id);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }
}
