package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}