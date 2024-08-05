package ai.novus.demo.repository;


import ai.novus.demo.entitiy.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    long countByUserId(String userId);
    boolean existsByCatIdAndUserId(Long catId, String userId);
}
