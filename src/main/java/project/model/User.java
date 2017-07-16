package project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;


@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_user", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    
    @Column(unique = true, length = 25)
    @NotEmpty
    private String login;
    
    @Column(length = 100)
    @NotEmpty
    private String password;

    @Column(unique = true, length = 40)
    @NotEmpty
    private String email;
    
    @Column(name = "name", length = 25)
    private String firstName = "";
    
    @Column(length = 25)
    private String lastName = "";
    
    @Column(length = 255)
    private String photo;
    
    @Column
    private Integer gender;
    
    @Column
    private Integer age;
    
    @Column
    private Integer scoreMainTest;
    
    @Column(length = 255)
    private String shortInfo = "";
    
    @Column
    private boolean enabled;

    public User() {}

    public User(String firstName, String lastName, String login, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
    }   

    @OneToMany(targetEntity = ContactInfo.class, mappedBy = "user", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<ContactInfo> contactInfoList;

    @OneToMany(targetEntity = Comment.class, mappedBy = "user", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Comment> commentList;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", nullable = false, updatable = true, insertable = true)
    @JsonBackReference
    private Role role;


    @OneToMany(targetEntity = TouchedLesson.class, mappedBy = "user", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<TouchedLesson> touchedLesson;

    public List<TouchedLesson> getTouchedLessonList() {
        return touchedLesson;
    }
    
    public List<TouchedLesson> getTouchedLessonSortedBySection() {
    	touchedLesson.sort(new Comparator<TouchedLesson>() {
            @Override
            public int compare(TouchedLesson lhs, TouchedLesson rhs) {
                return lhs.getScore() > rhs.getScore() ? -1 : 
                	(lhs.getScore() < rhs.getScore() ) ? 1 : 0;
            }
        });
    	touchedLesson.sort(new Comparator<TouchedLesson>() {
            @Override
            public int compare(TouchedLesson lhs, TouchedLesson rhs) {
                return lhs.getLesson().getSection() > rhs.getLesson().getSection() ? -1 : 
                	(lhs.getLesson().getSection() < rhs.getLesson().getSection() ) ? 1 : 0;
            }
        });
    	return touchedLesson;

    }

    public void setTouchedLessonList(List<TouchedLesson> touchedLessonList) {
        this.touchedLesson = touchedLessonList;
    }


    public Long getId() {
        return id_user;
    }

    public void setId(Long id_user) {
        this.id_user = id_user;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    
    public Integer getScoreMainTest() {
        return scoreMainTest;
    }

    public void setScoreMainTest(Integer scoreMainTest) {
        this.scoreMainTest = scoreMainTest;
    }
    
    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
    public List<ContactInfo> getContactInfoList() {
        return contactInfoList;
    }

    public void setContactInfoList(List<ContactInfo> contactInfoList) {
        this.contactInfoList = contactInfoList;
    }
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

}
