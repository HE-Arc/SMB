package ch.hearc.sandbox.service;

import ch.hearc.sandbox.model.CustomUser;

public interface CustomUserService {

	void save(CustomUser customUser);

	CustomUser findByCustomusername(String username);

	CustomUser findByCustomemail(String email);
}
