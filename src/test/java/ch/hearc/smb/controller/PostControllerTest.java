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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        Mockito.when(postRepository.findById(0l).get())
                .thenReturn(post);
    }

    @Test
    @WithMockUser(username = "test",roles = {"ADMIN"})
    public void HomeController_thenResponseIsCorrect() throws Exception {
        mvc.perform(post("/posts/delete").param("post","0").with(csrf()))
                .andExpect(status().isOk())
                .andReturn();
    }
}
