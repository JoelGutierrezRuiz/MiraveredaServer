package ieslavereda.es.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Response;
import ieslavereda.es.Api.ConectionApi;
import ieslavereda.es.repository.model.Contenido;
import ieslavereda.es.repository.model.MyDataSource;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Repository
public class ContenidoRepository {


    List<Pelicula> peliculas;

    public  ContenidoRepository(){
        peliculas = new ArrayList<>();
    }



    public List<Pelicula> insertarTodosLosContenidos() throws IOException, ParseException, SQLException {
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
        insertarTodos();
        return peliculas;
    }

    public void insertarTodos() throws SQLException {
        for(Contenido contenido : peliculas){
            insertarContenido(contenido);
            System.out.println(contenido.getTitulo());
        }
    }

    public void insertarContenido(Contenido contenido) throws SQLException {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        //id,genero,imagen,idTarifa,idDirector,titulo,precio,valoracion,descripcion,duracion,fechaEstreno,current_timestamp,tipo
        String query = "{call insertarContenido(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try(Connection con = ds.getConnection()){
            CallableStatement cs = con.prepareCall(query);
            cs.setInt(1,contenido.getId());
            cs.setString(2,contenido.getGenero());
            cs.setInt(3,1);
            cs.setInt(4,contenido.getIdDirector());
            cs.setString(5,contenido.getTitulo());
            cs.setInt(6,11);
            cs.setFloat(7,contenido.getValoMedia());
            cs.setString(8,contenido.getDesc());
            cs.setInt(9,contenido.getDuracion());
            cs.setDate(10, (java.sql.Date) contenido.getFecha());
            cs.setString(11,contenido.getTipo());
            cs.execute();
        }
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
