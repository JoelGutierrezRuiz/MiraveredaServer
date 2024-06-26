package ieslavereda.es.controller;

import ieslavereda.es.repository.model.Director;
import ieslavereda.es.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DirectorController {
    @Autowired
    DirectorService directorService;


    @GetMapping("/api/v1/insertarDirectores")
    public ResponseEntity<?> insertarDirectores(){
        try {
            List<Director> directores = directorService.insertarDirectores();
            return new ResponseEntity<>(directores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/director/{idDirector}")
    public ResponseEntity<?> getDirectorById(@PathVariable("idDirector") Integer idDirector){
        try {
            Director director = directorService.getDirectorById(idDirector);
            return new ResponseEntity<>(director,HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
