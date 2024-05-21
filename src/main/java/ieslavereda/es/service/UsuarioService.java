package ieslavereda.es.service;

import ieslavereda.es.repository.PeliculaRepository;
import ieslavereda.es.repository.UsuarioRepository;
import ieslavereda.es.repository.model.Pelicula;
import ieslavereda.es.repository.model.Usuario;
import ieslavereda.es.repository.model.UsuarioConcreto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() throws SQLException {
        return  usuarioRepository.getAllUsuarios();
    }
    public List<Usuario> getUsuarioByName(String nombre) throws SQLException{
        return usuarioRepository.getUsuarioByName(nombre);
    }

}
