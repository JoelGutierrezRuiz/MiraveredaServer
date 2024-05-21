package ieslavereda.es.controller;


import ieslavereda.es.repository.model.Actor;
import ieslavereda.es.repository.model.Cliente;
import ieslavereda.es.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/api/v1/{idPelicula}/actores")

    public ResponseEntity<?> getCliente(@PathVariable("idPelicula") Integer idPelicula){
        try {
            List<Actor> actores = actorService.getActores(idPelicula);
            return new ResponseEntity<>(actores, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/api/v1/prueba")

    public  ResponseEntity<?> prueba(){
        try {
            boolean respuesta = actorService.prueba();
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }



}
