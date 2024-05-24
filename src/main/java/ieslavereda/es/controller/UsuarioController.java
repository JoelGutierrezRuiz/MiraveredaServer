package ieslavereda.es.controller;

import ieslavereda.es.repository.model.*;
import ieslavereda.es.service.DirectorService;
import ieslavereda.es.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsuarios(){
        try {
            List<Usuario> usuarios = usuarioService.getAllUsuarios();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/usuarios/{nombre}")
    public ResponseEntity<?> getUsuarioByName(@PathVariable("nombre") String nombre){
        try {
            List<Usuario> usuarios = usuarioService.getUsuarioByName(nombre);
            return new ResponseEntity<>(usuarios,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/usuariosId/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable("id") int id){
        try {
            UsuarioConcreto usuario = usuarioService.getUsuarioById(id);
            if (usuario==null)
                return new ResponseEntity<>(usuario, HttpStatus.FOUND);
            return new ResponseEntity<>(usuario,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/usuarios")
    public ResponseEntity<?> addUsuario(@RequestBody UsuarioConcreto usuario){
        try{
            Boolean usuario1 = usuarioService.addUsuario(usuario);
            if (!usuario1){
                return new ResponseEntity<>(usuario1, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(usuario1, HttpStatus.OK);
        } catch (SQLException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/usuarios/iniciarSesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginRequest loginRequest){
        try {
            Boolean prueba = usuarioService.iniciarSesion(loginRequest);
            ArrayList<Boolean> sender = new ArrayList<>();
            sender.add(prueba);
            if (!prueba)
                return new ResponseEntity<>(new Data(sender), HttpStatus.FOUND);
            return new ResponseEntity<>(prueba,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/usuarios")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioConcreto usuario){
        try{
            Boolean usuario1 = usuarioService.updateUsuario(usuario);
            if (!usuario1){
                return new ResponseEntity<>(usuario1, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(usuario1, HttpStatus.OK);
        } catch (SQLException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/usuarios/{email}")
    public ResponseEntity<?> deleteUsario(@PathVariable("email") String email){
        try {
            boolean usuario = usuarioService.deleteUsuario(email);
            if (!usuario){
                return new ResponseEntity<>(usuario, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (SQLException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
