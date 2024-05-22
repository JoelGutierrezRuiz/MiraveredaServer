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
import oracle.jdbc.internal.OracleTypes;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Repository
public class ContenidoRepository {



    public List<Contenido> getContenido(String titulo) throws SQLException {
        titulo.replace("%20"," ");
        titulo.toLowerCase();
        String busqueda = "%"+titulo+"%";
        List<Contenido> contenidos = new ArrayList<>();
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String query = "SELECT * from contenido where lower(titulo) like ?";


        try(Connection con = ds.getConnection();
            PreparedStatement prep = con.prepareStatement(query)){
            prep.setString(1,busqueda);
            ResultSet rs = prep.executeQuery();
            Contenido contenido;

            while(rs.next()){
                contenido = new Contenido(rs.getInt("ID"),rs.getInt("PRECIO"),rs.getInt("ID_DIRECTOR"),rs.getString("GENERO"),rs.getInt("ID_TARIFA"),rs.getDate("FECHAESTRENO")
                ,rs.getFloat("VALORACIONMEDIA"),rs.getString("DESCRIPCION"),rs.getInt("DURACION"),rs.getString("TIPO"),
                        rs.getString("TITULO"),rs.getString("IMAGEN"));
                contenidos.add(contenido);
            }
            return contenidos;
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public List<Contenido> getContenidos() throws SQLException {

        List<Contenido> contenidos = new ArrayList<>();
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String query = "SELECT * from contenido";

        try(Connection con = ds.getConnection();
            PreparedStatement prep = con.prepareStatement(query)){
            ResultSet rs = prep.executeQuery();
            Contenido contenido;
            while(rs.next()){
                contenido = new Contenido(rs.getInt("ID"), rs.getInt("precio"), rs.getInt("ID_DIRECTOR"),rs.getString("GENERO"),rs.getInt("ID_TARIFA"),rs.getDate("FECHAESTRENO")
                        ,rs.getFloat("VALORACIONMEDIA"),rs.getString("DESCRIPCION"),rs.getInt("DURACION"),rs.getString("TIPO"),
                        rs.getString("TITULO"),rs.getString("IMAGEN"));
                contenidos.add(contenido);
            }
            return contenidos;
        }catch (SQLException e){
            throw new SQLException(e);
        }


    }

    public Contenido getContenidoById(Integer idContenido) throws SQLException {
        Contenido contenido;
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String query = "select * from contenido where id=?";
        try(Connection con = ds.getConnection();
        PreparedStatement prep = con.prepareStatement(query)){
            prep.setInt(1,idContenido);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                contenido= new Contenido(rs.getInt("ID"), rs.getInt("precio"), rs.getInt("ID_DIRECTOR"),rs.getString("GENERO"),rs.getInt("ID_TARIFA"),rs.getDate("FECHAESTRENO")
                        ,rs.getFloat("VALORACIONMEDIA"),rs.getString("DESCRIPCION"),rs.getInt("DURACION"),rs.getString("TIPO"),
                        rs.getString("TITULO"),rs.getString("IMAGEN"));
                return contenido;
            }

        }catch (SQLException e){
            throw  new SQLException(e);
        }
        return null;
    }

    public boolean postContenido(JsonObject contenido){
        System.out.println(contenido);
        return true;
    }












    List<Pelicula> peliculas;
    public  ContenidoRepository(){
        peliculas = new ArrayList<>();
    }
    public List<Pelicula> insertarTodosLosContenidos() throws IOException, ParseException, SQLException {
        int totalPaginas = 1;
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
        System.out.println("salimos");
        insertarTodos();
        return peliculas;
    }
    public void insertarTodos() throws SQLException {
        for(Contenido contenido : peliculas){
            insertarContenido(contenido);
            System.out.println(contenido.getTitulo());
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

                Date fechaUtil = formatoFecha.parse(fechaStr);
                java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());  // Usar java.sql.Date con nombre completo


                float rating = jsonObject.get("vote_average").getAsFloat();
                int idDirector = getIdDirector(jsonObject.get("credits").getAsJsonObject().get("crew").getAsJsonArray());
                int duration = jsonObject.get("runtime").getAsInt();
                response.body().close();
                return new Pelicula(idPelicula,11,idDirector, genre, 1, fecha, rating, overview, duration, "Película", null, title, img);
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
                System.out.println(element.getAsJsonObject().get("id").getAsInt());
                return element.getAsJsonObject().get("id").getAsInt();
            }
        }
        return -1;
    }
    public void insertarContenido(Contenido contenido) throws SQLException {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        //id,genero,imagen,idTarifa,idDirector,titulo,precio,valoracion,descripcion,duracion,fechaEstreno,current_timestamp,tipo
        String query = "{call ?:=insertarContenido(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try(Connection con = ds.getConnection()){
            CallableStatement cs = con.prepareCall(query);
            cs.registerOutParameter(1, OracleTypes.BOOLEAN);
            cs.setInt(2,contenido.getId());
            cs.setString(3,contenido.getGenero());
            cs.setString(4,contenido.getTipo());
            cs.setString(5,contenido.getImg());
            cs.setInt(6,1);
            cs.setInt(7,contenido.getIdDirector());
            cs.setString(8,contenido.getTitulo());
            cs.setInt(9,11);
            cs.setFloat(10,contenido.getValoMedia());
            cs.setString(11,contenido.getDesc());
            cs.setInt(12,contenido.getDuracion());
            cs.setDate(13, (java.sql.Date) contenido.getFecha());

            cs.execute();

            System.out.println(cs.getBoolean(1));
        }
    }


}
