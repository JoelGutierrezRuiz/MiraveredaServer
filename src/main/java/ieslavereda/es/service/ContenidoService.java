package ieslavereda.es.service;

import com.google.gson.JsonObject;
import ieslavereda.es.repository.ContenidoRepository;
import ieslavereda.es.repository.model.Contenido;
import ieslavereda.es.repository.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLPermission;
import java.text.ParseException;
import java.util.List;

@Service
public class ContenidoService {

    @Autowired
    ContenidoRepository contenidoRepository;

    public Data getContenido(String titulo) throws SQLException {

        try {
            return contenidoRepository.getContenido(titulo);
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }
    public Data getContenidos() throws SQLException {
        try {
            return contenidoRepository.getContenidos();
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }
    public Contenido getContenidoById(Integer idContenido) throws SQLException {

        try{
            return contenidoRepository.getContenidoById(idContenido);
        }catch (SQLException e){
            throw new SQLException(e);
        }


    }
    public boolean postContenido(Contenido contenido) throws SQLException {

        return contenidoRepository.postContenido(contenido);

    }

    public Contenido putContenido(Contenido contenido) throws SQLException{
        try{
            return contenidoRepository.putContenido(contenido);
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }
    public void deleteContenido(Integer idContenido) throws SQLException {
        try {
            contenidoRepository.deleteContenido(idContenido);
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public void insertarTodosLosContenidos() throws IOException, ParseException, SQLException {

        contenidoRepository.insertarTodosLosContenidos();

    }

}
