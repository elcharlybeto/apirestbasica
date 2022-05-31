package com.crud.rest.crudwebapp.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalidadDao extends JpaRepository<Localidad, String> {

    Optional<Localidad>  findByNombreIgnoreCase(String nombre);
}
