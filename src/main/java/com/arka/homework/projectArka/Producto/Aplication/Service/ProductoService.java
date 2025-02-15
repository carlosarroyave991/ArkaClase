package com.arka.homework.projectArka.Producto.Aplication.Service;


import com.arka.homework.projectArka.Categoria.Aplication.Dto.CreateCategoriaDto;
import com.arka.homework.projectArka.Categoria.Controller.CategoriaWithProductosResponse;
import com.arka.homework.projectArka.Categoria.Controller.OnlyCategoriaResponse;
import com.arka.homework.projectArka.Categoria.Domain.Entity.Categoria;
import com.arka.homework.projectArka.Categoria.Domain.Repository.CategoriaRepository;
import com.arka.homework.projectArka.Exception.GeneralException;
import com.arka.homework.projectArka.Producto.Aplication.Dto.CreateProductoDto;
import com.arka.homework.projectArka.Producto.Controller.OnlyProductoResponse;
import com.arka.homework.projectArka.Producto.Controller.ProductoResponse;
import com.arka.homework.projectArka.Producto.Controller.ProductosByCategoriaResponse;
import com.arka.homework.projectArka.Producto.Domain.Entity.Producto;
import com.arka.homework.projectArka.Producto.Domain.Repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductoService {
    private final static String PRODUCT_YA_EXISTE = "El producto ya existe en la base de datos";
    private final static String PRODUCT_NO_ENCONTRADO = "El producto no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<ProductoResponse> getAll() {
        //return productoRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(producto -> {
            //Convertir productos en ProductoResponse
            ProductoResponse productoResponse = new ProductoResponse();
            BeanUtils.copyProperties(producto, productoResponse);

            //Convertir categorias en CategoriaResponse
            OnlyCategoriaResponse categoriaResponse = new OnlyCategoriaResponse();
            BeanUtils.copyProperties(producto.getCategoria(), categoriaResponse);
            productoResponse.setCategoria(categoriaResponse);

            return productoResponse;
        }).collect(Collectors.toList());
    }

    public Optional<Producto> findById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return producto;
        } else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public List<Producto> findByName(String name) {
        List<Producto> producto = productoRepository.findByName(name);
        if (producto.isEmpty()) {
            throw new GeneralException(PRODUCT_NO_ENCONTRADO);
        } else {
            return producto;
        }
    }

    /**
     * Servicio que me consultara todos los productos que pertenezcan a una misma categoria
     * @param id de la categoria
     * @return El objeto de respuesta ordenado con la categoria y los productos
     */
    public Optional<ProductosByCategoriaResponse> getProductosByCategoria(Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isEmpty()){
            throw new GeneralException(ID_NO_ENCONTRADO);
        }

        Categoria categoria = categoriaOptional.get();
        List<Producto> productos = productoRepository.findByCategoria(categoria);

        ProductosByCategoriaResponse response = new ProductosByCategoriaResponse();
        response.setId(categoria.getId());
        response.setName(categoria.getName());
        response.setDescription(categoria.getDescription());
        response.setImage(categoria.getImage());
        response.setActiveSince(categoria.getActiveSince());


        List<OnlyProductoResponse> productoResponses = productos.stream().map(producto -> {
            OnlyProductoResponse productoResponse = new OnlyProductoResponse();
            productoResponse.setId(producto.getId());
            productoResponse.setName(producto.getName());
            productoResponse.setStamp(producto.getStamp());
            productoResponse.setPrice(producto.getPrice());
            productoResponse.setStock(producto.getStock());
            return productoResponse;
        }).collect(Collectors.toList());

        response.setProductosResponseList(productoResponses);

        return Optional.of(response);
    }

    public ProductoResponse save(CreateProductoDto productoDto) {
        List<Producto> productoList = productoRepository.findByName(productoDto.getName());
        if (!productoList.isEmpty()) {
            throw new GeneralException(PRODUCT_YA_EXISTE);
        }

        Categoria categorias = getOrCreateCategoria(productoDto.getCategoria());

        Producto producto = new Producto();
        BeanUtils.copyProperties(productoDto, producto);
        producto.setCategoria(categorias); // Asignar categorías
        Producto savedProducto = productoRepository.save(producto);

        return convertToProductoDto(savedProducto);
    }

    // Método para convertir Producto a ProductoResponse
    private ProductoResponse convertToProductoDto(Producto producto) {
        ProductoResponse productoResponse = new ProductoResponse();
        BeanUtils.copyProperties(producto, productoResponse);

        OnlyCategoriaResponse onlyCategoriaResponse = new OnlyCategoriaResponse();
        BeanUtils.copyProperties(producto.getCategoria(), onlyCategoriaResponse);
        productoResponse.setCategoria(onlyCategoriaResponse);

        return productoResponse;
    }

        // Método para obtener o crear categorías
        private Categoria getOrCreateCategoria (CreateCategoriaDto categoriaDto){
            List<Categoria> categoriaList = categoriaRepository.findByName(categoriaDto.getName());
            if (categoriaList.isEmpty()) {
                Categoria newCategoria = new Categoria();
                BeanUtils.copyProperties(categoriaDto, newCategoria);
                newCategoria.setActiveSince(new Date());
                return categoriaRepository.save(newCategoria);
            } else {
                return categoriaList.get(0);
            }
        }


        public Producto update (Long id, CreateProductoDto productoDto){
            Optional<Producto> result = productoRepository.findById(id);
            if (result.isPresent()) {
                Producto producto = new Producto();
                BeanUtils.copyProperties(productoDto, producto);
                return productoRepository.save(producto);
            } else {
                throw new GeneralException(ID_NO_ENCONTRADO);
            }
        }


        public void delete (Long id){
            Optional<Producto> result = productoRepository.findById(id);
            if (result.isPresent()) {
                productoRepository.deleteById(id);
            } else {
                throw new GeneralException(ID_NO_ENCONTRADO);
            }
        }

        public List<Producto> getProductosPorRangoDePrecio (Float minPrice, Float maxPrice){
            return productoRepository.findByPriceBetween(minPrice, maxPrice);
        }
    }
