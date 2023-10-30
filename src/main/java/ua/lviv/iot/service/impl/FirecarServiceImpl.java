package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.domain.Firecar;
import ua.lviv.iot.exeption.FirecarNotFoundException;
import ua.lviv.iot.repository.FirecarRepository;
import ua.lviv.iot.service.FirecarService;

import java.util.List;

@Service
public class FirecarServiceImpl implements FirecarService {

    @Autowired
    private FirecarRepository firecarRepository;

    @Override
    public List<Firecar> findAll() {
        return firecarRepository.findAll();
    }

    @Override
    public Firecar findById(Integer integer) {
        return firecarRepository.findById(integer).orElseThrow(() -> new FirecarNotFoundException(integer));
    }

    @Transactional
    @Override
    public Firecar create(Firecar entity) {
        return firecarRepository.save(entity);
    }

    @Transactional
    @Override
    public void update(Integer integer, Firecar entity) {

        Firecar firecar = firecarRepository.findById(integer).orElseThrow(() -> new FirecarNotFoundException(integer));
        firecar.setDepartures(entity.getDepartures());
        firecar.setMark(entity.getMark());
        firecarRepository.save(firecar);
    }

    @Transactional
    @Override
    public void delete(Integer integer) {
        firecarRepository.deleteById(integer);
    }

}
