package kwonyonghoon.animationrating.controller;

import kwonyonghoon.animationrating.domain.Animation;
import kwonyonghoon.animationrating.dto.AnimationViewResponse;
import org.springframework.ui.Model;
import kwonyonghoon.animationrating.dto.AnimationListViewResponse;
import kwonyonghoon.animationrating.service.AnimationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AnimationViewController {

    private final AnimationService animationService;

    @GetMapping("/animations")
    public String getAnimations(Model model) {
        List<AnimationListViewResponse> animations = animationService.findAll().stream()
                .map(AnimationListViewResponse::new)
                .toList();
        model.addAttribute("animations", animations);

        return "animationList";
    }

    @GetMapping("/animations/{id}")
    public String getAnimation(@PathVariable Long id, Model model){
        Animation animation = animationService.findById(id);
        model.addAttribute("animation", new AnimationViewResponse(animation));

        return "animation";
    }

    @GetMapping("new-animation")
    // id 키를 가진 쿼리 파라미터의 값을 매핑
    public String newAnimation(@RequestParam(required = false) Long id, Model model){
        if(id==null){ // id가 없으면 생성
            model.addAttribute("animation", new AnimationViewResponse());
        } else{ // id가 있으면 수정
            Animation animation = animationService.findById(id);
            model.addAttribute("animation", new AnimationViewResponse(animation));
        }

        return "newAnimation";
    }
}
