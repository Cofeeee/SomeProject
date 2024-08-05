package ai.novus.demo.mapper;

import ai.novus.demo.dto.response.ResultResponse;
import ai.novus.demo.dto.response.VoteCatResponse;
import ai.novus.demo.entitiy.Cat;
import ai.novus.demo.service.MinioServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CatMapper {

    @Autowired
    private MinioServiceImpl minioService;

    @Mapping(source = "imageFileName", target = "imageUrl", qualifiedByName = "generateImageUrl")
    public abstract VoteCatResponse toVoteCatResponse(Cat cat);

    @Mapping(source = "imageFileName", target = "imageUrl", qualifiedByName = "generateImageUrl")
    @Mapping(expression = "java(cat.getVotes().size())", target = "voteCount")
    public abstract ResultResponse toResultResponse(Cat cat);

    public List<ResultResponse> toResultResponseList(List<Cat> cats) {
        return cats.stream()
                .map(this::toResultResponse)
                .toList();
    }

    public Pair<VoteCatResponse,VoteCatResponse> toPairVoteCatResponse(Pair<Cat,Cat> catPair){
        return Pair.of(toVoteCatResponse(catPair.getFirst()),toVoteCatResponse(catPair.getSecond()));
    }

    @Named("generateImageUrl")
    protected String generateImageUrl(String imageFileName) {
        return minioService.getFileUrl(imageFileName);
    }
}
