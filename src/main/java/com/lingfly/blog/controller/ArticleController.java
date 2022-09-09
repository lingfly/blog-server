package com.lingfly.blog.controller;


import com.lingfly.blog.entity.Article;
import com.lingfly.blog.repo.ArticleRepository;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleRepository articleRepository;

    @PostMapping
    public Object addArticle(@RequestBody Article data) {
        return articleRepository.insert(data);
    }

    @GetMapping
    public List<Article> getArticle(@RequestBody Article data) {
        return articleRepository.findAll(Example.of(data));
    }

    @PutMapping
    public Article updateArticle(@RequestBody Article data) {
        Optional<Article> existed = articleRepository.findById(data.getId());
        return articleRepository.save(data);
    }
}
