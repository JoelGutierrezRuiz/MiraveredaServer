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
    public UsuarioConcreto getUsuarioById(int id) throws SQLException{
        return usuarioRepository.getUsuarioById(id);
    }
    public boolean addUsuario(UsuarioConcreto usuario) throws SQLException{
        return usuarioRepository.addUsuario(usuario);
    }
    public boolean deleteUsuario(String email) throws SQLException{
        return usuarioRepository.deleteUsuario(email);
    }
    public boolean updateUsuario(UsuarioConcreto usuario) throws SQLException{
        return usuarioRepository.updateUsuario(usuario);
    }
}
