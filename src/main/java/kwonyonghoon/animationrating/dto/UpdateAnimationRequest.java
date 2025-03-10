package kwonyonghoon.animationrating.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateAnimationRequest {
    private String title;
    private String description;
    private String thumbnail;
}
