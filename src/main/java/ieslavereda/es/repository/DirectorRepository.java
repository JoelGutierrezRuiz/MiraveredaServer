package ieslavereda.es.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Response;
import ieslavereda.es.Api.ConectionApi;
import ieslavereda.es.repository.model.Director;
import ieslavereda.es.repository.model.MyDataSource;
import ieslavereda.es.repository.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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

    public List<Director> getDirectores() throws IOException, ParseException, SQLException {
        int totalPaginas = 10;
        for(int i=0; i<=totalPaginas;i++){
            ConectionApi connection = new ConectionApi("https://api.themoviedb.org/3/movie/top_rated?language=es&page="+i);
            Response response = connection.response();
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
                for (JsonElement peli : jsonObject.get("results").getAsJsonArray()) {
                    int id = peli.getAsJsonObject().get("id").getAsInt();
                    directores.add(getIdDirector(id));
                }
            }
            response.body().close();
        }
        insertarTodos();
        return directores;
    }

    public void insertarTodos() throws SQLException {
        for (Director director : directores){
            if(director!=null) {
                insertarDirector(director.getId(), director.getNombre());
                System.out.println(director.getNombre());
            }
        }
    }

    public Director getIdDirector(int idPelicula){

        try {
            ConectionApi connection = new ConectionApi(("https://api.themoviedb.org/3/movie/"+idPelicula+"?append_to_response=credits&language=es"));
            Response response = connection.response();
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
                for (JsonElement element : jsonObject.get("credits").getAsJsonObject().get("crew").getAsJsonArray()){
                    String department = element.getAsJsonObject().get("known_for_department").getAsString();
                    if(department.equals("Directing")){
                        int idDirector =  element.getAsJsonObject().get("id").getAsInt();
                        String nombre = element.getAsJsonObject().get("name").getAsString();
                        Director director = new Director(idDirector,nombre);
                        return director;
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public void insertarDirector(int id,String nombre) throws SQLException {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String query = " {call insertarDirector(?,?)}";
        try(Connection con = ds.getConnection()){
            CallableStatement cs = con.prepareCall(query);
            cs.setInt(1,id);
            cs.setString(2,nombre);
            cs.execute();
        }
    }
}
