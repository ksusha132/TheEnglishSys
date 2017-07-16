package project.services;

import java.util.List;



import project.dto.UserDTO;
import project.model.User;

public interface UserService {

	String validateVerificationToken(String token);
	User getUserByToken(String token);
	void createVerificationToken(User user, String token);
	void updateLessonScore(Integer idLesson, Integer score, Integer cnt);
	UserDTO getUserDTO(Long id);
	UserDTO getUserDTO(String login);
	List<UserDTO> getAllUsers();
	List<UserDTO> getSpeakingUsers(String login);
	List<String> updateUser(Long id, String name, String last_name, String short_info, Integer age, Integer gender, Integer id_role);
}
