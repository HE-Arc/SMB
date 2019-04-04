package ch.hearc.sandbox.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import ch.hearc.sandbox.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.hearc.sandbox.model.CustomUser;
import ch.hearc.sandbox.repository.CustomUserRepository;
import ch.hearc.sandbox.repository.RoleRepository;


@Service
public class CustomUserServiceImpl implements CustomUserService {
	@Autowired
	private CustomUserRepository customUserRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(CustomUser customUser) {
		customUser.setPassword(bCryptPasswordEncoder.encode(customUser.getPassword()));
		customUser.setRoles(new HashSet<>((Collection<? extends Role>) roleRepository.findAllById(Arrays.asList(2l))));
		customUserRepository.save(customUser);
	}

	@Override
	public CustomUser findByCustomusername(String username) {
		return customUserRepository.findByUsername(username);
	}
}
