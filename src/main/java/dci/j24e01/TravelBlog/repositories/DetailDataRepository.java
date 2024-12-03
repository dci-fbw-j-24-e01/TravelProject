package dci.j24e01.TravelBlog.repositories;

import dci.j24e01.TravelBlog.models.DetailData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailDataRepository extends JpaRepository<DetailData, Long> {


}
