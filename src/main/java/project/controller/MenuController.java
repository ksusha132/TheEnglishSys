package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import project.dto.LessonDTO;
import project.model.*;

import project.repository.CommentRepository;
import project.services.LessonService;

import javax.transaction.Transactional;

import java.util.List;

@Controller // This means that this class is a Controller
@Transactional
public class MenuController {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private LessonService lessonService;

	@GetMapping("/lesson")
	public String getLesson(@RequestParam("section") Integer section, @RequestParam("level") Integer level,
			Model model) {
		List<Lesson> results = lessonService.getLessonsBySectionAndLevel(section, level);
		model.addAttribute("lessons", results);
		return "lessons";
	}

	@GetMapping(path = "/lesson/{id_lesson}/content")
	public String getLesson(Model model, @PathVariable Integer id_lesson) {
		LessonDTO lessonDTO = lessonService.getLessonDTO(id_lesson);
		if (lessonDTO == null) {
			return "redirect:/error/404";
		}
		
		model.addAttribute("lesson", lessonDTO);
		// model.addAttribute("id", id_lesson);
		return "lesson";
	}

	@PostMapping(path = "/addComment/")
	public @ResponseBody String addComment(@RequestParam("comment_title") String commentTitle,
			@RequestParam("comment_body") String commentBody, @RequestParam("id_lesson") Integer idLesson) {
		return lessonService.addComment(idLesson, commentBody, commentTitle);
	}

	@PostMapping(path = "/delete_comment/{id_comment}")
	public @ResponseBody String deleteComment(@PathVariable Integer id_comment) {
		Comment comment = commentRepository.findOne(id_comment);
		commentRepository.delete(comment);
		return "Deleted";
	}

	@GetMapping(path = "/about")
	public String getAbout() {
		return "about";
	}

}
