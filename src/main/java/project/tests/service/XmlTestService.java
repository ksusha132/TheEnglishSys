package project.tests.service;

import java.io.File;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import project.localCache.TestsCache;
import project.tests.models.Choice;
import project.tests.models.Lesson;
import project.tests.models.Question;
import project.tests.models.Tests;

@Service
public class XmlTestService implements TestService{

	private final static String DEFAULT_FILE_PATH = new File("").getAbsolutePath().concat("/src/main/webapp/tests/test.xml");

	private final String filePath;
	private final TestsCache cache;

	
	public XmlTestService() {
		this(DEFAULT_FILE_PATH);
	}

	@Override
	public List<Question> getQuestionsForMainTest(int number, String section) {
		List<Question> questionsTest = new LinkedList<>();
		List<Lesson> lessons = getAllLessons();
		for (Lesson lesson : lessons) {
			if (!lesson.getSection().equals(section))
				continue;
			List<Question> questions = lesson.getQuestion();
			Collections.shuffle(questions);
			if (number > questions.size()) {
				questionsTest.addAll(questions);
			} else {
				questionsTest.addAll(questions.subList(0, number));
			}
		}
		for (Question question : questionsTest) {
			Collections.shuffle(question.getChoice());
		}
		Collections.shuffle(questionsTest);
		return questionsTest;
	}

	@Override
	public String getLevelOfEnglish(int score) {
		if (score < 20)
			return "Beginner";
		else if (score >= 20 && score < 40)
			return "Pre-Intermediate";
		else if (score >= 40 && score < 60)
			return "Intermediate";
		else if (score >= 60 && score < 80)
			return "Upper-Intermediate";
		else
			return "Advanced";
	}

	@Override
	public Lesson getLessonBySection(String section) {
		List<Lesson> lessons = getAllLessons();
		List<Lesson> sectionLessons = new LinkedList<>();
		for (Lesson les : lessons){
			if (les.getSection().equals(section))
				sectionLessons.add(les);
		}
		int rand = (int)(Math.random()*sectionLessons.size());
		Lesson sectionLesson = sectionLessons.get(rand);
		Collections.shuffle(sectionLesson.getQuestion());
		for (Question question : sectionLesson.getQuestion()) {
			Collections.shuffle(question.getChoice());
		}
		return sectionLesson;
	}


	public XmlTestService(final String path) {
		this.filePath = path;
	    cache = TestsCache.getInstance().loadCache(openXmlFile(filePath));
	}

	@Override
	public List<Question> getAllQuestionsByLessonId (final Long id)
		throws NoSuchElementException
	{
		Lesson res = cache.get(id);
		if (res == null) throw new NoSuchElementException("No such lesson found");
		return res.getQuestion();
	}
	
	@Override
	public List<Question> getRandomQuestionsByLessonId(Long id, int max) {
		
		List<Question> allQuestions = getAllQuestionsByLessonId(id);
		
		// randomize questions
		Collections.shuffle(allQuestions);
		List<Question> questions = (allQuestions.size() < max ? allQuestions 
				: allQuestions.subList(0, max));
		
		// randomize choices
		for (Question q : questions) {
			Collections.shuffle(q.getChoice());
		}
		
		return questions;
	}

	@Override
	public void addQuestion(Question question, Long lessonId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addQuestions(List<Question> questions) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Lesson> getAllLessons() {
		return cache.lessons();
	}

	@Override
	public int calculateResult(Lesson lesson) {
		int cntRightAnswers = 0;
		List<Question> questions = lesson.getQuestion();
		for (Question question : questions){
			Choice choice = getChoicen(question);
			if (choice != null && choice.isCorrect()) {
				++cntRightAnswers;
			}
		};
		return cntRightAnswers;
	}
	
	private Choice getChoicen(Question question) {
		int id = question.getChosenId();
		for (Choice choice : question.getChoice()) {
			if (choice.getId() == id) {
				return choice;
			}
		}
		return null;
	}
	

	private Tests openXmlFile(final String filename) {
		JAXBContext jc = null;
		Tests res = null;
		try {
			jc = JAXBContext.newInstance(Tests.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
		    File xml = new File(filename);
		    res = (Tests) unmarshaller.unmarshal(xml);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
        return res;
	}
	

}
