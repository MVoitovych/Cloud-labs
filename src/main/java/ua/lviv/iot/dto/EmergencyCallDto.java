package ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import ua.lviv.iot.domain.Departure;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "call", collectionRelation = "calls")
public class EmergencyCallDto extends RepresentationModel<EmergencyCallDto> {
    private final Integer id;
    private final String shortDescription;
    private final int departureByDepartureId;

}
