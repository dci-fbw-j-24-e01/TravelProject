package dci.j24e01.TravelBlog.repository;

import dci.j24e01.TravelBlog.model.PendingLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingLocationRepository extends JpaRepository<PendingLocation, Integer> {

}
