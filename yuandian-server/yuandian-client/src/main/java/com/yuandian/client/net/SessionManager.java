package com.yuandian.client.net;


/**
 * @author twjitm 2019/4/15/22:52
 */
public class SessionManager {
    private NetClient client;
    //TODO other;
    static final SessionManager singleton = new SessionManager();

    public static SessionManager getSingleton() {
        return singleton;
    }

    public void setClient(NetClient client) {
        this.client = client;
    }

    public NetClient getClient() {
        return client;
    }
}
