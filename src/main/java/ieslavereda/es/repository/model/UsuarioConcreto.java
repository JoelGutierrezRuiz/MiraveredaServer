package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UsuarioConcreto extends Usuario {
    public UsuarioConcreto(int id, int idRol, String nombre, String contrasenya, String apellido, String email, String domicilio, int CP, Date fechaNac) {
        super(id, idRol, nombre, contrasenya, apellido, email, domicilio, CP, fechaNac);
    }

}
