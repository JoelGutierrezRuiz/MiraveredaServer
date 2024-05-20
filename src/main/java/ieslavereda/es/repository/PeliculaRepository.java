package ieslavereda.es.repository;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import ieslavereda.es.Api.Conection;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.stereotype.Repository;

import java.io.EOFException;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class PeliculaRepository {

    private List<Pelicula> peliculas;

//    private List<Integer> peliculasId;

    public PeliculaRepository() throws IOException {
        peliculas = new ArrayList<>();
//        peliculasId = new ArrayList<>();
//        getPeliculasId();
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
            pelicula.setNombreDire("Joel");
            pelicula.setTitulo(jsonObject.get("results").getAsJsonArray().get(0).getAsJsonObject().get("original_title").toString());
            pelicula.setId_tarifa(22);
            pelicula.setValoMedia(23);
            return pelicula;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Pelicula> getPeliculas() throws IOException, ParseException {
        int totalPaginas = 150;
        for(int i=0; i<=totalPaginas;i++){
            Conection connection = new Conection("https://api.themoviedb.org/3/movie/top_rated?language=es&page="+i);
            Response response = connection.connect();
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();

                for (JsonElement peli : jsonObject.get("results").getAsJsonArray()) {

                    int id = peli.getAsJsonObject().get("id").getAsInt();
                    String nombreDir = "append";
                    String genero = "append";
                    int id_tarifa = 1;

                    String fechaStr = peli.getAsJsonObject().get("release_date").getAsString();
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-dd-MM");
                    Date fecha = (Date) formatoFecha.parse(fechaStr);


                    float valoracion = peli.getAsJsonObject().get("vote_average").getAsFloat();
                    String desc = peli.getAsJsonObject().get("overview").getAsString();
                    int duracion = 90;
                    String tipo = "Pelicula";
                    java.util.Date disponible = new java.util.Date(2027, Calendar.MARCH, 1);
                    String titulo = peli.getAsJsonObject().get("title").getAsString();
                    String img = "https://image.tmdb.org/t/p/original"+peli.getAsJsonObject().get("poster_path").getAsString();

                    Pelicula pelicula = new Pelicula(id,nombreDir, genero, id_tarifa, fecha, valoracion, desc, duracion, tipo, disponible, titulo,img);
                    peliculas.add(pelicula);
                }
            }
        }
        return peliculas;
    }




//    public Pelicula getPeliculaByid(int idPeli) throws IOException, ParseException {
//
//            Conection connection = new Conection("https://api.themoviedb.org/3/movie/"+idPeli+"?language=es");
//            Response response = connection.connect();
//            Pelicula pelicula;
//            if(response.isSuccessful()) {
//
//                String respuestaString = response.body().string();
//                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
//                int id = jsonObject.get("id").getAsInt();
//                String nombreDir = "append";
//                String genero = "append";
//                int id_tarifa = 1;
//                String fechaStr = jsonObject.get("release_date").getAsString();
//                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-dd-MM");
//                Date fecha = (Date) formatoFecha.parse(fechaStr);
//                float valoracion = jsonObject.get("vote_average").getAsFloat();
//                String desc = jsonObject.get("overview").getAsString();
//                int duracion = 90;
//                String tipo = "Pelicula";
//                java.util.Date disponible = new java.util.Date(2027, Calendar.MARCH, 1);
//                String titulo = jsonObject.get("title").getAsString();
//                String img = "https://image.tmdb.org/t/p/original"+jsonObject.get("poster_path").getAsString();
//
//                pelicula = new Pelicula(id,nombreDir, genero, id_tarifa, fecha, valoracion, desc, duracion, tipo, disponible, titulo,img);
//                return pelicula;
//            }
//            throw new EOFException("No hemos encontrado pelicula");
//    }
//

//    public void getPeliculasId() throws IOException {
//        int totalPaginas = 150;
//        for(int i=0; i<=totalPaginas;i++) {
//            Conection connection = new Conection("https://api.themoviedb.org/3/movie/top_rated?language=es&page=" + i);
//            Response response = connection.connect();
//            if (response.isSuccessful()) {
//                String respuestaString = response.body().string();
//                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
//                for (JsonElement peli : jsonObject.get("results").getAsJsonArray()) {
//                    int id = peli.getAsJsonObject().get("id").getAsInt();
//                    peliculasId.add(id);
//                }
//            }
//        }
//    }




//    public List<Pelicula> getPeliculasBd() throws IOException, ParseException {
//        for(int id : peliculasId){
//            peliculas.add(getPeliculaByid(id));
//        }
//        return peliculas;
//    }


}
