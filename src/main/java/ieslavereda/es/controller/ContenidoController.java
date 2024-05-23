package ieslavereda.es.controller;

import com.google.gson.JsonObject;
import ieslavereda.es.repository.ContenidoRepository;
import ieslavereda.es.repository.model.Contenido;
import ieslavereda.es.repository.model.Data;
import ieslavereda.es.repository.model.Pelicula;
import ieslavereda.es.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ContenidoController {


    @Autowired
    ContenidoService contenidoService;
    @GetMapping("/getContenido/{titulo}")
    public ResponseEntity<?> getCotenido(@PathVariable("titulo") String titulo){
        try {
            Data contenido = contenidoService.getContenido(titulo);
            return new ResponseEntity<>(contenido, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getContenidos")
    public ResponseEntity<?> getContenidos(){
        try{
            Data contenidos = contenidoService.getContenidos();
            return new ResponseEntity<>(contenidos,HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getContenidoById/{idContenido}")
    public ResponseEntity<?> getContenidoById(@PathVariable("idContenido") Integer idContenido){
        try {
            Contenido contenido = contenidoService.getContenidoById(idContenido);
            return new ResponseEntity<>(contenido,HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/insertarContenido")
    public boolean postContenido(@RequestBody Contenido contenido) throws SQLException {
        return contenidoService.postContenido(contenido);
    }

    @PutMapping("/modificarContenido")
    public ResponseEntity<?> putContenido(@RequestBody Contenido contenido) throws SQLException{
        try{

            Contenido contenidoModificado = contenidoService.putContenido(contenido);
            return new ResponseEntity<>(contenidoModificado,HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteContenido/{idContenido}")
    public ResponseEntity<?> deleteContenido(@PathVariable("idContenido") Integer idContenido ){

        try{
            contenidoService.deleteContenido(idContenido);
            return new ResponseEntity<>("Contenido eliminado",HttpStatus.OK);
        }catch (SQLException e){

            return new ResponseEntity<>("No se ha podido borrar el contenido",HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @GetMapping("/insertarTodosLosContenidos")
    public void insertarTodosLosContenidos() throws IOException, ParseException, SQLException {
        contenidoService.insertarTodosLosContenidos();
    }
}
