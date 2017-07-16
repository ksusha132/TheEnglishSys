package project.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TestQuestion")
public class TestQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    public TestQuestion() {

    }

    public TestQuestion(Integer id_testQuestion, String link, Lesson lesson) {
        this.id_testQuestion = id_testQuestion;
        this.link = link;
        this.lesson = lesson;
    }

    @Id
    @Column(name = "id_testQuestion", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_testQuestion;

    public Integer getId_testQuestion() {
        return id_testQuestion;
    }

    public void setId_testQuestion(Integer id_testQuestion) {
        this.id_testQuestion = id_testQuestion;
    }

    @Column(name = "link", unique = true, nullable = false, length = 200)
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @ManyToOne
    @JoinColumn(name = "id_lesson", nullable = false)
    private Lesson lesson;
}
