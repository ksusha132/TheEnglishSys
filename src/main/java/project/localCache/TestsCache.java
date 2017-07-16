package project.localCache;

import project.tests.models.Lesson;
import project.tests.models.Tests;

import java.util.*;

public class TestsCache implements Cache{
    private Map<Long, Lesson> map;
    private static TestsCache instance = new TestsCache();

    private TestsCache(){
        map = new WeakHashMap<>();
    }

    public static TestsCache getInstance(){
        return instance;
    }
    
    public TestsCache loadCache(Tests tests) {
		List<Lesson> lessons = tests.getLesson();
        for (Lesson x : lessons) {
            map.put(x.getId(), x);
        }
        return this;
    }

    @Override
    public void put(Long key, Lesson value) {
        map.put(key, value);
    }

    @Override
    public Lesson get(Long key) {
        return map.get(key);
    }

	@Override
	public List<Lesson> lessons() {
		return new ArrayList<Lesson>(map.values());
	}
}
