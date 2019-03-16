package ch.hearc.sandbox.repository;

import ch.hearc.sandbox.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
