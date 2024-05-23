package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Usuario {
    private int id;
    private int idRol;
    private String nombre;
    private String contrasenya;
    private String apellidos;
    private String email;
    private String domicilio;
    private int CP;
    private Date fechaNac;
}
