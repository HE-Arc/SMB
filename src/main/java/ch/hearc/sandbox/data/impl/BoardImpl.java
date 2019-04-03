package ch.hearc.sandbox.data.impl;

import ch.hearc.sandbox.model.Board;
import ch.hearc.sandbox.model.Post;
import ch.hearc.sandbox.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BoardImpl {
    @Autowired
    private BoardRepository brepo;


    public List<Board> findAll() {
        List<Board> boards = new ArrayList<>();
        brepo.findAll().forEach(boards::add);
        return boards;
    }

    public Board find(Long id) {
        return brepo.findById(id).orElse(new Board());
    }

    public Board save(@Valid Board board) {
        return brepo.save(board);
    }

    public Set<Post> getPosts(@Valid Board board) {
        return board.getPosts();
    }

}
