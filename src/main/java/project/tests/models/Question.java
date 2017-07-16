package project.tests.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"ask", "choice"})
public class Question {
	private Long id;
	private String ask;
	private List<Choice> choice;
	private int chosen_id;
	
    @XmlElement(name="ask")
	public String getAsk() {
		return ask;
	}
	
	public void setAsk(String question) {
		this.ask = question;
	}
	
    @XmlElement(name="choice")
	public List<Choice> getChoice() {
		return choice;
	}
	
	public void setChoice(List<Choice> choices) {
		this.choice = choices;
	}
	
	@XmlAttribute
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@XmlTransient
	public int getChosenId() {
		return chosen_id;
	}

	public void setChosenId(int chosen) {
		this.chosen_id = chosen;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", ask=" + ask + ", choice=" + choice + ", chosen=" + chosen_id + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Question question = (Question) o;

		if (chosen_id != question.chosen_id) return false;
		if (!id.equals(question.id)) return false;
		if (!ask.equals(question.ask)) return false;
		return choice.equals(question.choice);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + ask.hashCode();
		result = 31 * result + choice.hashCode();
		result = 31 * result + chosen_id;
		return result;
	}
}
