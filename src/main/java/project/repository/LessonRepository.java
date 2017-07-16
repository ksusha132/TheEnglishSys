package project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
