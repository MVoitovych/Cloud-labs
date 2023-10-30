package ua.lviv.iot.domain;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class EmergencyCall {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "short_description")
    private String shortDescription;
    @ManyToOne
    @JoinColumn(name = "departure_id", referencedColumnName = "id", nullable = false)
    private Departure departureByDepartureId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmergencyCall emergencyCall = (EmergencyCall) o;
        return Objects.equals(id, emergencyCall.id) && Objects.equals(shortDescription, emergencyCall.shortDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortDescription);
    }

    public Departure getDepartureByDepartureId() {
        return departureByDepartureId;
    }

    public void setDepartureByDepartureId(Departure departureByDepartureId) {
        this.departureByDepartureId = departureByDepartureId;
    }
}
