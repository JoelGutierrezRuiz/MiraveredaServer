package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario{
    private Date changedTS;
    private int ntarjeta;
    private Date fechaNac;
    private int CP;
    private String Domicilio;

    public Cliente(int id, String nombre, String apellidos, String contrasenya, String email, Date changedTS, Date changedTS1, int ntarjeta, Date fechaNac, int CP, String domicilio) {
        super(id, nombre, apellidos, contrasenya, email, changedTS);
        this.changedTS = changedTS1;
        this.ntarjeta = ntarjeta;
        this.fechaNac = fechaNac;
        this.CP = CP;
        Domicilio = domicilio;
    }

    @Override
    public boolean equals(Object object){
        if(object==null || !(object instanceof Cliente))
            return false;
        Cliente cliente = (Cliente) object;
        return getId() == cliente.getId();
     }

     @Override
    public int hashCode(){
        return getId();
     }

}
