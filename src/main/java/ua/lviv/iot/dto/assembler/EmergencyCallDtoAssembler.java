package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.EmergencyCallController;
import ua.lviv.iot.domain.EmergencyCall;
import ua.lviv.iot.dto.EmergencyCallDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmergencyCallDtoAssembler implements RepresentationModelAssembler<EmergencyCall, EmergencyCallDto> {
    @Override
    public EmergencyCallDto toModel(EmergencyCall entity) {
        EmergencyCallDto emergencyCallDto = EmergencyCallDto.builder().
                id(entity.getId())
                .shortDescription(entity.getShortDescription())
                .departureByDepartureId(entity.getDepartureByDepartureId().getId())
                .build();

        Link selfLink = linkTo(methodOn(EmergencyCallController.class).getCall(emergencyCallDto.getId())).withSelfRel();
        emergencyCallDto.add(selfLink);
        return emergencyCallDto;
    }

    @Override
    public CollectionModel<EmergencyCallDto> toCollectionModel(Iterable<? extends EmergencyCall> entities) {
        CollectionModel<EmergencyCallDto> callDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(EmergencyCallController.class).getAllCalls()).withSelfRel();
        callDtos.add(selfLink);
        return callDtos;
    }
}
