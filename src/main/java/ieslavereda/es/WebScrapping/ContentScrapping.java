package ieslavereda.es.WebScrapping;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.google.gson.JsonObject;
import ieslavereda.es.repository.model.Contenido;
import ieslavereda.es.repository.model.Pelicula;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ContentScrapping {
    public static void main(String[] args) throws IOException {


        List<Pelicula> peliculas = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/top_rated?language=es&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZjZiNjg4MDlkZGMyYTAyOGZmNzZiODY3ZWE5ZjI5MCIsInN1YiI6IjY2NDRjYzAwOGU2NDk3ZWY2ZTViY2JjZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MstHGYLhi2JqMKg98iEOknnVws5W12bYu5jRRSu7WN4")
                .build();
        Response response = client.newCall(request).execute();
        String respuestaString = response.body().string();



        JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();


        for( JsonElement peli : jsonObject.get("results").getAsJsonArray()){

            int id = 1;
            String nombreDir = "append";
            String genero = "append";
            int id_tarifa =1;
            Date fecha  = new Date(2024,Calendar.MARCH,1);
            int valoracion =1;
            String desc = peli.getAsJsonObject().get("overview").toString();
            int duracion = 90;
            String tipo = "Pelicula";
            Date changeTs  = new Date(2025,Calendar.MARCH,1);
            Date changeTs2  = new Date(2026, Calendar.MARCH,1);
            Date disponible  = new Date(2027,Calendar.MARCH,1);
            String titulo = peli.getAsJsonObject().get("title").toString();

            Pelicula pelicula = new Pelicula(id,nombreDir,genero,id_tarifa,fecha,valoracion,desc,duracion,tipo,changeTs,changeTs2,disponible,titulo);

            peliculas.add(pelicula);

        }





    }



}
