package yo.antihype.team.model;

import java.util.Date;

/**
 * Created by Serhii_Vasylenko on 9/19/2017.
 */
public class User {
    private int id;
    private String name;
    private Date registrationDate;

    public User() {
    }

    public User(int id, String name, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
