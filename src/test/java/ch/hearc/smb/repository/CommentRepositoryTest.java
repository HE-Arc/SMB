package ch.hearc.smb.repository;

import ch.hearc.smb.model.Board;
import ch.hearc.smb.model.Comment;
import ch.hearc.smb.model.CustomUser;
import ch.hearc.smb.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testComment() {
        Board board = new Board();
        board.setName("test board");
        board.setDescription("test description");

        entityManager.persist(board);
        entityManager.flush();

        CustomUser user = new CustomUser();
        user.setUsername("test");
        user.setPassword("12345678");
        user.setEmail("test@test.com");

        entityManager.persist(user);
        entityManager.flush();

        Post post = new Post();
        post.setBoard(board);
        post.setContent("test post");
        post.setName("test name");
        post.setUser(user);

        entityManager.persist(post);
        entityManager.flush();

        Comment comment = new Comment();
        comment.setContent("test content");
        comment.setPost(post);
        comment.setUser(user);

        entityManager.persist(comment);
        entityManager.flush();

        Optional<Comment> commentRecherche = commentRepository.findById(comment.getId());

        assertTrue(commentRecherche.isPresent());
        assertTrue(commentRecherche.get().getId().equals(comment.getId()));
        assertTrue(commentRecherche.get().getContent().equals(comment.getContent()));
        assertTrue(commentRecherche.get().getPost().equals(comment.getPost()));
        assertTrue(commentRecherche.get().getUser().equals(comment.getUser()));
        assertThat(commentRecherche.get()).isNotNull();
    }
}
