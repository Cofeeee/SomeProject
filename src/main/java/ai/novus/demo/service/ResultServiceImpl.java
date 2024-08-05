package ai.novus.demo.service;

import ai.novus.demo.dto.response.ResultResponse;
import ai.novus.demo.entitiy.Cat;
import ai.novus.demo.mapper.CatMapper;
import ai.novus.demo.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final CatRepository catRepository;

    private final CatMapper catMapper;

    public List<ResultResponse> getResult(){
        List<Cat> cats = catRepository.findAllWithVotes();
        cats.sort((c1, c2) -> Long.compare(c2.getVotes().size(), c1.getVotes().size()));

        return catMapper.toResultResponseList(cats);
    }
}
