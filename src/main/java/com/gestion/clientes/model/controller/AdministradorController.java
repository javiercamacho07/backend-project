package com.gestion.clientes.model.controller;

import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Administrador;

import com.gestion.clientes.model.repository.IAdministradorDao;

import com.gestion.clientes.model.service.IAdministradorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Administrador")
public class AdministradorController {

    @Autowired
    private IAdministradorDao administradorDao;

    @Autowired
    private IAdministradorService administradorService;

    // Buscar Por Id
    @GetMapping("/ver/{id}")
    public Administrador finByIdAdministrador (
            @PathVariable Long id) throws MasterResourceNotFoundException {
        return administradorService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    public Administrador crear(@RequestBody Administrador administrador) throws Exception {
        return administradorService.create(administrador);
    }

    // Editar Adminisgtrador
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/editar/{id}")
    public Administrador editar(@PathVariable Long id, @RequestBody Administrador administrador) throws Exception {
        return administradorService.edit(id, administrador);
    }

    // Eliminar Administrador
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable Long id) throws MasterResourceDeletedException {
        administradorService.delete(id);
    }

}
