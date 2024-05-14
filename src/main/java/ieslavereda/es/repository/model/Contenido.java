package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public abstract class Contenido {
    private int id;
    private String nombreDire;
    private String genero;
    private int id_tarifa;
    private Date fecha;
    private int valoMedia;
    private String desc;
    private int duracion;
    private String tipo;
    private Date changedTS;
}
