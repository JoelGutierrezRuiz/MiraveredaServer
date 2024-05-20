package ieslavereda.es.service;

import ieslavereda.es.repository.PeliculaRepository;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;
    public Pelicula getPelicula(String nombre) throws IOException {
        return  peliculaRepository.getPelicula(nombre);
    }

    public List<Pelicula> getPeliculas() throws IOException {
        return  peliculaRepository.getPeliculas();
    }

}
