package project.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dto.CommentDTO;
import project.dto.LessonDTO;
import project.model.Comment;
import project.model.Lesson;
import project.model.User;
import project.repository.CommentRepository;
import project.repository.LessonRepository;
import project.repository.UserRepository;
import project.util.SecurityUtil;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private CommentRepository commentRepository;
    
	@Override
	public List<Lesson> getLessonsBySectionAndLevel(Integer section, Integer level) {
		Session session = sessionFactory.getCurrentSession();
        String s = "from Lesson where section =" + section + " and level = " + level;
        Query query = session.createQuery(s);
        List<Lesson> results = query.list();
		return results;
	}

	@Override
	public LessonDTO getLessonDTO(Integer idLesson) {
		Session session = sessionFactory.getCurrentSession();
        String s = "from Lesson where id_lesson = " + idLesson;
        Query query = session.createQuery(s);
        Object result = query.uniqueResult();
        Lesson lesson = (Lesson) result;
        
        if (lesson == null) {
        	return null;
        }
        
        LessonDTO lessonDTO = new LessonDTO(lesson);
        for (Comment comment : lesson.getCommentList()) {
            CommentDTO commentDTO = new CommentDTO(comment);
            lessonDTO.getCommentList().add(commentDTO);
        }
        
        return lessonDTO;

	}

	@Override
	public String addComment(Integer idLesson, String commentBody, String commentTitle) {
		if (!commentBody.isEmpty()) {
			System.out.println("comment print");
			Comment comment = new Comment();
			comment.setCommentTitle(commentTitle);
			comment.setCommentBody(commentBody);

			String login = SecurityUtil.getCurrentLogin();
			if (login == null) {
				return "redirect:/login";
			}

			User user = userRepository.findByLogin(login);
			comment.setUser(user);
			Lesson lesson = lessonRepository.findOne(idLesson);
			comment.setLesson(lesson);
			commentRepository.save(comment);
			return "Saved";
		} else {
			return "the comment is empty";
		}
	}

}
