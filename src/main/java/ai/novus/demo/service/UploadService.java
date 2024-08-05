package ai.novus.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void upload(String name, MultipartFile file);
}
