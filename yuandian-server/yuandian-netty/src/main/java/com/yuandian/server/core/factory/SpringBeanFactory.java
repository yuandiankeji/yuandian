package com.yuandian.server.core.factory;

import com.yuandian.server.config.RedisService;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.friends.service.FriendService;
import com.yuandian.server.logic.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringBeanFactory {
    private static SpringBeanFactory factory;
    @Autowired
    private ChatService chatService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisChatService;
    @Autowired
    private RedisService redisGlobalService;

    public static void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-bean.xml", "spring/applicationContext-spring.xml");
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

    public UserService getUserService() {
        return userService;
    }

    public RedisService getRedisChatService() {
        return redisChatService;
    }

    public RedisService getRedisGlobalService() {
        return redisGlobalService;
    }
}
