package ch.hearc.sandbox.data;

import ch.hearc.sandbox.model.Board;
import ch.hearc.sandbox.model.Post;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

public interface BoardDAO {
    List<Board> findAll();
    Board find(Long id);
    void save(@Valid Board board);
    Set<Post> getPosts(@Valid Board board);
}
