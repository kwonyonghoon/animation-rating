package kwonyonghoon.animationrating.repository;

import kwonyonghoon.animationrating.domain.Animation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimationRepository extends JpaRepository<Animation, Long> {
}
