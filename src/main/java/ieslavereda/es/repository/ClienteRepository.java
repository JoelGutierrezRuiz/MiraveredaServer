package ieslavereda.es.repository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.xdevapi.Client;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import ieslavereda.es.repository.model.Cliente;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    @Qualifier("mysqlDataSource")
    DataSource dataSource;
    private List<Cliente> clientes;


    public List<Cliente> getClientes() throws IOException {
        return clientes;
    }
}
