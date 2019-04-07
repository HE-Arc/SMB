package ch.hearc.sandbox.repository;

import ch.hearc.sandbox.model.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository  extends CrudRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);

}
