package project.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Level")
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;

    public Level() {

    }

    public Level(Integer id_level, String name) {

        this.name = name;
        this.id_level = id_level;
    }

    @Id
    @Column(name = "id_level", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_level;

    public Integer getId_level() {
        return id_level;
    }

    public void setId_level(Integer id_level) {
        this.id_level = id_level;
    }

    @Column(name = "name", unique = false, nullable = false, length = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


   /* @OneToMany(targetEntity = SectionType.class, mappedBy = "level", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SectionType> sectionTypeList;

    public List<SectionType> getSectionTypeList() {
        return sectionTypeList;
    }

    public void setSectionTypeList(List<SectionType> sectionTypeList) {
        this.sectionTypeList = sectionTypeList;
    }

    */
}
