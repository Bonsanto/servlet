package mypack;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Bonsanto on 9/13/2014.
 */
public class SessionListener implements HttpSessionListener {
    private int sessionCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        synchronized (this) {
            sessionCount++;
        }
        System.out.println("Session Created: " + httpSessionEvent.getSession().getId());
        System.out.println("Total Sessions: " + sessionCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        synchronized (this) {
            //Removes the user from the connected or active users list.
            ChatUser chatUser = (ChatUser) httpSessionEvent.getSession().getAttribute("usr");
            ChatSessionManager.removeUser(chatUser);
            sessionCount--;
        }
        System.out.println("Session Destroyed: " + httpSessionEvent.getSession().getId());
        System.out.println("Total Sessions: " + sessionCount);
    }
}

