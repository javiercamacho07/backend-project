package com.gestion.clientes.model.controller;

import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Proveedor;
import com.gestion.clientes.model.repository.IProveedorDao;
import com.gestion.clientes.model.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Proveedor")
public class ProveedorController {

    @Autowired
    private IProveedorDao proveedorDao;

    @Autowired
    private IProveedorService proveedorService;

    // Buscar Por Id
    @GetMapping("/ver/{id}")
    public Proveedor finByIdProveedor (
            @PathVariable Long id) throws MasterResourceNotFoundException {
        return proveedorService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    public Proveedor crear(@RequestBody Proveedor proveedor) throws Exception {
        return proveedorService.create(proveedor);
    }

    // Editar Producto
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/editar/{id}")
    public Proveedor editar(@PathVariable Long id, @RequestBody Proveedor proveedor) throws Exception {
        return proveedorService.edit(id, proveedor);
    }

    // Eliminar Producto
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable Long id) throws MasterResourceDeletedException {
        proveedorService.delete(id);
    }


}
