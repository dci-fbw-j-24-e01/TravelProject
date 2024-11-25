package dci.j24e01.TravelBlog.repositories;

import dci.j24e01.TravelBlog.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
