package mypack;

import java.util.ArrayList;

/**
 * Created by Bonsanto on 9/12/2014.
 */
public class ChatUser {
    private String name;
    private ArrayList<Msg> messages = new ArrayList<>();


    //TODO: PROBABLY YOU WILL NEED TO ADD A REMOVE MESSAGEUSING A STRING, OR INDEX.
    public void removeMessage(Msg message) {
        messages.remove(message);
    }

    public void setMessageStatus(Msg msg, boolean status) {
        for (Msg m : messages) {
            if (m.equals(msg)) {
                m.setStatus(status);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Msg> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Msg> messages) {
        this.messages = messages;
    }

    public void addMessage(Msg message){
        messages.add(message);
    }
}
