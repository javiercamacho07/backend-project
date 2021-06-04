package com.gestion.clientes.model.service.implement;

import com.gestion.clientes.exception.exceptions.ApiRequestException;
import com.gestion.clientes.exception.exceptions.MasterResourceConstraintException;
import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Producto;
import com.gestion.clientes.model.repository.IProductoDao;
import com.gestion.clientes.model.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {


    @Autowired
    private IProductoDao productoDao;

    private Producto producto;

    // BUSCAR  POR ID
    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) throws MasterResourceNotFoundException {
        producto = productoDao.findById(id).orElse(null);
        if(producto == null){
            throw new MasterResourceNotFoundException();
        }
        return producto;
    }

    // SERVICIO DE CREAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Producto create(Producto producto) throws Exception {


        Producto crear = null;
        try{

            crear = productoDao.save(producto);
        }catch (ConstraintViolationException ce){
            List<String> errores = ce.getConstraintViolations()
                    .stream().map(err -> " El campo " + err.getPropertyPath() + " " + err.getMessage()).collect(Collectors.toList());
            throw new ApiRequestException(errores.toString(), ce.getCause());
        }catch (DataIntegrityViolationException di){
            String error = " La entidad Producto identificada con " + producto.getId() + " ";
            throw new MasterResourceConstraintException(error);
        }
        return crear;
    }


    // SERVICIO DE EDITAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Producto edit(Long id, Producto producto) throws Exception {

        this.producto = productoDao.findById(id).orElse(null);

        if(this.producto == null){
            throw new MasterResourceNotFoundException();
        }
        try{
            this.producto.setNombre(producto.getNombre());
            this.producto.setDescripcion(producto.getDescripcion());
            this.producto.setPrecio(producto.getPrecio());


            return productoDao.save(this.producto);

        }catch (ConstraintViolationException ce){
            List<String> errores = ce.getConstraintViolations()
                    .stream().map(err -> "El campo " + err.getPropertyPath() + " " + err.getMessage()).
                            collect(Collectors.toList());
            throw new ApiRequestException(errores.toString(), ce.getCause());
        }catch (DataIntegrityViolationException di){
            String error = " La entidad Producto identificada con " + producto.getId() + " ";
            throw new MasterResourceConstraintException(error);
        }
    }

    // SERVICIO DE ELIMINAR
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void delete(Long id) throws MasterResourceDeletedException {
        try {
            productoDao.deleteById(id);
        }catch (Exception e){
            throw new MasterResourceDeletedException(e.getLocalizedMessage());
        }
    }


}
