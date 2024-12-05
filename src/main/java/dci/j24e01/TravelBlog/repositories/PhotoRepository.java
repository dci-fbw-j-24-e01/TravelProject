package dci.j24e01.TravelBlog.repositories;


import dci.j24e01.TravelBlog.models.Photo;
import dci.j24e01.TravelBlog.models.VacationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    void deleteAllByVacationPoint(VacationPoint savedPoint);
    void deleteAllByVacationPointId(Long id);
}
