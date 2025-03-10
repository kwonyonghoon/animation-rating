package kwonyonghoon.animationrating.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animation {

    // 게터
    // 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // 애니메이션 제목
    @Column(name = "title", nullable = false)
    private String title;

    // 줄거리
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // 대표 사진
    @Column(columnDefinition = "TEXT")
    private String thumbnail;

    // 객체 생성
    @Builder
    public Animation(String title, String description, String thumbnail) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}
