package ai.novus.demo.util;

import ai.novus.demo.entitiy.Cat;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PairUtil {

    public static List<Pair<Cat, Cat>> generateUniquePairs(List<Cat> cats) {
        List<Pair<Cat, Cat>> pairs = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            for (int j = i + 1; j < cats.size(); j++) {
                pairs.add(Pair.of(cats.get(i), cats.get(j)));
            }
        }
        Collections.shuffle(pairs);
        return pairs;
    }
}
