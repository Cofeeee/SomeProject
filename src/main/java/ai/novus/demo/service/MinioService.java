package ai.novus.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MinioService {

    String uploadFile(MultipartFile file) throws IOException;

    String getFileUrl(String fileName);
}
