package ieslavereda.es.controller;


import ieslavereda.es.repository.model.Actor;
import ieslavereda.es.repository.model.Cliente;
import ieslavereda.es.repository.model.Data;
import ieslavereda.es.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/api/v1/{idPelicula}/actores")

    public Data getCliente(@PathVariable("idPelicula") Integer idPelicula) throws SQLException {
        try{
            return  actorService.getActores(idPelicula);
        }catch (Exception e){
            throw new SQLException(e);
        }
    }



}