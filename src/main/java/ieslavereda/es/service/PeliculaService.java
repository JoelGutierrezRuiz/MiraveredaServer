package ieslavereda.es.service;

import ieslavereda.es.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;
    public String getPelicula(String nombre) throws IOException {
        return  peliculaRepository.getPelicula(nombre);
    }

}
