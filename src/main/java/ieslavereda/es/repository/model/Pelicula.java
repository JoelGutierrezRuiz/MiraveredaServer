package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pelicula extends Contenido{

   private Date disponibleHasta;
   private Date changedTS_peli;

    public Pelicula(int id, String nombreDire, String genero, int id_tarifa, Date fecha, int valoMedia, String desc, int duracion, String tipo, Date changedTS, Date changedTS_peli, Date disponibleHasta) {
        super(id, nombreDire, genero, id_tarifa, fecha, valoMedia, desc, duracion, tipo, changedTS);
        this.changedTS_peli = changedTS_peli;
        this.disponibleHasta = disponibleHasta;
    }
}
