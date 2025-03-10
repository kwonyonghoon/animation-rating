package kwonyonghoon.animationrating.dto;

import kwonyonghoon.animationrating.domain.Animation;
import lombok.Getter;

@Getter
public class AnimationResponse {

    private final String title;
    private final String description;
    private final String thumbnail;

    public AnimationResponse(Animation animation) {
        this.title = animation.getTitle();
        this.description = animation.getDescription();
        this.thumbnail = animation.getThumbnail();
    }
}
