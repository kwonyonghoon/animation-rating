package kwonyonghoon.animationrating.dto;

import kwonyonghoon.animationrating.domain.Animation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class AnimationViewResponse {

    private Long id;
    private String title;
    private String description;
    private String thumbnail;
    private LocalDateTime createdAt;

    public AnimationViewResponse(Animation animation){
        this.id = animation.getId();
        this.title = animation.getTitle();
        this.description = animation.getDescription();
        this.thumbnail = animation.getThumbnail();
        this.createdAt = animation.getCreatedAt();
    }
}
