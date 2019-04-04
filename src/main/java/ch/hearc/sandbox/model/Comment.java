package ch.hearc.sandbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Size(min = 1, max = 300)
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action=OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private CustomUser user;

    public Comment(String contenu, Post post, CustomUser user) {
        this.contenu = contenu;
        this.post = post;
        this.user = user;
    }

    public Comment() {
        this(new Post(), new CustomUser());
    }

    public Comment(Post post, CustomUser user) {
        this("", post, user);
    }

    public Long getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }
}
