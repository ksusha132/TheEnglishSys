package project.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TouchedLesson")
public class TouchedLesson implements Serializable {
    private static final long serialVersionUID = 1L;

    public TouchedLesson() {}

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "score", unique = false)
    private Integer score;
    
    @Column(name = "question_cnt", unique = false)
    private Integer questionCnt;

    @ManyToOne
    @JoinColumn(name = "id_lesson")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getQuestionCnt() {
		return questionCnt;
	}

	public void setQuestionCnt(Integer questionCnt) {
		this.questionCnt = questionCnt;
	}
	
	public Integer getPercentScore() {
		return  (int) (score / (float) questionCnt * 100);
	}
    
}

