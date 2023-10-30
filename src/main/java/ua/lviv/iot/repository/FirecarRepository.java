package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.domain.Firecar;

public interface FirecarRepository extends JpaRepository<Firecar, Integer> {
}
