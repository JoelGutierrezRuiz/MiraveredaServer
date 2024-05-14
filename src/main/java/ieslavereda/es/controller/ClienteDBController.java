package ieslavereda.es.controller;

import ieslavereda.es.repository.model.Cliente;
import ieslavereda.es.service.ClienteDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apidb")
public class ClienteDBController {
    @Autowired
    private ClienteDBService dbService;

    @GetMapping("/clientes/")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(dbService.getAll(), HttpStatus.OK);
        } catch (SQLException e){
            return response(e);
        }
    }


    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> getByID(@PathVariable("id") int id){
        try {
            Cliente cliente = dbService.getbyID(id);
            if (cliente ==null)
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (SQLException e){
            return response(e);
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") int id){
        try {
            Cliente cliente = dbService.deleteCliente(id);
            if (cliente ==null)
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (SQLException e){
            return response(e);
        }
    }

    @PostMapping("/clientes/")
    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente){
        try{
            Cliente cliente1 = dbService.addCliente(cliente);
            if (cliente1 ==null)
                return new ResponseEntity<>("Usuario ya existente", HttpStatus.FOUND);
            return new ResponseEntity<>(cliente1, HttpStatus.OK);
        } catch (SQLException e){
            return response(e);
        }
    }

    @PutMapping("/clientes/")
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente){
        try{
            Cliente cliente1 = dbService.updateCLiente(cliente);
            if (cliente1 ==null)
                return new ResponseEntity<>("Usuario no existe", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cliente1, HttpStatus.OK);
        } catch (SQLException e){
            return response(e);
        }
    }

    private ResponseEntity<?> response(SQLException e){
        Map<String,Object> response = new HashMap<>();
        response.put("code", e.getErrorCode());
        response.put("message",e.getMessage());
        response.put("sqlstate",e.getSQLState());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
