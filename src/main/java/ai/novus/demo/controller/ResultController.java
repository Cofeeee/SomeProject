package ai.novus.demo.controller;

import ai.novus.demo.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class ResultController {

    private final ResultService service;

    @GetMapping("/result")
    public String showResultsPage(Model model) {
        model.addAttribute("cats", service.getResult());
        return "result";
    }
}
