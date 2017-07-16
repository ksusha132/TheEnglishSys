package project.tests.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"question"})
public class Lesson {
	private Long id;
	private String level;
	private String section;
	
	List<Question> question;
	
	public Lesson() {
		
	}
	
	public Lesson(List<Question> questions) {
		this.question = new ArrayList<>(questions);
	}

    @XmlElement
	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlAttribute
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	@XmlAttribute
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	public Integer getSize() {
		return question.size();
	}
	
}
