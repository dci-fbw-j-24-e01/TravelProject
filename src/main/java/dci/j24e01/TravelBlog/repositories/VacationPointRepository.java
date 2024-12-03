package dci.j24e01.TravelBlog.repositories;


import dci.j24e01.TravelBlog.models.VacationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VacationPointRepository extends JpaRepository<VacationPoint, Long> {
    List<VacationPoint> findAllByApprovedTrue();


}