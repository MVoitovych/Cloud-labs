package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Departure {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "short_description")
    private String shortDescription;
    @OneToMany(mappedBy = "departureByDepartureId")
    private List<EmergencyCall> emergencyCall;
    @ManyToMany
    @JoinTable(name = "departure_firecar", catalog = "", schema = "firedepartment", joinColumns = @JoinColumn(name = "departure_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "firecar_id", referencedColumnName = "id", nullable = false))
    private Set<Firecar> firecars;
    @ManyToMany
    @JoinTable(name = "departure_fireman", catalog = "", schema = "firedepartment", joinColumns = @JoinColumn(name = "departure_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "fireman_id", referencedColumnName = "id", nullable = false))
    private Set<Fireman> firemans;

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
        Departure departure = (Departure) o;
        return Objects.equals(id, departure.id) && Objects.equals(shortDescription, departure.shortDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortDescription);
    }

    public List<EmergencyCall> getCall() {
        return emergencyCall;
    }

    public void setCall(List<EmergencyCall> emergencyCall) {
        this.emergencyCall = emergencyCall;
    }

    public Set<Firecar> getFirecars() {
        return firecars;
    }

    public void setFirecars(Set<Firecar> firecars) {
        this.firecars = firecars;
    }

    public Set<Fireman> getFiremans() {
        return firemans;
    }

    public void setFiremans(Set<Fireman> firemans) {
        this.firemans = firemans;
    }
}
