package ieslavereda.es.repository;

import ieslavereda.es.repository.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteDBRepository implements IClienteDBRepository {

    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource  dataSource;


    @Override
    public List<Cliente> getAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            while(resultSet.next()){
                clientes.add(new Cliente(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getDate(5),
                        resultSet.getInt(6), resultSet.getDate(7),
                        resultSet.getString(8), resultSet.getInt(9),
                        resultSet.getString(10)));
            }
        }

        return clientes;
    }

    @Override
    public Cliente getbyID(int id) throws SQLException {
        Cliente cliente = null;
        String query = "select * from usuario where id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
               cliente = new Cliente(resultSet.getInt(1),
                       resultSet.getString(2), resultSet.getString(3),
                       resultSet.getString(4), resultSet.getDate(5),
                       resultSet.getInt(6), resultSet.getDate(7),
                       resultSet.getString(8), resultSet.getInt(9),
                       resultSet.getString(10));
        }
        return cliente;
    }

    @Override
    public Cliente deleteCliente(int id) throws SQLException {
        Cliente cliente = getbyID(id);
        String query = "delete from usuario where id = ?";

        if(cliente == null)
            return null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }
        return cliente;
    }

    @Override
    public Cliente addCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO usuario(nombre, apellidos) VALUES(?,?)";
        Cliente cliente1 = getbyID(cliente.getId());
        if (cliente1 !=null)
            return null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.executeUpdate();
        }
        return cliente;
    }

    @Override
    public Cliente updateCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE usuario SET nombre = ?, apellidos = ? where id = ?";
        Cliente cliente1 = getbyID(cliente.getId());
        if (cliente1 ==null)
            return null;
        try(Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setInt(3, cliente.getId());
            ps.executeUpdate();

        }
        return getbyID(cliente.getId());
    }
}
