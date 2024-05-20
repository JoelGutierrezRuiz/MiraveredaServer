package ieslavereda.es.repository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.xdevapi.Client;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import ieslavereda.es.repository.model.Cliente;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {



    private List<Cliente> clientes;


    public ClienteRepository(){
        clientes = new ArrayList<>();
        clientes.add(new Cliente(1111,"Joel","Lopez","198024","@gmail.com",111111,new Date(2024,1,1),46160,"Mi casa num 5"));
        clientes.add(new Cliente(1111,"Ivan","Lopez","198024","@gmail.com",111111,new Date(2024,1,1),46160,"Mi casa num 5"));
        clientes.add(new Cliente(1111,"Aray","Lopez","198024","@gmail.com",111111,new Date(2024,1,1),46160,"Mi casa num 5"));


    }


    public List<Cliente> getClientes() throws IOException {
        return clientes;
    }


    public Cliente getCliente(String nombre){
        return new Cliente(1111,nombre,"Lopez","198024","@gmail.com",111111,new Date(2024,1,1),46160,"Mi casa num 5");
    }


}
