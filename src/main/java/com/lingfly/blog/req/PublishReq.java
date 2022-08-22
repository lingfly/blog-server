package com.lingfly.blog.req;

import lombok.Data;

import java.util.List;

@Data
public class PublishReq {

    private String title;
    private String description;
    private List<String> tags;
    private String author;

}
