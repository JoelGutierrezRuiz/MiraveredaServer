package ieslavereda.es.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import ieslavereda.es.Api.ConectionApi;
import ieslavereda.es.repository.model.*;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ActorRepository {

    List<Actor> actores;


    public ActorRepository(){
        actores = new ArrayList<>();
    }




    public Data getActores(int idPelicula) throws RuntimeException {
        List<Actor> actores = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/" + idPelicula + "?append_to_response=credits&language=es/ES")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZjZiNjg4MDlkZGMyYTAyOGZmNzZiODY3ZWE5ZjI5MCIsInN1YiI6IjY2NDRjYzAwOGU2NDk3ZWY2ZTViY2JjZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MstHGYLhi2JqMKg98iEOknnVws5W12bYu5jRRSu7WN4")
                    .build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String respuestaString = response.body().string();
                if (respuestaString.isEmpty()) return new Data(actores);

                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
                if (jsonObject.isJsonNull() || !jsonObject.has("credits"))return new Data(actores);

                JsonObject creditsObject = jsonObject.getAsJsonObject("credits");
                JsonArray castArray = creditsObject.getAsJsonArray("cast");
                if (castArray.isJsonNull() || castArray.size() == 0) return new Data(actores);

                int cantidadActores = 6;
                int iterador = Math.min(cantidadActores, castArray.size());
                for (int i = 0; i < iterador; i++) {
                    JsonObject actorObject = castArray.get(i).getAsJsonObject();
                    if (actorObject.has("name") && actorObject.has("character") && actorObject.has("id") && actorObject.has("profile_path")) {
                        String nombre = actorObject.get("name").getAsString();
                        String personaje = actorObject.get("character").getAsString();
                        Integer id = actorObject.get("id").getAsInt();
                        String img = "https://image.tmdb.org/t/p/original" + actorObject.get("profile_path").getAsString();
                        Actor actor = new Actor(id, nombre, personaje, img);
                        actores.add(actor);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Data(actores);
    }

    }
