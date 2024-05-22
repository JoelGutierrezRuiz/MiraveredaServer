package ieslavereda.es.service;

import ieslavereda.es.repository.DirectorRepository;
import ieslavereda.es.repository.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@Service
public class DirectorService {
    @Autowired
    DirectorRepository directorRepository;


    public List<Director> insertarDirectores() throws IOException, ParseException, SQLException {
        return directorRepository.insertarDirectores();
    }

}
