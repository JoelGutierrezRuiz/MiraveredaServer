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
    private String nombre;
    private String apellidos;
    private String contrasenya;
    private String email;
    private Date changedTS;
}
