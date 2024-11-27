package dci.j24e01.TravelBlog.repository;

import dci.j24e01.TravelBlog.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByBlogEntryId(Long blogEntryId);
}
