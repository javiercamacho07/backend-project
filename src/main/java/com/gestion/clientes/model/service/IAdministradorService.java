package com.gestion.clientes.model.service;

import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Administrador;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAdministradorService {




    // BUSCAR  POR ID
    @Transactional(readOnly = true)
    Administrador findById(Long id) throws MasterResourceNotFoundException;

    // SERVICIO DE CREAR
    @Transactional(propagation = Propagation.NEVER)
    Administrador create(Administrador administrador) throws Exception;

    // SERVICIO DE EDITAR
    @Transactional(propagation = Propagation.NEVER)
    Administrador edit(Long id, Administrador administrador) throws Exception;

    // SERVICIO DE ELIMINAR
    @Transactional(propagation = Propagation.NEVER)
    void delete(Long id) throws MasterResourceDeletedException, MasterResourceDeletedException;
}
