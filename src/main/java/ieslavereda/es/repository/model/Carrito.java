package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Carrito {
   private int id_cliente;
   private int lineaFactura;
   private Date changedTS;
}
