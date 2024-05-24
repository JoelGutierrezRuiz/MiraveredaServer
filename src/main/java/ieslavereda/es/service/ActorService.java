package ieslavereda.es.service;

import ieslavereda.es.repository.ActorRepository;
import ieslavereda.es.repository.model.Actor;
import ieslavereda.es.repository.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Data getActores(Integer idPelicula) throws RuntimeException{
        try {

            return actorRepository.getActores(idPelicula);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }






}
