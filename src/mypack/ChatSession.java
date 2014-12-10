package mypack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Bonsanto on 9/13/2014.
 */
public class ChatSession {
    public static String getNick(HttpServletRequest request, Object parameter) {
        HttpSession session = request.getSession(true);
        ChatUser usr = (ChatUser) session.getAttribute("usr");
        String answer;

        //If the chat session doesn't exist create it.
        if (usr == null) answer = "{\"status\": false}";
        else {
            String name = usr.getName();
            answer = "{\"status\": true, \"name\": \"" + name + "\"}";
        }

        return answer;
    }

    public static String setNick(HttpServletRequest request, Object parameter) {
        HttpSession session = request.getSession(true);
        ChatUser usr = (ChatUser) session.getAttribute("usr");
        String name = ((Var) parameter).asString();
        String answer;

        if (usr == null) {
            usr = new ChatUser();
        }
        usr.setName(name);
        if (!ChatSessionManager.isUsed(usr)) {
            ChatSessionManager.addUser(usr);
            session.setAttribute("usr", usr);
            session.setMaxInactiveInterval(60*5);
            answer = "{\"status\": true, \"name\": \"" + name + "\"}";
        } else {
            answer = "{\"status\": false}";
        }

        return answer;
    }

    public static String getMessages(HttpServletRequest request, Object parameter) {
        JSON json = new JSON();
        ArrayList<Msg> m = new ArrayList<>();
        HttpSession session = request.getSession(true);
        ChatUser usr = (ChatUser) session.getAttribute("usr");
        ArrayList<Msg> msgs = ChatSessionManager.getMessages(usr);

        String answer;

        if(msgs != null) {
            int msgsSize = msgs.size();
            if(msgsSize > 0) {
                int notSentMessages = 0;

                for (int i = 0; i < msgsSize; i++) {
                    notSentMessages += (!msgs.get(i).isStatus()) ? 1 : 0 ;
                }

                if(notSentMessages > 0) {
                    JSON[] message = new JSON[notSentMessages];

                    for (int i = 0; i < msgsSize; i++) {
                        if(!msgs.get(i).isStatus()){
                            m.add(msgs.get(i));
                        }
                        /*
                        if (!msgs.get(i).isStatus()) { //If the message was not delivered.
                            Msg m = msgs.get(i);
                            message[i] = new JSON();
                            message[i].addAttribute("sender", m.getSenderName());
                            message[i].addAttribute("msg", m.getMessage());
                            message[i].build();
                        }
                        */
                    }
                    for (int i = 0; i < m.size(); i++) {
                        Msg me = m.get(i);
                        message[i] = new JSON();
                        message[i].addAttribute("sender", me.getSenderName());
                        message[i].addAttribute("msg", me.getMessage());
                        message[i].build();
                    }
                    //msgs = new ArrayList<>();
                    ChatSessionManager.deleteMessages(usr, msgs);
                    json.addAttribute("messages", message);
                    json.build();
                    answer = json.getJson();
                } else {
                    answer = "{\"messages\": []}";
                }
            } else {
                answer = "{\"messages\": []}";
            }
        } else {
            answer = "{\"messages\": []}";
        }
        return answer;
    }

    //todo Session can expire.
    public static String sendMessage(HttpServletRequest request, Object parameter) {
        //todo if receiver is connected
        JSON json = new JSON();
        HttpSession session = request.getSession(true);
        VarList vl = (VarList) parameter;
        String receiverName = vl.getStruct(0).asString();
        String message = vl.getStruct(1).asString();
        ChatUser sender = (ChatUser) session.getAttribute("usr");
        ChatUser receiver = ChatSessionManager.getChatUser(receiverName);
        String answer;

        if (receiver != null){
            Msg msg = new Msg();
            msg.setSenderName(sender.getName());
            msg.setMessage(message);
            msg.setStatus(false);
            ChatSessionManager.addMessage(receiver, msg);
            answer = "{\"messages\": [ {\"sender\": \"" + sender.getName() + "\", \"msg\": \"" + message + "\"" + "]}";
        } else {
            answer = "{\"messages\": [ {\"sender\": \"Server\", \"msg\": \"User is not connected, try later\"]}";
        }

        return answer;
    }
}
