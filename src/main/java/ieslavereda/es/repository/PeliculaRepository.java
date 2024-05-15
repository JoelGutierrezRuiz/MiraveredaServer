package ieslavereda.es.repository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Date;

@Repository
public class PeliculaRepository {




    public Pelicula getPelicula(String nombre) throws IOException {

        try {
            nombre.replace(" ","%");

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/search/movie?query="+nombre+"&include_adult=false&language=es&page=1")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZjZiNjg4MDlkZGMyYTAyOGZmNzZiODY3ZWE5ZjI5MCIsInN1YiI6IjY2NDRjYzAwOGU2NDk3ZWY2ZTViY2JjZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MstHGYLhi2JqMKg98iEOknnVws5W12bYu5jRRSu7WN4")
                    .build();
            Response response = client.newCall(request).execute();

            String respuestaString = response.body().string();

            JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();

            Pelicula pelicula = new Pelicula();

            pelicula.setId(1);
            pelicula.setDuracion(122);
            pelicula.setFecha(new Date(2024,1,1));
            pelicula.setGenero("Nose");
            pelicula.setTipo("Accion");
            pelicula.setDisponibleHasta(new Date(2024,1,1));
            pelicula.setNombreDire("Joel");
            pelicula.setTitulo(jsonObject.get("results").getAsJsonArray().get(0).getAsJsonObject().get("original_title").toString());
            pelicula.setId_tarifa(22);
            pelicula.setValoMedia(23);
            return pelicula;

        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

}
