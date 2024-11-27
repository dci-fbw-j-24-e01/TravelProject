package dci.j24e01.TravelBlog.repository;

import dci.j24e01.TravelBlog.model.BlogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogEntryRepository extends JpaRepository<BlogEntry, Long> {
}
