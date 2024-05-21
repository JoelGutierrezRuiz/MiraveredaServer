package ieslavereda.es.controller;

import ieslavereda.es.repository.model.Director;
import ieslavereda.es.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class DirectorController {

    @Autowired
    DirectorService directorService;

    @GetMapping("/api/v1/subeLosDirectores")
    public ResponseEntity<?> addAllDirectores(){

        try {
            List<Director> directores = directorService.addAllDirectores();
            return new ResponseEntity<>(directores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
