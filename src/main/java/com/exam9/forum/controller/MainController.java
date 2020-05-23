package com.exam9.forum.controller;

import com.exam9.forum.model.Theme;
import com.exam9.forum.model.User;
import com.exam9.forum.repository.CommentRepository;
import com.exam9.forum.repository.ThemeRepository;
import com.exam9.forum.repository.UserRepository;
import com.exam9.forum.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class MainController {

    @Autowired
    UserRepository ur;

    @Autowired
    ThemeRepository tr;

    @Autowired
    CommentRepository cr;

    private final PropertiesService propertiesService;

    public MainController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }


    @RequestMapping("/")
    public String mainPage(Model model, Principal principal, HttpServletRequest uriBuilder, Pageable page, @RequestParam(value = "page",defaultValue = "") String p){
        var uri = uriBuilder.getRequestURI();

        if(p.equals("")) {
            page = PageRequest.of(0, 5);
            constructPageable1("themes",tr.findAll(page), propertiesService.getDefaultPageSize(), model, uri);
        }
        else
            constructPageable1("themes",tr.findAll(page), propertiesService.getDefaultPageSize(), model,uri);

        try{
            var user = ur.findUserByMail(principal.getName());
            model.addAttribute("user",user);
        }catch (NullPointerException e){
            model.addAttribute("nouser",true);
        }
        return "index";
    }

    @RequestMapping("/search")
    public String getProductByName(Model model, @RequestParam("name") String name, Pageable page, HttpServletRequest uriBuilder, Principal principal){
        var uri = uriBuilder.getRequestURI().concat("?name=")+name;
        if (tr.findThemeByName(name,page).getContent().get(0)==null)
            model.addAttribute("notFound",true);
        else
            constructPageable(tr.findThemeByName(name,page), propertiesService.getDefaultPageSize(), model,uri);
        try{
            var user = ur.findUserByMail(principal.getName());
            model.addAttribute("user",user);
        }catch (NullPointerException e){
            model.addAttribute("nouser",true);
        }
        return "index";
    }

    @GetMapping("/addtheme")
    public String addThem(){
        return "addtheme";
    }

    @PostMapping("/theme/addTheme")
    public String addTheme(@RequestParam("name") String name, @RequestParam("text") String text, Principal principal){
        User user = ur.findUserByMail(principal.getName());
        Theme theme = Theme.builder()
                .name(name)
                .qty(0)
                .ldt(LocalDateTime.now())
                .user(user)
                .text(text)
                .build();
        tr.save(theme);
        return "redirect:/";
    }

    @RequestMapping("/theme/{id}")
    public String getComments(HttpServletRequest uriBuilder,Model model,@PathVariable("id")Integer id, Pageable page, @RequestParam(value = "page",defaultValue = "") String p, Principal principal){
        //tr.findThemeById(id);
        var uri = uriBuilder.getRequestURI();
        if(tr.findThemeById(id)!=null) {
            model.addAttribute("theme", tr.findThemeById(id));
        }
        if(cr.findAllByTheme_Id(id, page).getContent().size()!=0){
            if (p.equals("")) {
                page = PageRequest.of(0, 5);
                constructPageable1("comments",cr.findAllByTheme_Id(id, page), propertiesService.getDefaultPageSize(), model, uri);
            } else
                constructPageable1("comments",cr.findAllByTheme_Id(id, page), propertiesService.getDefaultPageSize(), model, uri);
        }
        try{
            User user = ur.findUserByMail(principal.getName());
            model.addAttribute("user",user);
        }catch (Exception e){

        }
        return "comment";
    }

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("themes", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s&page=%s&size=%s", uri, page, size);
    }

    private static <T> void constructPageable1(String s, Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri1(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri1(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute(s, list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri1(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

}
