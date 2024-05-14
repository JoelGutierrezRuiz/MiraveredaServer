package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Factura {
    private int id;
    private Date fecha;
    private double importe;
    private float IVA;
    private int id_cliente;
    private Date changedTS;
}
