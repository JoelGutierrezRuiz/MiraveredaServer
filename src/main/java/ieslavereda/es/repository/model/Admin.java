package ieslavereda.es.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Admin extends Usuario{
    private Date changedTS1;
    private int nivel;

    public Admin(int id, String nombre, String apellidos, String contrasenya, String email, Date changedTS, int nivel) {
        super(id, nombre, apellidos, contrasenya, email);
        this.nivel = nivel;
    }

}
