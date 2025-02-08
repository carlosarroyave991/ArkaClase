package com.arka.homework.projectArka.Producto.Aplication.Service;


import com.arka.homework.projectArka.Categoria.Domain.Entity.Categoria;
import com.arka.homework.projectArka.Categoria.Domain.Repository.CategoriaRepository;
import com.arka.homework.projectArka.Exception.GeneralException;
import com.arka.homework.projectArka.Producto.Aplication.Dto.CreateProductoDto;
import com.arka.homework.projectArka.Producto.Domain.Entity.Producto;
import com.arka.homework.projectArka.Producto.Domain.Repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<Producto> getAll(){
        return productoRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<Producto> findById(Long id){
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            return producto;
        }else{
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public List<Producto> findByName(String name){
        List<Producto> producto = productoRepository.findByName(name);
        if (producto.isEmpty()){
            throw new GeneralException(PRODUCT_NO_ENCONTRADO);
        }else{
            return producto;
        }
    }

    public Producto save(CreateProductoDto productoDto){
        List<Producto> productoList = productoRepository.findByName(productoDto.getName());
        if (productoList.isEmpty()){
            // Verificar si la categoría existe
            List<Categoria> categorias;
            categorias = categoriaRepository.findByName(productoDto.getCategoria().getName());
            Categoria categoria;
            if (categorias.isEmpty()) {
                categoria = new Categoria();
                BeanUtils.copyProperties(productoDto.getCategoria(), categoria);
                categoria.setActiveSince(new Date());
                categoria = categoriaRepository.save(categoria);
            } else {
                categoria = categorias.get(0); // Asignar la primera categoría encontrada
            }
            // Crear el producto
            Producto producto = new Producto();
            BeanUtils.copyProperties(productoDto, producto);
            producto.setCategoria(categoria);
            return productoRepository.save(producto);
        }else{
            throw new GeneralException(PRODUCT_YA_EXISTE);
        }
    }

    public Producto update(Long id,CreateProductoDto productoDto){
        Optional<Producto> result = productoRepository.findById(id);
        if(result.isPresent()){
            Producto producto = new Producto();
            BeanUtils.copyProperties(productoDto,producto);
            return productoRepository.save(producto);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }


    public void delete(Long id){
        Optional<Producto> result = productoRepository.findById(id);
        if(result.isPresent()){
            productoRepository.deleteById(id);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public List<Producto> getProductosPorRangoDePrecio(Float minPrice, Float maxPrice){
        return productoRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
