package ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import ua.lviv.iot.domain.Firecar;
import ua.lviv.iot.domain.Fireman;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "departure", collectionRelation = "departures")
public class DepartureDto extends RepresentationModel<DepartureDto> {

    private final Integer id;
    private final String shortDescription;
    private final Set<Integer> firemansId;
    private final Set<Integer> firecarsId;
}
