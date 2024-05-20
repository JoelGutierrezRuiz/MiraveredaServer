package ieslavereda.es.repository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import ieslavereda.es.repository.model.Actor;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepository {

    List<Actor> actores;



    public ActorRepository(){
        actores = new ArrayList<>();
    }


    public List<Actor> getActores (int idPelicula) throws RuntimeException{

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/"+idPelicula+"?append_to_response=credits&language=es/ES")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZjZiNjg4MDlkZGMyYTAyOGZmNzZiODY3ZWE5ZjI5MCIsInN1YiI6IjY2NDRjYzAwOGU2NDk3ZWY2ZTViY2JjZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MstHGYLhi2JqMKg98iEOknnVws5W12bYu5jRRSu7WN4")
                    .build();
            Response response = client.newCall(request).execute();

            String respuestaString = response.body().string();

            JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();

            int cantidadActores = 10;
            for (int i=0; i<=cantidadActores;i++){
                String nombre = jsonObject.get("credits").getAsJsonObject().get("cast").getAsJsonArray().get(i).getAsJsonObject().get("name").getAsString();
                String personaje = jsonObject.get("credits").getAsJsonObject().get("cast").getAsJsonArray().get(i).getAsJsonObject().get("character").getAsString();
                Integer id = jsonObject.get("credits").getAsJsonObject().get("cast").getAsJsonArray().get(i).getAsJsonObject().get("id").getAsInt();
                String img = "https://image.tmdb.org/t/p/original"+jsonObject.get("credits").getAsJsonObject().get("cast").getAsJsonArray().get(i).getAsJsonObject().get("profile_path").getAsString();

                Actor actor = new Actor(id,nombre,personaje,img);

                actores.add(actor);

            }


        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return actores;



    }






}
