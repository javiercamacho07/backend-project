package com.gestion.clientes.model.service;

import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Producto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductoService {


    // BUSCAR  POR ID
    @Transactional(readOnly = true)
    Producto findById(Long id) throws MasterResourceNotFoundException;

    // SERVICIO DE CREAR
    @Transactional(propagation = Propagation.NEVER)
    Producto create(Producto producto) throws Exception;

    // SERVICIO DE EDITAR
    @Transactional(propagation = Propagation.NEVER)
    Producto edit(Long id, Producto producto) throws Exception;

    // SERVICIO DE ELIMINAR
    @Transactional(propagation = Propagation.NEVER)
    void delete(Long id) throws MasterResourceDeletedException;
}
