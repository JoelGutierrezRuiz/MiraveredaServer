package ieslavereda.es.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Cliente {

    private int id;
    private String nombre;
    private String apellidos;
    private String contrasenya;
    private Date changedTS;
    private int ntarjeta;
    private Date fechaNac;
    private String email;
    private int CP;
    private String Domicilio;

    @Override
    public boolean equals(Object object){
        if(object==null || !(object instanceof Cliente))
            return false;
        Cliente cliente = (Cliente) object;
        return id == cliente.getId();
     }

     @Override
    public int hashCode(){
        return id;
     }

}
