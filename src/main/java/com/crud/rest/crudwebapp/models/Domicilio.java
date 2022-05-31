package com.crud.rest.crudwebapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "domicilios")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Domicilio {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String calle;
    @Getter @Setter
    private int numero;
    @Getter @Setter
    private int piso;
    @Getter @Setter
    private String depto;
    @Getter @Setter
    @ManyToOne
    @JoinColumn
    private Localidad localidad;
    @Getter @Setter
    private String obs;
    @Getter @Setter
    @JsonIgnore
    @OneToMany (mappedBy = "domicilio")
    private List<Usuario> usuarioList;
}
