package dbpackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Aleksandra Marysheva on 18.03.2016.
 */
@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long university_id;
    private String title;

    public University() {
    }

    public University(long university_id, String title) {
        this.university_id = university_id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(long university_id) {
        this.university_id = university_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
