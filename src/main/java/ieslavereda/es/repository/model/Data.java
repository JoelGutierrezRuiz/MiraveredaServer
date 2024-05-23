package ieslavereda.es.repository.model;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    List<?> data;
    public Data (List<?> data){
        this.data = data;
    }

    public List<?> getData() {
        return data;
    }
}
