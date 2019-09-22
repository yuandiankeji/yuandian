package com.yuandian.client.net;


import io.netty.util.AttributeKey;

/**
 * @author twjitm 2019/4/15/22:52
 */
public class SessionManager {
    private NetClient client;
    public static final String SESSION_KYE = "YUANDIAN_SESSION_KEY";
    //TODO other;
    static final SessionManager singleton = new SessionManager();

    public static SessionManager getSingleton() {
        return singleton;
    }

    public void setClient(NetClient client) {
        this.client = client;
    }

    public void bandSession(long sessionId) {
        if (getClient() == null) {
            return;
        }
        getClient().getChannel().attr(AttributeKey.valueOf(SESSION_KYE)).set(sessionId);
    }

    public NetClient getClient() {
        return client;
    }

    public long getSession() {
        if (getClient() == null) {
            return 0L;
        }
        Object session = getClient().getChannel().attr(AttributeKey.valueOf(SESSION_KYE)).get();
        if (session == null) {

            return  0L;
        }
        return  Long.parseLong(session.toString());
    }
}
