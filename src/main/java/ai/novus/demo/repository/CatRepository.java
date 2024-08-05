package ai.novus.demo.repository;

import ai.novus.demo.entitiy.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {

    @Query("SELECT c FROM Cat c LEFT JOIN FETCH c.votes")
    List<Cat> findAllWithVotes();
}
