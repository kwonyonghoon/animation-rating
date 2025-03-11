package kwonyonghoon.animationrating.dto;

import kwonyonghoon.animationrating.domain.Image;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CoverUploadDto {

    private MultipartFile file;
    private String title;

    public Image toEntity(String coverImageUrl){
        return Image.builder()
                .coverImageUrl(coverImageUrl)
                .title(title)
                .build();
    }

}
