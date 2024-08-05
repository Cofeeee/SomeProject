package ai.novus.demo.entitiy;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String imageFileName;

    @OneToMany(mappedBy = "cat")
    private List<Vote> votes;
}
