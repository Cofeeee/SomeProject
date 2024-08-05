package ai.novus.demo.controller;

import ai.novus.demo.dto.response.VoteCatResponse;
import ai.novus.demo.entitiy.Cat;
import ai.novus.demo.service.VoteService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class VoteController {

    private final VoteService service;

    @GetMapping("/vote")
    @ResponseStatus(HttpStatus.OK)
    public String vote(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            userId = UUID.randomUUID().toString();
            session.setAttribute("userId", userId);
        }

        Pair<VoteCatResponse,VoteCatResponse> pair = service.getPare();

        if (pair == null) {
            return "redirect:/result";
        }

        model.addAttribute("cat1", pair.getFirst());
        model.addAttribute("cat2", pair.getSecond());
        model.addAttribute("userId", userId);
        return "vote";
    }

    @PostMapping("/vote")
    @ResponseStatus(HttpStatus.OK)
    public String vote(@RequestParam Long catId, @RequestParam String userId) {
        service.vote(catId, userId);

        return "redirect:/vote?userId=" + userId;
    }


}
