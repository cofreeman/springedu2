package springrest.exam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue
    private Integer id;
    private String fname;
    private Integer fage;

    @Builder
    public Friend(Integer id, String fname, Integer fage) {
        this.id = id;
        this.fname = fname;
        this.fage = fage;
    }
}

