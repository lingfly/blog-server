package com.lingfly.blog.controller;


import com.lingfly.blog.entity.Article;
import com.lingfly.blog.repo.ArticleRepository;
import com.lingfly.blog.util.mongo.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/insert")
    public Object insert(@RequestBody Article article) {
        Article insert = articleRepository.insert(article);
        System.out.println(insert);
        return insert;
    }


}
