package com.crud.rest.crudwebapp.controllers;

import com.crud.rest.crudwebapp.exceptions.NotFoundException;
import com.crud.rest.crudwebapp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    LocalidadDao localidadDao;

    @Autowired DomicilioDao domicilioDao;

    @GetMapping
    public List<Usuario> getUsuario() {

        return usuarioDao.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarUsuario(@RequestBody Usuario usuario) {

        Domicilio domicilio = usuario.getDomicilio();
        String calle = domicilio.getCalle();
        int numero = domicilio.getNumero();
        int piso = domicilio.getPiso();
        String depto = domicilio.getDepto();
        String nombreLocalidad = domicilio.getLocalidad().getNombre();
        // (*) Para no almacenar localidades duplicadas, hago el control manual de si existe o no en la BD
        // Otra opción sería ponerle cascadeType.ALL al usuario y no es necesario chequearlo, pero se graba siempre
        // lo que causa redundancia de datos (no tendría sentido tener una tabla de localidades).
        Optional<Localidad> optLoc = localidadDao.findByNombreIgnoreCase(nombreLocalidad);
        if(optLoc.isEmpty()){ // grabo entidad localidad (*)
            localidadDao.save(usuario.getDomicilio().getLocalidad());
        } else{ //si la localidad ya existe en la BD, se la asigno al domicilio del usuario (y no la grabo)
            usuario.getDomicilio().setLocalidad(optLoc.get());
        }

        // grabo entidad domicilio (*)
            domicilioDao.save(domicilio);
        //   Finalmente grabo la entidad principal, usuario
        usuarioDao.save(usuario);
    }

    @GetMapping(path = "/{id}")
    public Usuario getUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> optUsu = usuarioDao.findById(id);
        if (optUsu.isPresent()) {
            return optUsu.get();
        }
        throw new NotFoundException();

    }

    @PutMapping(path = "/{id}")
    public void editUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> optUsu = usuarioDao.findById(id);
        if (optUsu.isEmpty()) {
            throw new NotFoundException();
        }
        usuarioDao.save(usuario);

    }
    @DeleteMapping(path = "/{id}")
    public void editUsuario(@PathVariable Long id) {
        Optional<Usuario> optUsu = usuarioDao.findById(id);
        if (optUsu.isEmpty()) {
            throw new NotFoundException();
        }

        usuarioDao.deleteById(id);
        domicilioDao.delete(optUsu.get().getDomicilio());
        //acá borro las entidades en el orden contrario a que fueron insertadas (porque no tienen cascade).

    }
}
