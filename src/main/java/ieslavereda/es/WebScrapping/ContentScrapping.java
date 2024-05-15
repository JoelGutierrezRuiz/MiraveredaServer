package ieslavereda.es.WebScrapping;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.google.gson.JsonObject;
import java.io.IOException;

public class ContentScrapping {
    public static void main(String[] args) throws IOException {


        String nombre = "el padrino";

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

        System.out.println(jsonObject.get("results").getAsJsonArray().get(0).getAsJsonObject().get("original_title"));


    }



}
