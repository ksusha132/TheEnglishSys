package project.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SectionType")
public class SectionType implements Serializable {
    private static final long serialVersionUID = 1L;

    public SectionType() {

    }

    public SectionType(Integer id_sectionType, String name, Level level) {
        this.id_sectionType = id_sectionType;
        this.name = name;
    }

    @Id
    @Column(name = "id_sectionType", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sectionType;

    public Integer getId_sectionType() {
        return id_sectionType;
    }

    public void setId_sectionType(Integer id_sectionType) {
        this.id_sectionType = id_sectionType;
    }

    @Column(name = "name", unique = false, nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*@ManyToOne
    @JoinColumn(name = "id_level", nullable = false, updatable = true, insertable = true)
    private Level level;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @OneToMany(targetEntity = Lesson.class, mappedBy = "sectionType", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Lesson> lessonList;

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }
    */
}
