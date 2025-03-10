package kwonyonghoon.animationrating.service;

import kwonyonghoon.animationrating.domain.Animation;
import kwonyonghoon.animationrating.dto.AddAnimationRequest;
import kwonyonghoon.animationrating.repository.AnimationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final, @NotNull이 있는 필드의 생성자
@Service
public class AnimationService {

    private final AnimationRepository animationRepository;

    // 애니메이션 등록 메서드
    public Animation save(AddAnimationRequest request){
        return animationRepository.save(request.toEntity());
    }
}
