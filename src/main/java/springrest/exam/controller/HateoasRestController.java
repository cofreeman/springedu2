package springrest.exam.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collection;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springrest.exam.domain.ComicActor;
import springrest.exam.domain.ComicActorModel;

@RestController
public class HateoasRestController {

    @GetMapping("/all")
    public CollectionModel<ComicActorModel> allActor() {
        ComicActorModel doolyModel = new ComicActorModel("둘리");
        ComicActorModel ddochiModel = new ComicActorModel("또치");
        ComicActorModel daunerModel = new ComicActorModel("도우너");
        WebMvcLinkBuilder doolyBuilder = linkTo(methodOn(this.getClass()).getDooly());
        WebMvcLinkBuilder ddochiBuilder = linkTo(methodOn(this.getClass()).getDdochi());
        WebMvcLinkBuilder daunerBuilder = linkTo(methodOn(this.getClass()).getDauner());

        doolyModel.add(doolyBuilder.withRel("둘리"));
        ddochiModel.add(ddochiBuilder.withRel("또치"));
        daunerModel.add(daunerBuilder.withRel("도우너"));

        List<ComicActorModel> actorModels = List.of(doolyModel, ddochiModel, daunerModel);
        Collection<ComicActorModel> comicActorModels = new java.util.ArrayList<>(actorModels);

        return CollectionModel.of(comicActorModels);

    }

    @GetMapping("/dooly")
    public ResponseEntity<ComicActor> getDooly() {
        ComicActor comicActor = new ComicActor("둘리", "쌍문동", "dooly.jpg");
        return ResponseEntity.ok().body(comicActor);
    }

    @GetMapping("/ddochi")
    public ResponseEntity<ComicActor> getDdochi() {
        ComicActor comicActor = new ComicActor("또치", "아프리카", "ddochi.jpg");
        return ResponseEntity.ok().body(comicActor);
    }

    @GetMapping("/dauner")
    public ResponseEntity<ComicActor> getDauner() {
        ComicActor comicActor = new ComicActor("도우너", "깐따삐아", "dauner.png");
        return ResponseEntity.ok().body(comicActor);
    }

}
