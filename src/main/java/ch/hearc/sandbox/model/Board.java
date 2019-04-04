package ch.hearc.sandbox.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Size(min = 1, max = 30)
    private String nom;

    @NotNull
    @Size(min = 1, max = 100)
    private String description;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> posts;

    public Board(String nom, String description) {
        super();
        this.nom = nom;
        this.description = description;
        this.posts = new ArrayList<>();
    }

    public Board() {
        this("","");
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    public String getNom() {
        return nom;
    }

    public Long getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
