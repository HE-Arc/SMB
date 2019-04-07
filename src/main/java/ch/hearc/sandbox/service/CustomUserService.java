package ch.hearc.sandbox.service;

import ch.hearc.sandbox.model.CustomUser;

public interface CustomUserService {

	void save(CustomUser customUser);

	CustomUser findByCustomusername(String username);

	CustomUser findByCustomemail(String email);

	CustomUser findByCustomId(long id);

	void createPasswordResetTokenForUser(CustomUser customUser, String token);

	void changeUserPassword(CustomUser user, String password);

}
