package dci.j24e01.TravelBlog.repositories;

import dci.j24e01.TravelBlog.models.PendingLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingLocationRepository extends JpaRepository<PendingLocation, Long> {

}