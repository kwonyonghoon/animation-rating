package kwonyonghoon.animationrating.service;

import jakarta.transaction.Transactional;
import kwonyonghoon.animationrating.domain.Animation;
import kwonyonghoon.animationrating.dto.AddAnimationRequest;
import kwonyonghoon.animationrating.dto.UpdateAnimationRequest;
import kwonyonghoon.animationrating.repository.AnimationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final, @NotNull이 있는 필드의 생성자
@Service
public class AnimationService {

    private final AnimationRepository animationRepository;

    // 애니메이션 등록 메서드
    public Animation save(AddAnimationRequest request){
        return animationRepository.save(request.toEntity());
    }

    // 모든 애니메이션 조회 메서드
    public List<Animation> findAll(){
        return animationRepository.findAll();
    }

    // 애니메이션 조회 메서드
    public Animation findById(Long id){
        return animationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 애니메이션 삭제 메서드
    public void delete(Long id){
        animationRepository.deleteById(id);
    }

    // 트랜잭션 메서드
    @Transactional
    public Animation Update(long id, UpdateAnimationRequest request){
        Animation animation = animationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        animation.Update(request.getTitle(), request.getDescription(), request.getThumbnail());

        return animation;
    }
}
