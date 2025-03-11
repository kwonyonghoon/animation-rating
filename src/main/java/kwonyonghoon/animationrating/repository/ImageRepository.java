package kwonyonghoon.animationrating.repository;

import kwonyonghoon.animationrating.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

