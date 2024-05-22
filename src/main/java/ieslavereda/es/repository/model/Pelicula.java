package ieslavereda.es.repository.model;

import lombok.*;

import java.util.Date;
@Setter
@NoArgsConstructor
public class Pelicula extends Contenido{

   private Date disponibleHasta;

    public Pelicula(int id,int precio, int idDirector, String genero, int id_tarifa, Date fecha, float valoracion, String desc, int duracion, String tipo, Date disponibleHasta,String titulo,String img) {
        super(id,precio, idDirector, genero, id_tarifa, fecha, valoracion, desc, duracion, tipo,titulo,img);
        this.disponibleHasta = disponibleHasta;
    }
}
