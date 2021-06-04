package com.gestion.clientes.model.service.implement;

import com.gestion.clientes.exception.exceptions.ApiRequestException;
import com.gestion.clientes.exception.exceptions.MasterResourceConstraintException;
import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Proveedor;
import com.gestion.clientes.model.repository.IProveedorDao;
import com.gestion.clientes.model.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService implements IProveedorService {

    @Autowired
    private IProveedorDao proveedorDao;

    private Proveedor proveedor;



    // BUSCAR  POR ID
    @Override
    @Transactional(readOnly = true)
    public Proveedor findById(Long id) throws MasterResourceNotFoundException {
        proveedor = proveedorDao.findById(id).orElse(null);
        if(proveedor == null){
            throw new MasterResourceNotFoundException();
        }
        return proveedor;
    }

    // SERVICIO DE CREAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Proveedor create(Proveedor proveedor) throws Exception {


        Proveedor crear = null;
        try{

            crear = proveedorDao.save(proveedor);
        }catch (ConstraintViolationException ce){
            List<String> errores = ce.getConstraintViolations()
                    .stream().map(err -> " El campo " + err.getPropertyPath() + " " + err.getMessage()).collect(Collectors.toList());
            throw new ApiRequestException(errores.toString(), ce.getCause());
        }catch (DataIntegrityViolationException di){
            String error = " La entidad Proveedor identificada con " + proveedor.getId() + " ";
            throw new MasterResourceConstraintException(error);
        }
        return crear;
    }


    // SERVICIO DE EDITAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Proveedor edit(Long id, Proveedor proveedor) throws Exception {

        this.proveedor = proveedorDao.findById(id).orElse(null);

        if(this.proveedor == null){
            throw new MasterResourceNotFoundException();
        }
        try{
            this.proveedor.setContacto(proveedor.getContacto());
            this.proveedor.setNombre(proveedor.getNombre());
            this.proveedor.setNombretienda(proveedor.getNombretienda());
            this.proveedor.setProductos(proveedor.getProductos());



            return proveedorDao.save(this.proveedor);

        }catch (ConstraintViolationException ce){
            List<String> errores = ce.getConstraintViolations()
                    .stream().map(err -> "El campo " + err.getPropertyPath() + " " + err.getMessage()).
                            collect(Collectors.toList());
            throw new ApiRequestException(errores.toString(), ce.getCause());
        }catch (DataIntegrityViolationException di){
            String error = " La entidad Proveedor identificada con " + proveedor.getId() + " ";
            throw new MasterResourceConstraintException(error);
        }
    }

    // SERVICIO DE ELIMINAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void delete(Long id) throws MasterResourceDeletedException {
        try {
            proveedorDao.deleteById(id);
        }catch (Exception e){
            throw new MasterResourceDeletedException(e.getLocalizedMessage());
        }
    }
}
