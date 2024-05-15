package ieslavereda.es.WebScrapping;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static java.rmi.server.LogStream.log;

public class ContentScrapping {


    public static void main(String[] args) throws IOException {

        String url = "https://m.imdb.com/list/ls063676189/";


        try{
            Document document = Jsoup.connect(url).get();
            Elements titles = document.select(".ipc-title__text");

            for(Element element : titles){

                String title = element.text();
                System.out.println(title);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
