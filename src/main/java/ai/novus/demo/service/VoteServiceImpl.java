package ai.novus.demo.service;

import ai.novus.demo.dto.response.VoteCatResponse;
import ai.novus.demo.entitiy.Cat;
import ai.novus.demo.entitiy.Vote;
import ai.novus.demo.mapper.CatMapper;
import ai.novus.demo.repository.CatRepository;
import ai.novus.demo.repository.VoteRepository;
import ai.novus.demo.session.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final CatRepository catRepository;

    private final UserSession userSession;

    private final CatMapper mapper;

    public void vote(Long catId, String userId) {
        if (!voteRepository.existsByCatIdAndUserId(catId, userId)) {
            Vote vote = new Vote();
            vote.setCat(catRepository.getReferenceById(catId));
            vote.setUserId(userId);
            voteRepository.save(vote);
        }
    }

    public Pair<VoteCatResponse, VoteCatResponse> getPare() {
        Pair<Cat, Cat> pair = userSession.getNextPair();

        if (pair == null) {
            List<Cat> cats = catRepository.findAll();
            userSession.initializePairs(cats);
        }

        Pair<VoteCatResponse,VoteCatResponse> pairdto = null;
        if(pair != null){
            pairdto = mapper.toPairVoteCatResponse(pair);
        }

        return pairdto;
    }

}