package com.arka.homework.projectArka.Categoria.Domain.Repository;

import com.arka.homework.projectArka.Categoria.Domain.Entity.Categoria;
import com.arka.homework.projectArka.Cliente.Domain.Entity.Cliente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByName(String name);

}