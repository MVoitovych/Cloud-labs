package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.FiremanController;
import ua.lviv.iot.domain.Fireman;
import ua.lviv.iot.dto.FiremanDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FiremanDtoAssembler implements RepresentationModelAssembler<Fireman, FiremanDto> {

    @Override
    public FiremanDto toModel(Fireman entity) {
        FiremanDto firemanDto = FiremanDto.builder().
                id(entity.getId())
                .name(entity.getSurname())
                .surname(entity.getSurname()).
                build();


        Link selfLink = linkTo(methodOn(FiremanController.class).getFireman(firemanDto.getId())).withSelfRel();
        firemanDto.add(selfLink);
        return firemanDto;
    }

    @Override
    public CollectionModel<FiremanDto> toCollectionModel(Iterable<? extends Fireman> entities) {
        CollectionModel<FiremanDto> firemanDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(FiremanController.class).getAllFiremans()).withSelfRel();
        firemanDtos.add(selfLink);
        return firemanDtos;
    }
}
