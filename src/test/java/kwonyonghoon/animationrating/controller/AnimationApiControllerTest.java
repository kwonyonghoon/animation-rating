package kwonyonghoon.animationrating.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kwonyonghoon.animationrating.domain.Animation;
import kwonyonghoon.animationrating.dto.AddAnimationRequest;
import kwonyonghoon.animationrating.dto.UpdateAnimationRequest;
import kwonyonghoon.animationrating.repository.AnimationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AnimationApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    AnimationRepository animationRepository;

    // 테스트 실행 전 마다 실행되는 메서드
    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        animationRepository.deleteAll();
    }

    @DisplayName("addAnimation: 애니메이션 추가에 성공한다.")
    @Test
    public void addAnimation() throws Exception {
        // given
        final String url = "/api/animations";
        final String title = "안녕하세요";
        final String description = "반갑습니다";
        final String thumbnail = "https://www.google.com/imgres?q=%EC%9D%B4%EB%AF%B8%EC%A7%80%20url&imgurl=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fk8gCF%2FbtqHiOcJh6F%2FvCBjNW3CzQt1i5OM1Z76xk%2Fimg.png&imgrefurl=https%3A%2F%2Fxofks136.tistory.com%2F15&docid=hjwOBjNAB3q9AM&tbnid=ddOhEM6-CAQTeM&vet=12ahUKEwjw94Kqpv-LAxU8i68BHfnVOP4QM3oECFoQAA..i&w=1005&h=392&hcb=2&ved=2ahUKEwjw94Kqpv-LAxU8i68BHfnVOP4QM3oECFoQAA";
        final AddAnimationRequest userRequest = new AddAnimationRequest(title, description, thumbnail);

        // 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        // 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Animation> animations = animationRepository.findAll();

        // 크기가 1인지 검증
        assertThat(animations.size()).isEqualTo(1);
        assertThat(animations.get(0).getTitle()).isEqualTo(title);
        assertThat(animations.get(0).getDescription()).isEqualTo(description);
        assertThat(animations.get(0).getThumbnail()).isEqualTo(thumbnail);
    }

    @DisplayName("findAllAnimations: 애니메이션 목록 조회에 성공한다.")
    @Test
    public void findAllAnimations() throws Exception {
        // given
        final String url = "/api/animations";
        final String title = "title";
        final String description = "description";
        final String thumbnail = "thumbnail";

        animationRepository.save(Animation.builder()
                .title(title)
                .description(description)
                .thumbnail(thumbnail)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value(description))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @DisplayName("findAnimation: 애니메이션 조회에 성공한다.")
    @Test
    public void findAnimation() throws Exception {
        // given
        final String url = "/api/animations/{id}";
        final String title = "title";
        final String description = "description";
        final String thumbnail = "thumbnail";

        Animation savedAnimation = animationRepository.save(Animation.builder()
                .title(title)
                .description(description)
                .thumbnail(thumbnail)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedAnimation.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.thumbnail").value(thumbnail));
    }

    @DisplayName("deleteAnimation: 애니메이션 삭제에 성공한다.")
    @Test
    public void deleteAnimation() throws Exception{
        // given
        final String url = "/api/animations/{id}";
        final String title = "title";
        final String description = "description";
        final String thumbnail = "thumbnail";

        Animation savedAnimation = animationRepository.save(Animation.builder()
                .title(title)
                .description(description)
                .thumbnail(thumbnail)
                .build());

        // when
        mockMvc.perform(delete(url, savedAnimation.getId()))
                .andExpect(status().isOk());

        // then
        List<Animation> animations = animationRepository.findAll();

        assertThat(animations).isEmpty();
    }

    @DisplayName("updateAnimation: 애니메이션 수정에 성공한다.")
    @Test
    public void updateAnimation() throws Exception {
        // given
        final String url = "/api/animations/{id}";
        final String title = "title";
        final String description = "description";
        final String thumbnail = "thumbnail";

        Animation savedAnimation = animationRepository.save(Animation.builder()
                .title(title)
                .description(description)
                .thumbnail(thumbnail)
                .build());

        final String newTitle = "newTitle";
        final String newDescription = "newDescription";
        final String newThumbnail = "newThumbnail";

        UpdateAnimationRequest request = new UpdateAnimationRequest(newTitle, newDescription, newThumbnail);

        // when
        ResultActions result = mockMvc.perform(put(url, savedAnimation.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isOk());

        Animation animation = animationRepository.findById(savedAnimation.getId()).get();

        assertThat(animation.getTitle()).isEqualTo(newTitle);
        assertThat(animation.getDescription()).isEqualTo(newDescription);
        assertThat(animation.getThumbnail()).isEqualTo(newThumbnail);
    }
}