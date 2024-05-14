package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Añadir {
   private int id_contenido;
   private int id_cliente;
   private Date fecha;
   private Date changedTS;
}
