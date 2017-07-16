package project.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TestSentance")
public class TestSentance implements Serializable {

    private static final long serialVersionUID = 1L;

    public TestSentance() {

    }

    public TestSentance(Integer id_testSentance, String link, Lesson lesson) {
        this.id_testSentance = id_testSentance;
        this.link = link;
        this.lesson = lesson;
    }

    @Id
    @Column(name = "id_testSentance", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_testSentance;

    public Integer getId_testSentance() {
        return id_testSentance;
    }

    @Column(name = "link", unique = true, nullable = false, length = 200)
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setId_testSentance(Integer id_testSentance) {
        this.id_testSentance = id_testSentance;
    }

    @ManyToOne
    @JoinColumn(name = "id_lesson", nullable = false)
    private Lesson lesson;
}
