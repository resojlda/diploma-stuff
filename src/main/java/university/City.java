package university;

import java.util.ArrayList;

public class City {
    private int id;
    private String title;
    private ArrayList<University> array;
    
    public City(int id, String title, ArrayList<University> array){
        this.id = id;
        this.title = title;
        this.array = array;
    }

    public ArrayList<University> getArray() {
        return array;
    }

    public void setArray(ArrayList<University> array) {
        this.array = array;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
