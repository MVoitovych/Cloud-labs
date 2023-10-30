package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.domain.Departure;
import ua.lviv.iot.exeption.DepartureNotFoundException;
import ua.lviv.iot.repository.DepartureRepository;
import ua.lviv.iot.service.DepartureService;

import java.util.List;

@Service
public class DepartureServiceImpl implements DepartureService {

    @Autowired
    DepartureRepository departureRepository;

    @Override
    public List<Departure> findAll() {
        return departureRepository.findAll();
    }

    @Override
    public Departure findById(Integer integer) {
        return departureRepository.findById(integer).orElseThrow(() -> new DepartureNotFoundException(integer));
    }

    @Transactional
    @Override
    public Departure create(Departure entity) {
        return departureRepository.save(entity);
    }

    @Transactional
    @Override
    public void update(Integer integer, Departure entity) {

        Departure departure = departureRepository.findById(integer).orElseThrow(() -> new DepartureNotFoundException(integer));
        departure.setCall(entity.getCall());
        departure.setFirecars(entity.getFirecars());
        departure.setFiremans(entity.getFiremans());
        departure.setShortDescription(entity.getShortDescription());
        departureRepository.save(departure);

    }

    @Transactional
    @Override
    public void delete(Integer integer) {
        departureRepository.deleteById(integer);
    }
}
