package ai.novus.demo.service;

import ai.novus.demo.dto.response.VoteCatResponse;
import org.springframework.data.util.Pair;

public interface VoteService {
    void vote(Long catId, String userId);

    Pair<VoteCatResponse, VoteCatResponse> getPare();
}
