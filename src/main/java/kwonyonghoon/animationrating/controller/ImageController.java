package kwonyonghoon.animationrating.controller;

import kwonyonghoon.animationrating.dto.CoverUploadDto;
import kwonyonghoon.animationrating.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> UploadImage(@ModelAttribute CoverUploadDto coverUploadDto){
        if(coverUploadDto.getFile().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미지가 첨부되지 않았습니다");
        }

        imageService.UploadImage(coverUploadDto);
        return ResponseEntity.ok("이미지 업로드 성공");
    }
}
