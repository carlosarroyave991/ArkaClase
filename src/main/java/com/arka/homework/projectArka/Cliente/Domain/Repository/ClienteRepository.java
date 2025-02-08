package com.arka.homework.projectArka.Cliente.Domain.Repository;

import com.arka.homework.projectArka.Cliente.Domain.Entity.Cliente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByName(String name); //Opcionalmente me envie un cliente por el name

    List<Cliente> findByDni(String dni);

    List<Cliente> findAll(Sort sort);


}