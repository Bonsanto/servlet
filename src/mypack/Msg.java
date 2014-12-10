package mypack;

/**
 * Created by Bonsanto on 9/12/2014.
 */
public class Msg {
    private String senderName;
    private String message;
    private boolean status; //true = sent, false = not sent.

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
