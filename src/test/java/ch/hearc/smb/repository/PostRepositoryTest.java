package ch.hearc.smb.repository;


import ch.hearc.smb.model.Board;
import ch.hearc.smb.model.CustomUser;
import ch.hearc.smb.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testBoard() {
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

        Optional<Post> postRecherche = postRepository.findById(post.getId());

        assertTrue(postRecherche.isPresent());
        assertTrue(postRecherche.get().getId().equals(post.getId()));
        assertTrue(postRecherche.get().getName().equals(post.getName()));
        assertTrue(postRecherche.get().getContent().equals(post.getContent()));
        assertTrue(postRecherche.get().getBoard().equals(post.getBoard()));
        assertTrue(postRecherche.get().getUser().equals(post.getUser()));
        assertThat(postRecherche.get()).isNotNull();

    }
}
