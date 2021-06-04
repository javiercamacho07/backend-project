package com.gestion.clientes.model.service;

import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Cliente;


import java.util.List;

public interface IClienteService {


    Cliente findById(Long id) throws MasterResourceNotFoundException;

    Cliente create(Cliente cliente) throws Exception;

    Cliente edit(Long id, Cliente cliente) throws Exception;

    void delete(Long id) throws MasterResourceDeletedException;
}
