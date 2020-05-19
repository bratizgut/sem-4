package server.message;

import java.io.Serializable;

/**
 *
 * @author bratizgut
 */
public class GameEndMessage implements Serializable, Message {
    public final boolean P1Ready;
    public final boolean P2Ready;

    public GameEndMessage(boolean P1Ready, boolean P2Ready) {
        this.P1Ready = P1Ready;
        this.P2Ready = P2Ready;
    }
    
}
