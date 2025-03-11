package kwonyonghoon.animationrating.service;

import jakarta.transaction.Transactional;
import kwonyonghoon.animationrating.domain.Image;
import kwonyonghoon.animationrating.dto.CoverUploadDto;
import kwonyonghoon.animationrating.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("$file.path")
    private String uploadFolder;

    @Transactional
    public void UploadImage(CoverUploadDto coverUploadDto) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + coverUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        try{
            Files.write(imageFilePath, coverUploadDto.getFile().getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }

        Image image = coverUploadDto.toEntity(imageFileName);
        imageRepository.save(image);
    }
}
