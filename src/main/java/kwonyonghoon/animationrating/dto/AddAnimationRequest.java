package kwonyonghoon.animationrating.dto;

import kwonyonghoon.animationrating.domain.Animation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자
@Getter
public class AddAnimationRequest {

    private String title;

    private String description;

    private String thumbnail;

    public Animation toEntity(){
        return Animation.builder()
                .title(title)
                .description(description)
                .thumbnail(thumbnail)
                .build();
    }
}
