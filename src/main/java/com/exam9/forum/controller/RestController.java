package com.exam9.forum.controller;

import com.exam9.forum.model.Comment;
import com.exam9.forum.model.Theme;
import com.exam9.forum.model.User;
import com.exam9.forum.repository.CommentRepository;
import com.exam9.forum.repository.ThemeRepository;
import com.exam9.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    UserRepository ur;

    @Autowired
    ThemeRepository tr;

    @Autowired
    CommentRepository cr;



    @PostMapping("/theme/addComment")
    public Comment addComment(@RequestParam("text") String text, @RequestParam("id") Integer id){
        var theme = tr.findThemeById(id);
        theme.setQty(theme.getQty()+1);
        tr.save(theme);
        Comment comment = Comment.builder()
                .ldt(LocalDateTime.now())
                .text(text)
                .theme(theme)
                .user(theme.getUser())
                .build();
        cr.save(comment);
        return comment;
    }
}
