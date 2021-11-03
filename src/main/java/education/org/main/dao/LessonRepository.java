package education.org.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import education.org.main.entities.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
