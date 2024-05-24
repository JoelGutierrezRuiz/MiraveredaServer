package ieslavereda.es.repository.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Actor implements Serializable {
    private int id;
    private String nombre;
    private String personaje;
    private String img;
}
