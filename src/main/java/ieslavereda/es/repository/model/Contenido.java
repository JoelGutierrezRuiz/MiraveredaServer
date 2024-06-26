package ieslavereda.es.repository.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public  class Contenido implements Serializable {
    private int id;
    private int precio;
    private int idDirector;
    private String genero;
    private int id_tarifa;
    private Date fecha;
    private float valoMedia;
    private String desc;
    private int duracion;
    private String tipo;
    private String titulo;
    private String img;
}
