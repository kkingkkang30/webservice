package com.jpa.webservice.web;

import com.jpa.webservice.config.auth.LoginUser;
import com.jpa.webservice.config.auth.dto.SessionUser;
import com.jpa.webservice.service.posts.PostsService;
import com.jpa.webservice.web.dto.PostsResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
       // SessionUser user = (SessionUser) httpSession.getAttribute("user"); 어노테이션으로 대체

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
