package com.lingfly.blog.util.mongo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class MongoUtil implements ApplicationContextAware {


    private static MongoTemplate mongoTemplate;

    private ApplicationContext applicationContext;


    @PostConstruct
    private void init() {
        mongoTemplate = applicationContext.getBean(MongoTemplate.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object insert(Object o) {
        Object insert = mongoTemplate.insert(o);
        System.out.println(insert);
        return insert;
    }
}
