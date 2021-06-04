package com.gestion.clientes.model.controller;

import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Producto;
import com.gestion.clientes.model.repository.IProductoDao;
import com.gestion.clientes.model.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Producto")
public class ProductoController {

    @Autowired
    private IProductoDao productoDao;

    @Autowired
    private IProductoService productoService;

    // Buscar Por Id
    @GetMapping("/ver/{id}")
    public Producto finByIdProducto (
            @PathVariable Long id) throws MasterResourceNotFoundException {
        return productoService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    public Producto crear(@RequestBody Producto producto) throws Exception {
        return productoService.create(producto);
    }

    // Editar Tienda
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/editar/{id}")
    public Producto editar(@PathVariable Long id, @RequestBody Producto producto) throws Exception {
        return productoService.edit(id, producto);
    }

    // Eliminar Tienda
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable Long id) throws MasterResourceDeletedException {
        productoService.delete(id);
    }

}
