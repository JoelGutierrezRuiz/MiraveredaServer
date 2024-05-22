package ieslavereda.es.controller;

import com.google.gson.JsonObject;
import ieslavereda.es.repository.model.Contenido;
import ieslavereda.es.repository.model.Pelicula;
import ieslavereda.es.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ContenidoController {


    @Autowired
    ContenidoService contenidoService;


    @GetMapping("/contenido/{titulo}")
    public ResponseEntity<?> getCotenido(@PathVariable("titulo") String titulo){
        try {
            List<Contenido> contenido = contenidoService.getContenido(titulo);
            return new ResponseEntity<>(contenido, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertarContenido")
    public boolean postContenido(@RequestBody JsonObject contenido){

        return contenidoService.postContenido(contenido);

    }


    @GetMapping("/api/v1/insertarTodosLosContenidos")
    public void insertarTodosLosContenidos() throws IOException, ParseException, SQLException {
        contenidoService.insertarTodosLosContenidos();
    }

}
