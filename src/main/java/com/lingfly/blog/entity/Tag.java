package com.lingfly.blog.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tags")
@Data
public class Tag {
    @Id
    private String id;

    private String name;
    private String description;
}
