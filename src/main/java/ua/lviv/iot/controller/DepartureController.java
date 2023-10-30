package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Departure;
import ua.lviv.iot.domain.Firecar;
import ua.lviv.iot.domain.Fireman;
import ua.lviv.iot.dto.DepartureDto;
import ua.lviv.iot.dto.assembler.DepartureDtoAssembler;
import ua.lviv.iot.service.DepartureService;
import ua.lviv.iot.service.FirecarService;
import ua.lviv.iot.service.FiremanService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/departures")
public class DepartureController {
    @Autowired
    private DepartureDtoAssembler departureDtoAssembler;
    @Autowired
    private FirecarService firecarService;
    @Autowired
    private FiremanService firemanService;
    @Autowired
    private DepartureService departureService;

    @GetMapping(value = "/{departureId}")
    public ResponseEntity<DepartureDto> getDeparture(@PathVariable Integer departureId) {
        Departure departure = departureService.findById(departureId);
        DepartureDto departureDto = departureDtoAssembler.toModel(departure);
        return new ResponseEntity<>(departureDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<DepartureDto>> getAllDepartures() {
        List<Departure> departures = departureService.findAll();
        CollectionModel<DepartureDto> departureDtos = departureDtoAssembler.toCollectionModel(departures);
        return new ResponseEntity<>(departureDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<DepartureDto> addDeparture(@RequestBody DepartureDto uDeparture) {
        Departure departure = new Departure();

        Set<Firecar> firecarSet = new HashSet<>();
        Set<Fireman> firemanSet = new HashSet<>();

        uDeparture.getFirecarsId().forEach(firecarId -> firecarSet.add(firecarService.findById(firecarId)));
        uDeparture.getFiremansId().forEach(firemanId -> firemanSet.add(firemanService.findById(firemanId)));

        departure.setShortDescription(uDeparture.getShortDescription());
        departure.setFirecars(firecarSet);
        departure.setFiremans(firemanSet);

        Departure newDeparture = departureService.create(departure);
        DepartureDto departureDto = departureDtoAssembler.toModel(newDeparture);
        return new ResponseEntity<>(departureDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{departureId}")
    public ResponseEntity<?> updateDeparture(@RequestBody DepartureDto uDeparture, @PathVariable Integer departureId) {

        Departure departure = new Departure();

        Set<Firecar> firecarSet = new HashSet<>();
        Set<Fireman> firemanSet = new HashSet<>();

        uDeparture.getFirecarsId().forEach(firecarId -> firecarSet.add(firecarService.findById(firecarId)));
        uDeparture.getFiremansId().forEach(firemanId -> firemanSet.add(firemanService.findById(firemanId)));

        departure.setShortDescription(uDeparture.getShortDescription());
        departure.setFirecars(firecarSet);
        departure.setFiremans(firemanSet);
        departureService.update(departureId, departure);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{departureId}")
    public ResponseEntity<?> deleteDeparture(@PathVariable Integer departureId) {
        departureService.delete(departureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
