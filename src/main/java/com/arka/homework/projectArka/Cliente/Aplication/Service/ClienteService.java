package com.arka.homework.projectArka.Cliente.Aplication.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.arka.homework.projectArka.Cliente.Aplication.Dto.CreateClienteDto;
import com.arka.homework.projectArka.Cliente.Domain.Entity.Cliente;
import com.arka.homework.projectArka.Cliente.Domain.Repository.ClienteRepository;
import com.arka.homework.projectArka.Exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ClienteService {
    private final static String NAME_YA_EXISTE = "El nickname ya existe en la base de datos";
    private final static String NAME_NO_ENCONTRADO = "El nickname no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAll(){
        return clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<Cliente> findById(Long id){
        Optional<Cliente> client = clienteRepository.findById(id);
        if(client.isPresent()){
            return client;
        }else{
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public List<Cliente> findByName(String name){
        List<Cliente> client = clienteRepository.findByName(name);
        if(client.isEmpty()){
            throw new GeneralException(NAME_NO_ENCONTRADO);
        }else{
            return client;
        }
    }

    public Cliente save(CreateClienteDto clienteDto){
        List<Cliente> clienteList = clienteRepository.findByDni(clienteDto.getDni());
        if(clienteList.isEmpty()){
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, cliente);
            return clienteRepository.save(cliente);
        }else {
            throw new GeneralException(ID_YA_EXISTE);
        }
    }

    public Cliente update(Long id, CreateClienteDto clienteDto){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteDto.getId());
        if(clienteOptional.isPresent()){
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, cliente);
            return clienteRepository.save(cliente);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public void delete(Long id){
        Optional<Cliente> result = clienteRepository.findById(id);
        if(result.isPresent()){
            clienteRepository.deleteById(id);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }
}
