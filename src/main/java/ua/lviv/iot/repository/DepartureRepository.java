package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.domain.Departure;

public interface DepartureRepository extends JpaRepository<Departure, Integer> {
}
