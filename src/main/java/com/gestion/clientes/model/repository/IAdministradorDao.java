package com.gestion.clientes.model.repository;

import com.gestion.clientes.model.entity.Administrador;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdministradorDao  extends CrudRepository<Administrador, Long> {


}
