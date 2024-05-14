package ieslavereda.es.repository;

import ieslavereda.es.repository.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDBRepository {
    List<Cliente> getAll() throws SQLException;
    Cliente getbyID(int id) throws SQLException;
    Cliente deleteCliente(int id) throws SQLException;
    Cliente addCliente(Cliente cliente) throws SQLException;
    Cliente updateCliente(Cliente cliente) throws SQLException;

}
