package ua.lviv.iot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.EmergencyCall;
import ua.lviv.iot.dto.EmergencyCallDto;
import ua.lviv.iot.dto.assembler.EmergencyCallDtoAssembler;
import ua.lviv.iot.service.CallService;
import ua.lviv.iot.service.DepartureService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/calls")
public class EmergencyCallController {

    @Autowired
    private EmergencyCallDtoAssembler emergencyCallDtoAssembler;

    @Autowired
    private DepartureService departureService;

    @Autowired
    private CallService callService;

    @GetMapping(value = "/{callId}")
    public ResponseEntity<EmergencyCallDto> getCall(@PathVariable Integer callId) {
        EmergencyCall emergencyCall = callService.findById(callId);
        EmergencyCallDto emergencyCallDto = emergencyCallDtoAssembler.toModel(emergencyCall);
        return new ResponseEntity<>(emergencyCallDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EmergencyCallDto>> getAllCalls() {
        List<EmergencyCall> emergencyCalls = callService.findAll();
        CollectionModel<EmergencyCallDto> callDtos = emergencyCallDtoAssembler.toCollectionModel(emergencyCalls);
        return new ResponseEntity<>(callDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EmergencyCallDto> addCall(@RequestBody EmergencyCallDto uEmergencyCall) {
        EmergencyCall emergencyCall = new EmergencyCall();
        emergencyCall.setShortDescription(uEmergencyCall.getShortDescription());
        emergencyCall.setDepartureByDepartureId(departureService.findById(uEmergencyCall.getDepartureByDepartureId()));

        EmergencyCall newEmergencyCall = callService.create(emergencyCall);
        EmergencyCallDto emergencyCallDto = emergencyCallDtoAssembler.toModel(newEmergencyCall);
        return new ResponseEntity<>(emergencyCallDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{callId}")
    public ResponseEntity<?> updateCall(@RequestBody EmergencyCallDto uEmergencyCall, @PathVariable Integer callId) {
        EmergencyCall emergencyCall = new EmergencyCall();

        emergencyCall.setShortDescription(uEmergencyCall.getShortDescription());
        emergencyCall.setDepartureByDepartureId(departureService.findById(uEmergencyCall.getDepartureByDepartureId()));

        callService.update(callId, emergencyCall);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{callId}")
    public ResponseEntity<?> deleteCall(@PathVariable Integer callId) {
        callService.delete(callId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
