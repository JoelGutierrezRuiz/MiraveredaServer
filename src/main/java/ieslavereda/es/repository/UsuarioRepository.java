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
}
