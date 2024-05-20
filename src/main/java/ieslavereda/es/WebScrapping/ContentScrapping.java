package ieslavereda.es.WebScrapping;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.google.gson.JsonObject;
import ieslavereda.es.Api.Conection;
import ieslavereda.es.repository.model.Actor;
import ieslavereda.es.repository.model.Contenido;
import ieslavereda.es.repository.model.Pelicula;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ContentScrapping {
    public static void main(String[] args) throws IOException, ParseException {

        List<Pelicula> peliculas;
         peliculas = new ArrayList<>();

        getPeliculas(peliculas);

        System.out.println(peliculas);


    }

    public static void getPeliculas(List peliculas) throws IOException, ParseException {
        int totalPaginas = 150;
        for(int i=0; i<=totalPaginas;i++){
            Conection connection = new Conection("https://api.themoviedb.org/3/movie/top_rated?language=es&page="+i);
            Response response = connection.connect();
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
                for (JsonElement peli : jsonObject.get("results").getAsJsonArray()) {
                    int id = peli.getAsJsonObject().get("id").getAsInt();
                    peliculas.add(getPeliculaById(id));
                }
            }
            response.body().close();
        }
    }
    public static Pelicula getPeliculaById(int idPelicula){
        try {
            Conection connection = new Conection(("https://api.themoviedb.org/3/movie/"+idPelicula+"?append_to_response=credits&language=es"));
            Response response = connection.connect();
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();

                String title = jsonObject.get("title").getAsString();
                String overview = jsonObject.get("overview").getAsString();
                String img = "https://image.tmdb.org/t/p/original" + jsonObject.get("poster_path").getAsString();
                String genre = jsonObject.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();

                String fechaStr = jsonObject.get("release_date").getAsString();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-dd-MM");
                Date fecha = (Date) formatoFecha.parse(fechaStr);

                float rating = jsonObject.get("vote_average").getAsFloat();
                int idDirector = 11;
                int duration = jsonObject.get("runtime").getAsInt();
                response.body().close();
                return new Pelicula(idPelicula, idDirector, genre, 1, fecha, rating, overview, duration, "Película", null, title, img);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        throw new RuntimeException("No se ha encontrado la película");
    }



}
