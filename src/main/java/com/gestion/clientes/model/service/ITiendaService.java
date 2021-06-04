package com.gestion.clientes.model.service;

import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Tienda;

import java.util.List;

public interface ITiendaService {


    Tienda findById(Long id) throws MasterResourceNotFoundException;

    Tienda create(Tienda tienda) throws Exception;

    Tienda edit(Long id, Tienda tienda) throws Exception;

    void delete(Long id) throws MasterResourceDeletedException;
}
