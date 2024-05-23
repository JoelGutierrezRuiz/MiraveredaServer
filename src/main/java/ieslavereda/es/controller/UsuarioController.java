package ieslavereda.es.controller;

import ieslavereda.es.repository.model.Pelicula;
import ieslavereda.es.repository.model.Usuario;
import ieslavereda.es.repository.model.UsuarioConcreto;
import ieslavereda.es.service.DirectorService;
import ieslavereda.es.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    @GetMapping("/usuarios/iniciarSesion/&email={email}&contrasenya={contrasenya}")
    public ResponseEntity<?> iniciarSesion(@PathVariable("email") String email,@PathVariable("contrasenya") String contrasenya){
        try {
            boolean prueba = usuarioService.iniciarSesion(email,contrasenya);
            if (!prueba)
                return new ResponseEntity<>(prueba, HttpStatus.FOUND);
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
