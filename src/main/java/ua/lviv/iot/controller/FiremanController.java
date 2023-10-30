package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Fireman;
import ua.lviv.iot.dto.FiremanDto;
import ua.lviv.iot.dto.assembler.FiremanDtoAssembler;
import ua.lviv.iot.service.FiremanService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/firemans")
public class FiremanController {

    @Autowired
    private FiremanDtoAssembler firemanDtoAssembler;

    @Autowired
    private FiremanService firemanService;

    @GetMapping(value = "/{firemanId}")
    public ResponseEntity<FiremanDto> getFireman(@PathVariable Integer firemanId) {
        Fireman fireman = firemanService.findById(firemanId);
        FiremanDto firemanDto = firemanDtoAssembler.toModel(fireman);
        return new ResponseEntity<>(firemanDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<FiremanDto>> getAllFiremans() {
        List<Fireman> firemans = firemanService.findAll();
        CollectionModel<FiremanDto> firemanDtos = firemanDtoAssembler.toCollectionModel(firemans);
        return new ResponseEntity<>(firemanDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<FiremanDto> addFireman(@RequestBody Fireman fireman) {
        Fireman newFireman = firemanService.create(fireman);
        FiremanDto firemanDto = firemanDtoAssembler.toModel(newFireman);
        return new ResponseEntity<>(firemanDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{firemanId}")
    public ResponseEntity<?> updateFireman(@RequestBody Fireman uFireman, @PathVariable Integer firemanId) {
        firemanService.update(firemanId, uFireman);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{firemanId}")
    public ResponseEntity<?> deleteFireman(@PathVariable Integer firemanId) {
        firemanService.delete(firemanId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}