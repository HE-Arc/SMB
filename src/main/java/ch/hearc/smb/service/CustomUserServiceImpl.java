package ch.hearc.smb.service;

import ch.hearc.smb.model.CustomUser;
import ch.hearc.smb.model.PasswordResetToken;
import ch.hearc.smb.model.Role;
import ch.hearc.smb.repository.CustomUserRepository;
import ch.hearc.smb.repository.PasswordResetTokenRepository;
import ch.hearc.smb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Service
public class CustomUserServiceImpl {
    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(CustomUser customUser) {
        customUser.setPassword(bCryptPasswordEncoder.encode(customUser.getPassword()));
        customUser.setEmail(customUser.getEmail());
        customUser.setRoles(new HashSet<>((Collection<? extends Role>) roleRepository.findAllById(Arrays.asList(2l))));
        customUserRepository.save(customUser);
    }

    public CustomUser findByCustomusername(String username) {
        return customUserRepository.findByUsername(username);
    }

    public CustomUser findByCustomemail(String email) {
        return customUserRepository.findByEmail(email);
    }

    public void createPasswordResetTokenForUser(CustomUser customUser, String token){
        PasswordResetToken myToken = new PasswordResetToken(token,customUser);
        passwordResetTokenRepository.save(myToken);
    }

    public void changeUserPassword(CustomUser user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        customUserRepository.save(user);
    }

    public CustomUser findByCustomId(long id){
        return customUserRepository.findById(id).get();
    }

    public List<CustomUser> findByUsernameContaining(String username)
    {
        return customUserRepository.findByUsernameContaining(username);
    }
}
