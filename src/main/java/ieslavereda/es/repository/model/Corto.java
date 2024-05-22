package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Corto extends Contenido{

    public Corto(int id,int precio ,int idDirector, String genero, int id_tarifa, Date fecha, int valoMedia, String desc, int duracion, String tipo,String titulo,String img) {
        super(id,precio ,idDirector, genero, id_tarifa, fecha, valoMedia, desc, duracion, tipo, titulo,img);
    }
}
