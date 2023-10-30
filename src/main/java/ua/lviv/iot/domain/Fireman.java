package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Fireman {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @ManyToMany(mappedBy = "firemans")
    private Set<Departure> departures;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fireman fireman = (Fireman) o;
        return Objects.equals(id, fireman.id) && Objects.equals(name, fireman.name) && Objects.equals(surname, fireman.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    public Set<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(Set<Departure> departures) {
        this.departures = departures;
    }
}
