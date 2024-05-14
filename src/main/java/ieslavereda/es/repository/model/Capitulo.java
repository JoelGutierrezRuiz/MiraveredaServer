package ieslavereda.es.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString

public class Capitulo extends Contenido{
    private String nombre;
    private Date changedTSCapitulo;
    private boolean disponibilidad;

    public Capitulo(int id, String nombreDire, String genero, int id_tarifa, Date fecha, int valoMedia, String desc, int duracion, String tipo, Date changedTS, String nombre, Date changedTSCapi, boolean disponibilidad) {
        super(id, nombreDire, genero, id_tarifa, fecha, valoMedia, desc, duracion, tipo, changedTS);
        this.nombre = nombre;
        this.changedTSCapitulo = changedTSCapi;
        this.disponibilidad = disponibilidad;
    }
}
