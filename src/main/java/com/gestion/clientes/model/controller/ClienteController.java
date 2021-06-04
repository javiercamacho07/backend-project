package com.gestion.clientes.model.controller;

import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Cliente;
import com.gestion.clientes.model.entity.Tienda;
import com.gestion.clientes.model.repository.IClienteDao;
import com.gestion.clientes.model.repository.ITiendaDao;
import com.gestion.clientes.model.service.IClienteService;
import com.gestion.clientes.model.service.ITiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Clientes")
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @Autowired
    private IClienteService clienteService;

    // Buscar Por Id
    @GetMapping("/ver/{id}")
    public Cliente finByIdCliente (
            @PathVariable Long id) throws MasterResourceNotFoundException {
        return clienteService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    public Cliente crear(@RequestBody Cliente cliente) throws Exception {
        return clienteService.create(cliente);
    }

    // Editar Tienda
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/editar/{id}")
    public Cliente editar(@PathVariable Long id, @RequestBody Cliente cliente) throws Exception {
        return clienteService.edit(id, cliente);
    }

    // Eliminar Tienda
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable Long id) throws MasterResourceDeletedException {
        clienteService.delete(id);
    }

}
