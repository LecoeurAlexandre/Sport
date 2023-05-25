package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class FavoriteSport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String sport;
    @OneToMany(mappedBy = "favoriteSport")
    private List<User> users;

    public FavoriteSport() {
    }

    public FavoriteSport(String sport) {
        this.sport = sport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "FavoriteSport{" +
                "id=" + id +
                ", sport='" + sport + '\'' +
                ", users=" + users +
                '}';
    }
}
