package ai.novus.demo.init;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class MinioInitializer {

    private final MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${images.path}")
    private String imagesPath;

    private final String FILE_TYPE = "image/webp";

    public MinioInitializer(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            try {
                boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
                if (!isExist) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                }

                List<String> images = Arrays.asList("image1.jpg", "image2.jpg", "image3.jpg", "image4.jpg", "image5.jpg");

                for (String imageName : images) {
                    File file = new File(imagesPath + File.separator + imageName);
                    try (InputStream is = new FileInputStream(file)) {
                        minioClient.putObject(PutObjectArgs.builder()
                                .bucket(bucketName)
                                .contentType(FILE_TYPE)
                                .object(file.getName())
                                .stream(is, is.available(), -1)
                                .build());
                        System.out.println("Uploaded: " + imageName);
                    } catch (Exception e) {
                        System.err.println("Error uploading " + imageName + ": " + e.getMessage());
                    }
                }

            } catch (MinioException e) {
                System.err.println("Error occurred: " + e.getMessage());
            }
        };
    }
}

