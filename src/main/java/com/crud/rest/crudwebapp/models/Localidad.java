package com.crud.rest.crudwebapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "localidades")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Localidad {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    private String nombre;
    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "localidad")
    private List<Domicilio> domicilioList;

}
