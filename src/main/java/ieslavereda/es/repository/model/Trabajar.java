package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Trabajar {
    private int id_actor;
    private int id_contenido;
    private Date changedTS;
}

