package project.controller;


import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import project.dto.LessonDTO;
import project.model.User;
import project.repository.UserRepository;
import project.services.UserService;
import project.tests.models.Lesson;
import project.tests.models.Question;
import project.tests.service.TestService;
import project.util.SecurityUtil;

@Controller
@Transactional
public class TestController {
	
	private static final int MAX_QUESTION = 11;
	private static final int MAX_QUESTION_MAIN_TEST = 1;
	private static final String READING_SECTION = "reading";
	private static final String LISTENING_SECTION = "listening";
	private static final String GRAMMAR_SECTION = "grammar";

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TestService testService;
	
	@Autowired
	UserService userService;

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@GetMapping("/maintest")
	public String getMainTest(Model model) {
		
		List<Question> questions = null;
		try {
			questions = testService.getQuestionsForMainTest(MAX_QUESTION_MAIN_TEST, GRAMMAR_SECTION);
		} catch(NoSuchElementException ex) {
			return "redirect:/error/404";
		}

		Lesson readingLesson = testService.getLessonBySection(READING_SECTION);
		Lesson listeningLesson = testService.getLessonBySection(LISTENING_SECTION);

		Session session = getSessionFactory().getCurrentSession();
		String sReading = "from Lesson where id_lesson = " + readingLesson.getId();
		String sListening = "from Lesson where id_lesson = " + listeningLesson.getId();
		Query queryReading = session.createQuery(sReading);
		Query queryListening = session.createQuery(sListening);
		Object resultReading = queryReading.uniqueResult();
		Object resultListening = queryListening.uniqueResult();
		project.model.Lesson lessonReading = (project.model.Lesson) resultReading;
		project.model.Lesson lessonListening = (project.model.Lesson) resultListening;

		LessonDTO lessonDTOReading = new LessonDTO(lessonReading);
		LessonDTO lessonDTOListening = new LessonDTO(lessonListening);

		model.addAttribute("lessonReading", lessonDTOReading);
		for (Question question : readingLesson.getQuestion())
			questions.add(0, question);

		model.addAttribute("lessonListening", lessonDTOListening);
		for (Question question : listeningLesson.getQuestion())
			questions.add(0, question);

        model.addAttribute("questions", questions);
        model.addAttribute("test", new Lesson(questions));
		return "mainTest";
	}

	@PostMapping("/resultMain")
	public String showResult(@ModelAttribute("test") Lesson test,
							 HttpServletRequest request, Model model) {

		int res = testService.calculateResult(test);
		int countQuestions = test.getQuestion().size();
		int finalResult = (int) ((res * 1.0 / countQuestions) * 100);
		String levelOfEnglish = testService.getLevelOfEnglish(finalResult);

		model.addAttribute("questions", test.getQuestion());
		model.addAttribute("result", finalResult);
		model.addAttribute("level", levelOfEnglish);

		String login = SecurityUtil.getCurrentLogin();
		User user = userRepository.findByLogin(login);
		user.setScoreMainTest(res);
		userRepository.save(user);

		return "resultMainTest";
	}

	@GetMapping("/lesson/{id}/test")
	public String getTest(@PathVariable("id") Long id, Model model) {

		List<Question> questions = null;
		try {
			questions = testService.getRandomQuestionsByLessonId(id, MAX_QUESTION);
		} catch(NoSuchElementException ex) {
			return "redirect:/error/404";
		}

		model.addAttribute("questions", questions);
		model.addAttribute("test", new Lesson(questions));
		return "test";
	}

	@PostMapping("/lesson/{id}/result")
	public String showResult(@PathVariable("id") Integer id, @ModelAttribute("test") Lesson test,
			HttpServletRequest request, Model model) {
		
		
		int score = testService.calculateResult(test);
		userService.updateLessonScore(id, score, test.getSize());
		
		model.addAttribute("questions", test.getQuestion());
		model.addAttribute("result", score);
		model.addAttribute("cnt", test.getSize());
		
		return "result";
	}
	
	
}
