package ieslavereda.es.service;

import ieslavereda.es.repository.ClienteRepository;
import ieslavereda.es.repository.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente getCliente(String nombre) throws IOException {
        return clienteRepository.getCliente(nombre);
    }

    public List<?> getClientes() throws IOException {
        return clienteRepository.getClientes();
    }

}
