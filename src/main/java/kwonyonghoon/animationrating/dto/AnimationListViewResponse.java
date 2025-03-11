package kwonyonghoon.animationrating.dto;

import kwonyonghoon.animationrating.domain.Animation;
import lombok.Getter;

@Getter
public class AnimationListViewResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final String thumbnail;

    public AnimationListViewResponse(Animation animation) {
        this.id = animation.getId();
        this.title = animation.getTitle();
        this.description = animation.getDescription();
        this.thumbnail = animation.getThumbnail();
    }
}
