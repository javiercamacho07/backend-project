package com.gestion.clientes.model.controller;


import com.gestion.clientes.exception.exceptions.MasterResourceDeletedException;
import com.gestion.clientes.exception.exceptions.MasterResourceNotFoundException;
import com.gestion.clientes.model.entity.Tienda;
import com.gestion.clientes.model.repository.ITiendaDao;
import com.gestion.clientes.model.service.ITiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Tiendas")
public class TiendaController {

    @Autowired
    private ITiendaDao tiendaDao;

    @Autowired
    private ITiendaService tiendaService;

    // Buscar Por Id
    @GetMapping("/ver/{id}")
    public Tienda finByIdTienda (
            @PathVariable Long id) throws MasterResourceNotFoundException {
        return tiendaService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    public Tienda crear(@RequestBody Tienda tienda) throws Exception {
        return tiendaService.create(tienda);
    }

    // Editar Tienda
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/editar/{id}")
    public Tienda editar(@PathVariable Long id, @RequestBody Tienda tienda) throws Exception {
        return tiendaService.edit(id, tienda);
    }

    // Eliminar Tienda
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable Long id) throws MasterResourceDeletedException {
        tiendaService.delete(id);
    }

}
