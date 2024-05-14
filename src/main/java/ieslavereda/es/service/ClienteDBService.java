package ieslavereda.es.service;

import ieslavereda.es.repository.ClienteDBRepository;
import ieslavereda.es.repository.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClienteDBService {

    @Autowired
    private ClienteDBRepository dbRepository;

    public List<Cliente> getAll() throws SQLException {
        return dbRepository.getAll();
    }
    public Cliente getbyID(int id) throws SQLException{
        return dbRepository.getbyID(id);
    }
    public Cliente deleteCliente(int id) throws SQLException{
        return dbRepository.deleteCliente(id);
    }
    public Cliente addCliente(Cliente cliente) throws SQLException{
        return dbRepository.addCliente(cliente);
    }
    public Cliente updateCLiente(Cliente cliente) throws SQLException{
        return dbRepository.updateCliente(cliente);
    }

}
