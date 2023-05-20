package springrest.exam.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@Setter
@AllArgsConstructor
public class ComicActorModel extends RepresentationModel<ComicActorModel> {

    private String name;

}
