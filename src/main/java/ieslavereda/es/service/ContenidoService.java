package ieslavereda.es.service;

import ieslavereda.es.Api.Resources;
import ieslavereda.es.repository.ContenidoRepository;
import ieslavereda.es.repository.model.Pelicula;
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


    public void subirPeliculas() throws IOException, ParseException, SQLException {

        contenidoRepository.insertarTodosLosContenidos();

    }

}
