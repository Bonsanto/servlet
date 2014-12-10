package mypack;

import java.util.*;

/**
 * Created by Bonsanto on 9/13/2014.
 */
public class ChatSessionManager {
    private static List<ChatUser> _connected = Collections.synchronizedList(new ArrayList<ChatUser>());

    public static List<ChatUser> getConnected(){
        return _connected;
    }

    //Adds a user to the list.
    public static void addUser(ChatUser chatUser) {
        if (!isUsed(chatUser)) {
            synchronized (_connected) {
                _connected.add(chatUser);
            }
        }
    }

    //Removes a user of the list.
    public static void removeUser(ChatUser chatUser){
        if(isUsed(chatUser)){
            synchronized (_connected){
                _connected.remove(chatUser);
            }
        }
    }

    public static boolean isUsed(String chatUsername) {
        synchronized (_connected) {
            for (ChatUser cu : _connected) {
                if(cu.getName().equals(chatUsername)){
                    return true;
                }
            }
            return false;
        }
    }

    //Says if the username is alreadyused.
    public static boolean isUsed(ChatUser chatUser) {

        synchronized (_connected) {
            return isUsed(chatUser.getName());
        }
    }

    public static ChatUser getChatUser(String chatUser){
        synchronized (_connected){
            if (isUsed(chatUser)) {
                for (ChatUser cu : _connected) {
                    if (cu.getName().equals(chatUser)) {
                        return cu;
                    }
                }
            }
            return null;
        }
    }
    public static ChatUser getChatUser(ChatUser chatUser){
        synchronized (_connected){
            return getChatUser(chatUser.getName());
        }
    }

    //todo, probbaly won't work.
    public static ArrayList<Msg> getMessages(ChatUser chatUser){
        synchronized (_connected) {
            ArrayList<Msg> answer;
            ChatUser cu = getChatUser(chatUser);

            return (cu == null) ? null : cu.getMessages();
        }
    }

    public static void deleteMessages(ChatUser chatUser, ArrayList<Msg> msgs){
        synchronized (_connected) {
            ChatUser cu = getChatUser(chatUser);
            boolean answer = true;

            if(cu != null && cu.getName().equals(chatUser.getName())){
                for (Msg m : msgs){
                    cu.setMessageStatus(m, true);
                }
            }
        }
    }

    public static void addMessage(ChatUser chatUser, Msg msg){
        synchronized (_connected){
            ChatUser cu = getChatUser(chatUser);
            removeUser(chatUser);
            cu.addMessage(msg);
            addUser(cu);
        }
    }
}