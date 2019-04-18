package ch.hearc.smb.controller;


import ch.hearc.smb.model.Board;
import ch.hearc.smb.model.CustomUser;
import ch.hearc.smb.model.Post;
import ch.hearc.smb.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private PostRepository postRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CustomUser user = new CustomUser();
        user.setUsername("test");
        user.setPassword("12345678");
        user.setEmail("test@test.com");
        user.setId(0l);

        Board board = new Board();
        board.setName("test board");
        board.setDescription("test description");


        Post post = new Post();
        post.setBoard(board);
        post.setContent("test post");
        post.setName("test name");
        post.setId(0l);
        post.setUser(user);

        Mockito.when(postRepository.findById(0l))
                .thenReturn(Optional.of(post));
    }

    @Test
    @WithMockUser(username = "test",roles = {"ADMIN"})
    public void HomeController_thenResponseIsCorrect() throws Exception {

    }
}
