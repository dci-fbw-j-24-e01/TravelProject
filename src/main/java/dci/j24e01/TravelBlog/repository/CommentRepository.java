package dci.j24e01.TravelBlog.repository;

import dci.j24e01.TravelBlog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
