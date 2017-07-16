package project.tests.models;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType
public class Choice {
	private Integer id;
	private String value;
	private boolean isCorrect;

	public String getValue() {
		return value;
	}
	
	@XmlValue
	public void setValue(String choice) {
		this.value = choice;
	}
	
	@XmlAttribute
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	@XmlAttribute
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", value=" + value + ", correct=" + isCorrect + "]";
	}

	
}
