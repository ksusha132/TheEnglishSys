package project.localCache;

import project.tests.models.Lesson;

import java.util.List;


public interface Cache {
    Lesson get(Long key);
    void put(Long key, Lesson lesson);
    List<Lesson> lessons();
}
