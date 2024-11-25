package dci.j24e01.TravelBlog.repository;


import dci.j24e01.TravelBlog.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
