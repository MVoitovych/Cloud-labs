package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.domain.EmergencyCall;

public interface EmergencyCallRepository extends JpaRepository<EmergencyCall, Integer> {
}
