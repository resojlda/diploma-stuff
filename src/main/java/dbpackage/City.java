package dbpackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Aleksandra Marysheva on 14.03.2016.
 */
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private int vk_id;
    private String title;

    public City(String title, int vk_id) {
        this.title = title;
        this.vk_id = vk_id;
    }

    public City() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVk_id() {
        return vk_id;
    }

    public void setVk_id(int vk_id) {
        this.vk_id = vk_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
