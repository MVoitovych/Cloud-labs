package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.FirecarController;
import ua.lviv.iot.domain.Firecar;
import ua.lviv.iot.dto.FirecarDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FirecarDtoAssembler implements RepresentationModelAssembler<Firecar, FirecarDto> {

    @Override
    public FirecarDto toModel(Firecar entity) {
        FirecarDto firecarDto = FirecarDto.builder().
                id(entity.getId())
                .mark(entity.getMark())
                .build();

        Link selfLink = linkTo(methodOn(FirecarController.class).getFirecar(firecarDto.getId())).withSelfRel();
        firecarDto.add(selfLink);
        return firecarDto;
    }

    @Override
    public CollectionModel<FirecarDto> toCollectionModel(Iterable<? extends Firecar> entities) {
        CollectionModel<FirecarDto> firecarDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(FirecarController.class).getAllFirecars()).withSelfRel();
        firecarDtos.add(selfLink);
        return firecarDtos;
    }
}
