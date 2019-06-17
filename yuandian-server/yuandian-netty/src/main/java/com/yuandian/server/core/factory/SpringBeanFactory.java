package com.yuandian.server.core.factory;

import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class SpringBeanFactory {
    private static SpringBeanFactory factory;
    @Autowired
    private ChatService chatService;
    @Autowired
    private FriendService friendService;


    public static void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-spring.xml");
        factory = (SpringBeanFactory) context.getBean("springBeanFactory");
    }

    public static SpringBeanFactory getInstance() {
        return factory;
    }

    public ChatService getChatService() {
        return chatService;
    }

    public FriendService getFriendService() {
        return friendService;
    }
}
