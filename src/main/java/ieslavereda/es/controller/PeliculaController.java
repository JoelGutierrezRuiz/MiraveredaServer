package ieslavereda.es.controller;


import ieslavereda.es.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/pelicula/{nombre}")
    public ResponseEntity<?> getPelicula(@PathVariable("nombre") String nombre){
        try {
            String titulo = peliculaService.getPelicula(nombre);
            return new ResponseEntity<>(titulo,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
