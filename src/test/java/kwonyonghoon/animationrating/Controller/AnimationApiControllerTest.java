package kwonyonghoon.animationrating.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kwonyonghoon.animationrating.domain.Animation;
import kwonyonghoon.animationrating.dto.AddAnimationRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("addAnimation: 블로그 글 추가에 성공한다.")
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
}