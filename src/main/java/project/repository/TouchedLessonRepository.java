package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.TouchedLesson;

public interface TouchedLessonRepository extends JpaRepository<TouchedLesson, Integer> {

}
