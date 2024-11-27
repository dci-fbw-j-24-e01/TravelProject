package dci.j24e01.TravelBlog.repository;


import dci.j24e01.TravelBlog.model.VacationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationPointRepository extends JpaRepository<VacationPoint, Long> {
    List<VacationPoint> findAllByApprovedTrue();
}