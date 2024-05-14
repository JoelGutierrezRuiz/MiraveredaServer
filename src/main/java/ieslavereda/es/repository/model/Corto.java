package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Corto extends Contenido{
    private Date changedTS;

    public Corto(int id, String nombreDire, String genero, int id_tarifa, Date fecha, int valoMedia, String desc, int duracion, String tipo, Date changedTS, Date changeTSCorto) {
        super(id, nombreDire, genero, id_tarifa, fecha, valoMedia, desc, duracion, tipo, changedTS);
        this.changedTS = changeTSCorto;
    }
}
