package project.dto;


import project.model.Comment;
import project.model.Lesson;

import java.util.LinkedList;
import java.util.List;

public class LessonDTO {

    public LessonDTO(Lesson lesson) {
        this.id_lesson = lesson.getId_lesson();
        this.name = lesson.getName();
        this.section = lesson.getSection();
        this.level = lesson.getLevel();
        this.rules = lesson.getRules();
        this.video_link = lesson.getVideo_link();
        this.audio_link = lesson.getAudio_link();
        this.text = lesson.getText();
    }

    private Integer id_lesson;

    public Integer getId_lesson() {
        return id_lesson;
    }

    public void setId_lesson(Integer id_lesson) {
        this.id_lesson = id_lesson;
    }

    private Integer section;

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    private Integer level;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String video_link;

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    private String audio_link;

    public String getAudio_link() {
        return audio_link;
    }

    public void setAudio_link(String audio_link) {
        this.audio_link = audio_link;
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String rules;

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }


    private List<CommentDTO> commentList = new LinkedList<>();

    public List<CommentDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDTO> commentList) {
        this.commentList = commentList;
    }

}
