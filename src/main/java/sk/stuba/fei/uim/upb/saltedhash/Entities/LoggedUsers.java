package sk.stuba.fei.uim.upb.saltedhash.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class LoggedUsers {

    @Id
    private String username;

    public LoggedUsers(String username) {
        this.username = username;
    }

    public LoggedUsers() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
