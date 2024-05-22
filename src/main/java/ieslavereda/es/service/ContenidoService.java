package ieslavereda.es.service;

import com.google.gson.JsonObject;
import ieslavereda.es.repository.ContenidoRepository;
import ieslavereda.es.repository.model.Contenido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@Service
public class ContenidoService {

    @Autowired
    ContenidoRepository contenidoRepository;

    public List<Contenido> getContenido(String titulo) throws SQLException {

        try {
            return contenidoRepository.getContenido(titulo);
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public boolean postContenido(JsonObject contenido){

        return contenidoRepository.postContenido(contenido);

    }

    public void insertarTodosLosContenidos() throws IOException, ParseException, SQLException {

        contenidoRepository.insertarTodosLosContenidos();

    }

}
