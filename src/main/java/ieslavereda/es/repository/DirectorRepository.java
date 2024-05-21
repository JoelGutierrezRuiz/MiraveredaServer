package ieslavereda.es.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Response;
import ieslavereda.es.Api.ConectionApi;
import ieslavereda.es.repository.model.Director;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DirectorRepository {



    List<Director> directores;


    public DirectorRepository(){
        directores = new ArrayList<>();
    }




    public List<Director> getDirectores() throws IOException, ParseException {
        int totalPaginas = 10;
        for(int i=0; i<=totalPaginas;i++){
            ConectionApi connection = new ConectionApi("https://api.themoviedb.org/3/movie/top_rated?language=es&page="+i);
            Response response = connection.response();
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
                for (JsonElement peli : jsonObject.get("results").getAsJsonArray()) {
                    int id = peli.getAsJsonObject().get("id").getAsInt();
                    getDirectorById(id);
                }
            }
            response.body().close();
        }
        return directores;
    }


    public Pelicula getDirectorById(int idPelicula){
        try {
            ConectionApi connection = new ConectionApi(("https://api.themoviedb.org/3/movie/"+idPelicula+"?append_to_response=credits&language=es"));
            Response response = connection.response();
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();

                getIdDirector(jsonObject.get("credits").getAsJsonObject().get("crew").getAsJsonArray());
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        throw new RuntimeException("No se ha encontrado la película");
    }


    public void getIdDirector(JsonArray casting){
        for (JsonElement element : casting){
            String department = element.getAsJsonObject().get("known_for_department").getAsString();
            if(department.equals("Directing")){
                System.out.println("exito");
                int idDirector =  element.getAsJsonObject().get("id").getAsInt();
                String nombre = element.getAsJsonObject().get("name").getAsString();
                directores.add(new Director(idDirector,nombre));
            }
        }
    }


}
