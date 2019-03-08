package ch.hearc.sandbox.repository;

import ch.hearc.sandbox.model.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
