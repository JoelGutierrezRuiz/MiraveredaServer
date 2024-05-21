package ieslavereda.es.service;

import ieslavereda.es.repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@Service
public class ContenidoService {

    @Autowired
    ContenidoRepository contenidoRepository;


    public void subirPeliculas() throws IOException, ParseException, SQLException {

        contenidoRepository.insertarTodosLosContenidos();

    }

}
