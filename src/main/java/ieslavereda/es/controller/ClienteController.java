package ieslavereda.es.controller;


import ieslavereda.es.repository.model.Cliente;
import ieslavereda.es.repository.model.Pelicula;
import ieslavereda.es.service.ClienteService;
import ieslavereda.es.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente/{nombre}")
    public ResponseEntity<?> getCliente(@PathVariable("nombre") String nombre){
        try {
            Cliente cliente = clienteService.getCliente(nombre);
            return new ResponseEntity<>(cliente,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/clientes")
    public ResponseEntity<?> getClientes(){
        try {
            List<?> clientes = clienteService.getClientes();
            return new ResponseEntity<>(clientes,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
