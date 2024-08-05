package ai.novus.demo.entitiy;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Cat cat;

    private String userId;
}
