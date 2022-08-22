package com.lingfly.blog.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("articles")
@Data
public class Article {

    @Id
    private String id;

    private String title;
    private String description;
    private List<String> tags;
    private Integer length;
    private String author;
    private Long publish_time;
    private Long update_time;

}
