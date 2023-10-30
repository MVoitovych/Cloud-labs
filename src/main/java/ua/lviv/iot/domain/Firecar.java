package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Firecar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "mark")
    private String mark;
    @ManyToMany(mappedBy = "firecars")
    private Set<Departure> departures;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firecar firecar = (Firecar) o;
        return Objects.equals(id, firecar.id) && Objects.equals(mark, firecar.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark);
    }

    public Set<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(Set<Departure> departures) {
        this.departures = departures;
    }
}
