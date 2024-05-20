package ieslavereda.es.repository.model;

import lombok.*;

import java.util.Date;
@Setter
@NoArgsConstructor
public class Pelicula extends Contenido{

   private Date disponibleHasta;
   private Date changedTS_peli;

    public Pelicula(int id, String nombreDir, String genero, int id_tarifa, Date fecha, int valoracion, String desc, int duracion, String tipo, Date changedTS, Date changedTS_peli, Date disponibleHasta,String titulo) {
        super(id, nombreDir, genero, id_tarifa, fecha, valoracion, desc, duracion, tipo, changedTS,titulo);
        this.changedTS_peli = changedTS_peli;
        this.disponibleHasta = disponibleHasta;
    }
}
