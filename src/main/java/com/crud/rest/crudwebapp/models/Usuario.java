package com.crud.rest.crudwebapp.models;


import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String apellido;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private int edad;
    @Getter @Setter
    @ManyToOne //por si hay familiares
    @JoinColumn
    private Domicilio domicilio;



}
