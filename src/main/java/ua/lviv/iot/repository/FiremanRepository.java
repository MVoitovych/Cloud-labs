package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.domain.Fireman;

public interface FiremanRepository extends JpaRepository<Fireman, Integer> {
}
