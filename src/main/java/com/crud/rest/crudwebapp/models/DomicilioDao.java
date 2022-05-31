package com.crud.rest.crudwebapp.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomicilioDao  extends JpaRepository<Domicilio, Long> {


    Optional<Domicilio> findByCalleAndNumeroAndPisoAndDeptoIgnoreCase(String calle, int numero, int piso, String depto);
}



