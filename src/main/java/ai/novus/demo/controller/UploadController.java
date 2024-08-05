package ai.novus.demo.controller;

import ai.novus.demo.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final UploadService service;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public String uploadCat(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        service.upload(name,file);
        return "redirect:/result";
    }
}
