package ieslavereda.es.repository;

import ieslavereda.es.repository.model.Usuario;
import ieslavereda.es.repository.model.UsuarioConcreto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    @Autowired
    @Qualifier("oracleDataSource")
    DataSource dataSource;

    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "{call getAllUsuario(?)}";
        try (Connection connection = dataSource.getConnection();
             CallableStatement cs = connection.prepareCall(query)) {
            cs.registerOutParameter(1, Types.REF_CURSOR);
            cs.execute();
            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                while (rs.next()) {
                    usuarios.add(new UsuarioConcreto(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8),
                            rs.getDate(9)
                    ));
                }
            }
        }
        return usuarios;
    }
    public List<Usuario> getUsuarioByName(String nombre) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "{ ? = call getUsuarioByName(?) }";
        try (Connection connection = dataSource.getConnection();
             CallableStatement cs = connection.prepareCall(query)) {
            cs.registerOutParameter(1, Types.REF_CURSOR);
            cs.setString(2, nombre);
            cs.execute();
            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                if (rs.next()) {
                    usuarios.add(new UsuarioConcreto(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8),
                            rs.getDate(9)
                    ));
                }
            }
        }
        return usuarios;
    }
    public boolean addUsuario(Usuario usuario) {
        String query = "{ call addUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
        try (Connection connection = dataSource.getConnection();
             CallableStatement cs = connection.prepareCall(query)) {
            cs.setInt(1, usuario.getIdRol());
            cs.setString(2, usuario.getNombre());
            cs.setString(3, usuario.getContrasenya());
            cs.setString(4, usuario.getApellido());
            cs.setString(5, usuario.getEmail());
            cs.setString(6, usuario.getDomicilio());
            cs.setInt(7, usuario.getCP());
            cs.setDate(8, new java.sql.Date(usuario.getFechaNac().getTime()));
            cs.registerOutParameter(9, Types.INTEGER);

            cs.execute();
            int result = cs.getInt(9);
            return result == 1; // Retorna true si la inserción fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Manejo de excepción, retorna false en caso de error
        }
    }
    public boolean deleteUsuario(String email) {
        String procedimiento = "{call deleteUsuario(?, ?)}";
        boolean eliminado = false;

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(procedimiento)) {

            // Establecer parámetros
            statement.setString(1, email);
            statement.registerOutParameter(2, java.sql.Types.INTEGER);

            // Ejecutar procedimiento almacenado
            statement.execute();

            // Obtener el resultado del procedimiento (1 si se eliminó, 0 en caso contrario)
            int resultado = statement.getInt(2);
            eliminado = resultado == 1;
        } catch (SQLException e) {
            // Manejar excepciones
            e.printStackTrace();
        }
        return eliminado;
    }
}
