package com.exam9.forum.repository;

import com.exam9.forum.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment as c where c.theme.id=:id order by c.ldt asc")
    Page<Comment> findAllByTheme_Id(Integer id, Pageable page);
}
