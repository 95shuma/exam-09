package com.exam9.forum.repository;

import com.exam9.forum.model.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThemeRepository extends JpaRepository<Theme,Integer> {

    @Query("select t from Theme as t where (t.name like concat(:name, '%')) or (t.name like concat('%',:name,'%')) or (t.name like concat('%',:name)) order by t.ldt desc ")
    Page<Theme> findThemeByName(String name, Pageable page);

    @Query("select t from Theme as t order by t.ldt desc")
    Page<Theme> findAll(Pageable page);

    Theme findThemeById(Integer id);
}
