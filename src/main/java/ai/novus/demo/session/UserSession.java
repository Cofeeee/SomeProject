package ai.novus.demo.session;

import ai.novus.demo.entitiy.Cat;
import ai.novus.demo.util.PairUtil;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.UUID;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class UserSession {
    private final String userId;
    private List<Pair<Cat, Cat>> remainingPairs;

    public UserSession() {
        this.userId = UUID.randomUUID().toString();
    }

    public void initializePairs(List<Cat> cats) {
        if (remainingPairs == null) {
            remainingPairs = PairUtil.generateUniquePairs(cats);
        }
    }

    public Pair<Cat, Cat> getNextPair() {
        if (remainingPairs == null || remainingPairs.isEmpty()) {
            return null;
        }
        return remainingPairs.remove(0);
    }
}
