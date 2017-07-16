package project.services;

import java.util.List;

import project.dto.LessonDTO;
import project.model.Lesson;

public interface LessonService {
	List<Lesson> getLessonsBySectionAndLevel(Integer section, Integer level);
	LessonDTO getLessonDTO(Integer id);
	String addComment(Integer idLesson, String commentBody, String commentTitle);
}
