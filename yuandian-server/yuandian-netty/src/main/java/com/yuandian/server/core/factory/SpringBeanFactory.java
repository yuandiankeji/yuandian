package com.yuandian.server.core.factory;

import com.yuandian.server.logic.chat.service.ChatService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;


public class SpringBeanFactory {
    private static SpringBeanFactory factory;
    @Resource
    private ChatService chatService;

    public static void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-spring.xml");
        factory = (SpringBeanFactory) context.getBean("springBeanFactory");
    }

    public static SpringBeanFactory getInstance() {
        return factory;
    }

    public ChatService getChatService() {
        return chatService;
    }
}
