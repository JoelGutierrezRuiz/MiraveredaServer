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
import java.sql.*;
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

    public List<Director> insertarDirectores() throws IOException, ParseException, SQLException {
        int totalPaginas = 10;
        for(int i=0; i<=totalPaginas;i++){
            ConectionApi connection = new ConectionApi("https://api.themoviedb.org/3/movie/top_rated?language=es&page="+i);
            Response response = connection.response();
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
                int idPeli;
                Director director;
                for (JsonElement peli : jsonObject.get("results").getAsJsonArray()) {
                    idPeli = peli.getAsJsonObject().get("id").getAsInt();
                    director = getIdDirector(idPeli);
                    directores.add(director);
                }
            }
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
            int idDirector;
            String nombre;
            Director director;
            if(response.isSuccessful()) {
                String respuestaString = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(respuestaString).getAsJsonObject();
                String department;
                for (JsonElement element : jsonObject.get("credits").getAsJsonObject().get("crew").getAsJsonArray()){
                    department = element.getAsJsonObject().get("known_for_department").getAsString().toLowerCase();
                    if(department.equals("directing")){
                        idDirector =  element.getAsJsonObject().get("id").getAsInt();
                        nombre = element.getAsJsonObject().get("name").getAsString();
                        director = new Director(idDirector,nombre);
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
            cs.close();
        }
    }

    public Director getDirectorById(int idDirector) throws SQLException {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String query = " select * from director where id=?";
        try (Connection con = ds.getConnection();
             PreparedStatement prep = con.prepareStatement(query)){
            prep.setInt(1,idDirector);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                Director director = new Director(rs.getInt("id"), rs.getString("nombre"));
                return director;
            }
        }catch (SQLException e){
            throw new SQLException(e);
        }
        return null;
    }
}
