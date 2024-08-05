package ai.novus.demo.service;

import ai.novus.demo.entitiy.Cat;
import ai.novus.demo.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final MinioServiceImpl minioService;

    private final CatRepository catRepository;

    public void upload(String name, MultipartFile file){
        try {
            String fileName = minioService.uploadFile(file);
            Cat cat = new Cat();
            cat.setName(name);
            cat.setImageFileName(fileName);
            catRepository.save(cat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
