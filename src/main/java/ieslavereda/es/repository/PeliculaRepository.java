package ieslavereda.es.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import ieslavereda.es.Api.ConectionApi;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculaRepository {

    private List<Pelicula> peliculas;

    public PeliculaRepository() throws IOException {
        peliculas = new ArrayList<>();
    }

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

            pelicula.setId(jsonObject.get("results").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt());
            pelicula.setDuracion(122);
            pelicula.setFecha(new Date(2024,1,1));
            pelicula.setGenero("Nose");
            pelicula.setTipo("Accion");
            pelicula.setDisponibleHasta(new Date(2024,1,1));
            pelicula.setIdDirector(111);
            pelicula.setTitulo(jsonObject.get("results").getAsJsonArray().get(0).getAsJsonObject().get("original_title").toString());
            pelicula.setId_tarifa(22);
            pelicula.setValoMedia(23);
            return pelicula;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public List<Pelicula> getPeliculas() throws IOException, ParseException {
        int totalPaginas = 10;
        for(int i=0; i<=totalPaginas;i++){
            ConectionApi connection = new ConectionApi("https://api.themoviedb.org/3/movie/top_rated?language=es&page="+i);
            Response response = connection.response();
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
        return peliculas;
    }


    public Pelicula getPeliculaById(int idPelicula){
        try {
            ConectionApi connection = new ConectionApi(("https://api.themoviedb.org/3/movie/"+idPelicula+"?append_to_response=credits&language=es"));
            Response response = connection.response();
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
                int idDirector = getIdDirector(jsonObject.get("credits").getAsJsonObject().get("crew").getAsJsonArray());
                int duration = jsonObject.get("runtime").getAsInt();
                response.body().close();
                return new Pelicula(idPelicula, idDirector, genre, 1, fecha, rating, overview, duration, "Película", null, title, img);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        throw new RuntimeException("No se ha encontrado la película");
    }


    public Integer getIdDirector(JsonArray casting){
        for (JsonElement element : casting){
            String department = element.getAsJsonObject().get("known_for_department").getAsString();
            if(department.equals("Directing")){
                System.out.println("exito");
                 return element.getAsJsonObject().get("id").getAsInt();
            }
        }
        return -1;
    }

}
