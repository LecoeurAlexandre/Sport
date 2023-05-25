package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private LocalDate date;
    private LocalTime hour;
    private int minAge;
    private int registrationsAvailable;

    public Activity() {
    }

    public Activity(String name, LocalDate date, LocalTime hour, int minAge, int registrationsAvailable) {
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.minAge = minAge;
        this.registrationsAvailable = registrationsAvailable;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getRegistrationsAvailable() {
        return registrationsAvailable;
    }

    public void setRegistrationsAvailable() {
        this.registrationsAvailable -= 1;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", hour=" + hour +
                ", minAge=" + minAge +
                ", registrationsAvailable=" + registrationsAvailable +
                '}';
    }
}
