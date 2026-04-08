package br.com.wilsonqdop.redesocial.domain.post;


import br.com.wilsonqdop.redesocial.domain.essential.Comment;
import br.com.wilsonqdop.redesocial.domain.essential.Like;
import br.com.wilsonqdop.redesocial.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Posts {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String content;
    private String picturePost;
    private LocalDateTime created_at;

    @ManyToOne
    private User user;
    @OneToMany
    private List<Comment> comments = new ArrayList<>();
    @OneToMany
    private Set<Like> Like = new HashSet<>();

}
