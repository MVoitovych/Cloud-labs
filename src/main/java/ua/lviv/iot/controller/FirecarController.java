package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Firecar;
import ua.lviv.iot.dto.FirecarDto;
import ua.lviv.iot.dto.assembler.FirecarDtoAssembler;
import ua.lviv.iot.service.FirecarService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/firecars")
public class FirecarController {

    @Autowired
    private FirecarDtoAssembler firecarDtoAssembler;

    @Autowired
    private FirecarService firecarService;

    @GetMapping(value = "/{firecarId}")
    public ResponseEntity<FirecarDto> getFirecar(@PathVariable Integer firecarId) {
        Firecar firecar = firecarService.findById(firecarId);
        FirecarDto firecarDto = firecarDtoAssembler.toModel(firecar);
        return new ResponseEntity<>(firecarDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<FirecarDto>> getAllFirecars() {
        List<Firecar> firecars = firecarService.findAll();
        CollectionModel<FirecarDto> firecarDtos = firecarDtoAssembler.toCollectionModel(firecars);
        return new ResponseEntity<>(firecarDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<FirecarDto> addFirecar(@RequestBody Firecar firecar) {
        Firecar newFirecar = firecarService.create(firecar);
        FirecarDto firecarDto = firecarDtoAssembler.toModel(newFirecar);
        return new ResponseEntity<>(firecarDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{firecarId}")
    public ResponseEntity<?> updateFirecar(@RequestBody Firecar uFirecar, @PathVariable Integer firecarId) {
        firecarService.update(firecarId, uFirecar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{firecarId}")
    public ResponseEntity<?> deleteFirecar(@PathVariable Integer firecarId) {
        firecarService.delete(firecarId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
