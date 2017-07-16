package project.tests.service;

import java.util.List;

import project.tests.models.Lesson;
import project.tests.models.Question;

public interface TestService {
	List<Lesson> getAllLessons();
	Lesson getLessonBySection(String section);
	String getLevelOfEnglish(int score);
	List<Question> getAllQuestionsByLessonId(Long id);
	List<Question> getRandomQuestionsByLessonId(Long id, int number);
	List<Question> getQuestionsForMainTest(int number, String section);
	void addQuestion(Question question, Long lessonId);
	void addQuestions(List<Question> questions);
	int calculateResult(Lesson lesson);
}
