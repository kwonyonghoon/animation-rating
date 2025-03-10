package kwonyonghoon.animationrating.controller;

import kwonyonghoon.animationrating.domain.Animation;
import kwonyonghoon.animationrating.dto.AddAnimationRequest;
import kwonyonghoon.animationrating.dto.AnimationResponse;
import kwonyonghoon.animationrating.dto.UpdateAnimationRequest;
import kwonyonghoon.animationrating.service.AnimationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/animations")
    public ResponseEntity<List<AnimationResponse>> findAllAnimations(){
        List<AnimationResponse> animations = animationService.findAll()
                .stream()
                .map(AnimationResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(animations);
    }

    @GetMapping("/api/animations/{id}")
    // URL 경로에서 값 추출
    public ResponseEntity<AnimationResponse> findAnimation(@PathVariable long id){
        Animation animation = animationService.findById(id);

        return ResponseEntity.ok()
                .body(new AnimationResponse(animation));
    }

    @DeleteMapping("/api/animations/{id}")
    public ResponseEntity<Void> deleteAnimation(@PathVariable long id){
        animationService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/animations/{id}")
    public ResponseEntity<Animation> updateAnimation(@PathVariable long id,
                                                     @RequestBody UpdateAnimationRequest request){
        Animation updatedAnimation = animationService.Update(id, request);

        return ResponseEntity.ok()
                .body(updatedAnimation);
    }
}
