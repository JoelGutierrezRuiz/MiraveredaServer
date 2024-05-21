package ieslavereda.es.controller;

import ieslavereda.es.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1")
public class ContenidoController {


    @Autowired
    ContenidoService contenidoService;

    @GetMapping("/subirPeliculas")
    public void subirPeliculas() throws IOException, ParseException, SQLException {
        contenidoService.subirPeliculas();
    }




}
