package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.domain.EmergencyCall;
import ua.lviv.iot.exeption.CallNotFoundException;
import ua.lviv.iot.repository.EmergencyCallRepository;
import ua.lviv.iot.service.CallService;

import java.util.List;

@Service
public class CallServiceImpl implements CallService {

    @Autowired
    private EmergencyCallRepository emergencyCallRepository;

    @Override
    public List<EmergencyCall> findAll() {
        return emergencyCallRepository.findAll();
    }

    @Override
    public EmergencyCall findById(Integer integer) {
        return emergencyCallRepository.findById(integer).orElseThrow(() -> new CallNotFoundException(integer));
    }

    @Transactional
    @Override
    public EmergencyCall create(EmergencyCall entity) {
        return emergencyCallRepository.save(entity);
    }

    @Transactional
    @Override
    public void update(Integer integer, EmergencyCall entity) {

        EmergencyCall emergencyCall = emergencyCallRepository.findById(integer).orElseThrow(() -> new CallNotFoundException(integer));
        emergencyCall.setDepartureByDepartureId(entity.getDepartureByDepartureId());
        emergencyCall.setShortDescription(entity.getShortDescription());
        emergencyCallRepository.save(emergencyCall);
    }

    @Transactional
    @Override
    public void delete(Integer integer) {
        emergencyCallRepository.deleteById(integer);
    }
}
