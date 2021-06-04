package com.gestion.clientes.model.service.implement;

import com.gestion.clientes.exception.exceptions.ApiRequestException;

import com.gestion.clientes.exception.exceptions.*;
import com.gestion.clientes.model.entity.Administrador;
import com.gestion.clientes.model.repository.IAdministradorDao;
import com.gestion.clientes.model.service.IAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorService implements IAdministradorService {

    @Autowired
    private IAdministradorDao administradorDao;

    private Administrador administrador;



    // BUSCAR  POR ID
    @Override
    @Transactional(readOnly = true)
    public Administrador findById(Long id) throws MasterResourceNotFoundException {
        administrador = administradorDao.findById(id).orElse(null);
        if(administrador == null){
            throw new MasterResourceNotFoundException();
        }
        return administrador;
    }

    // SERVICIO DE CREAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Administrador create(Administrador administrador) throws Exception {


        Administrador crear = null;
        try{

            crear = administradorDao.save(administrador);
        }catch (javax.validation.ConstraintViolationException ce){
            List<String> errores = ce.getConstraintViolations()
                    .stream().map(err -> " El campo " + err.getPropertyPath() + " " + err.getMessage()).collect(Collectors.toList());
            throw new ApiRequestException(errores.toString(), ce.getCause());
        }catch (DataIntegrityViolationException di){
            String error = " La entidad cliente identificada con " + administrador.getCodigo()+ " ";
            throw new MasterResourceConstraintException(error);
        }
        return crear;
    }


    // SERVICIO DE EDITAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Administrador edit(Long id, Administrador administrador) throws Exception {

        this.administrador = administradorDao.findById(id).orElse(null);

        if(this.administrador == null){
            throw new MasterResourceNotFoundException();
        }
        try{
            this.administrador.setCodigo(administrador.getCodigo());
            this.administrador.setNombre(administrador.getNombre());



            return administradorDao.save(this.administrador);

        }catch (ConstraintViolationException ce){
            List<String> errores = ce.getConstraintViolations()
                    .stream().map(err -> "El campo " + err.getPropertyPath() + " " + err.getMessage()).
                            collect(Collectors.toList());
            throw new ApiRequestException(errores.toString(), ce.getCause());
        }catch (DataIntegrityViolationException di){
            String error = " La entidad Cliente identificada con " +  administrador.getCodigo() + " ";
            throw new MasterResourceConstraintException(error);
        }
    }

    // SERVICIO DE ELIMINAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void delete(Long id) throws MasterResourceDeletedException {
        try {
            administradorDao.deleteById(id);
        }catch (Exception e){
            throw new MasterResourceDeletedException(e.getLocalizedMessage());
        }
    }
}
