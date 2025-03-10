package kwonyonghoon.animationrating.Controller;

import kwonyonghoon.animationrating.domain.Animation;
import kwonyonghoon.animationrating.dto.AddAnimationRequest;
import kwonyonghoon.animationrating.service.AnimationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // 데이터를 JSON 형식으로 반환하는 컨트롤러
public class AnimationApiController {

    private final AnimationService animationService;

    @PostMapping("/api/animations")
    public ResponseEntity<Animation> addAnimation(@RequestBody AddAnimationRequest request){
        Animation savedAnimation = animationService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAnimation);
    }
}
