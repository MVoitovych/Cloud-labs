package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.DepartureController;
import ua.lviv.iot.domain.Departure;
import ua.lviv.iot.dto.DepartureDto;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartureDtoAssembler implements RepresentationModelAssembler<Departure, DepartureDto> {

    Set<Integer> getFiremanIds(Departure d) {
        Set<Integer> finalSet = new HashSet<>();
        d.getFiremans().forEach((o) -> {
            finalSet.add(o.getId());
        });
        return finalSet;
    }

    Set<Integer> getFirecarIds(Departure d) {
        Set<Integer> finalSet = new HashSet<>();
        d.getFirecars().forEach((o) -> {
            finalSet.add(o.getId());
        });
        return finalSet;
    }

    @Override
    public DepartureDto toModel(Departure entity) {
        DepartureDto departureDto = DepartureDto.builder().
                id(entity.getId())
                .shortDescription(entity.getShortDescription())
                .firecarsId(getFirecarIds(entity))
                .firemansId(getFiremanIds(entity))
                .build();

        Link selfLink = linkTo(methodOn(DepartureController.class).getDeparture(departureDto.getId())).withSelfRel();
        departureDto.add(selfLink);
        return departureDto;
    }

    @Override
    public CollectionModel<DepartureDto> toCollectionModel(Iterable<? extends Departure> entities) {
        CollectionModel<DepartureDto> departureDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DepartureController.class).getAllDepartures()).withSelfRel();
        departureDtos.add(selfLink);
        return departureDtos;
    }



}
