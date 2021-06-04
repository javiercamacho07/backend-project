package com.gestion.clientes.model.repository;

import com.gestion.clientes.model.entity.Tienda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITiendaDao extends CrudRepository<Tienda,Long> {


}
