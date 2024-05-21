package ieslavereda.es.controller;

import ieslavereda.es.repository.model.Director;
import ieslavereda.es.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class DirectorController {
    @Autowired
    DirectorService directorService;
    @GetMapping("/api/v1/insertarDirectores")
    public ResponseEntity<?> insertarTodos(){
        try {
            List<Director> directores = directorService.insertarTodos();
            return new ResponseEntity<>(directores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
