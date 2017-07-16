package project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Lesson")
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    Lesson() {}

    Lesson(Integer id_lesson, String name, Integer section, Integer level,
           String rules, String video_link, String audio_link, String text) {
        this.id_lesson = id_lesson;
        this.name = name;
        this.section = section;
        this.level = level;
        this.rules = rules;
        this.video_link = video_link;
        this.audio_link = audio_link;
        this.text = text;
    }

    @Id
    @Column(name = "id_lesson", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_lesson;

    public Integer getId_lesson() {
        return id_lesson;
    }

    public void setId_lesson(Integer id_lesson) {
        this.id_lesson = id_lesson;
    }

    @Column(name = "section", unique = false, nullable = false)
    private Integer section;

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    @Column(name = "level", unique = false, nullable = false)
    private Integer level;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "video_link", unique = false, nullable = true, length = 250)
    private String video_link;

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    @Column(name = "audio_link", unique = false, nullable = true, length = 250)
    @Type(type="text")
    private String audio_link;

    public String getAudio_link() {
        return audio_link;
    }

    public void setAudio_link(String audio_link) {
        this.audio_link = audio_link;
    }

    @Column(name = "text", nullable = true)
    @Type(type="text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "rules", unique = false, nullable = true)
    private String rules;


    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }


    @OneToMany(targetEntity = Comment.class, mappedBy = "lesson", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Comment> commentList;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

/*
    @OneToMany(targetEntity = TouchedLesson.class, mappedBy = "lesson", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TouchedLesson> touchedLessonList;

    public List<TouchedLesson> getTouchedLessonList() {
        return touchedLessonList;
    }

    public void setTouchedLessonList(List<TouchedLesson> touchedLessonList) {
        this.touchedLessonList = touchedLessonList;
    }
    */

    @OneToMany(targetEntity = TestQuestion.class, mappedBy = "lesson", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TestQuestion> testQuestionList;

    public List<TestQuestion> getTestQuestionList() {
        return testQuestionList;
    }

    public void setTestQuestionList(List<TestQuestion> testQuestionList) {
        this.testQuestionList = testQuestionList;
    }

    @OneToMany(targetEntity = TestSentance.class, mappedBy = "lesson", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TestSentance> testSentanceList;

    public List<TestSentance> getTestSentanceList() {
        return testSentanceList;
    }

    public void setTestSentanceList(List<TestSentance> testSentanceList) {
        this.testSentanceList = testSentanceList;
    }
}

