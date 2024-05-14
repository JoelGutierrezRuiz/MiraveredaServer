package ieslavereda.es.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Temporada{
    private int numero;
    private Date changedTSTemporada;

    public Temporada(int numero, Date changedTSTemporada) {
        this.numero = numero;
        this.changedTSTemporada = changedTSTemporada;
    }
}
