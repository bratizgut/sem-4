package common.message;

import java.io.Serializable;

/**
 *
 * @author bratizgut
 */
public class CoordMessage implements Serializable, Message {
    public final int player1X;
    public final int player1Y;
    public final int player2X;
    public final int player2Y;
    public final int ballX;
    public final int ballY;
    public final int Score1;
    public final int Score2;

    public CoordMessage(int player1X, int player1Y, int player2X, int player2Y, int ballX, int ballY, int Score1, int Score2) {
        this.player1X = player1X;
        this.player1Y = player1Y;
        this.player2X = player2X;
        this.player2Y = player2Y;
        this.ballX = ballX;
        this.ballY = ballY;
        this.Score1 = Score1;
        this.Score2 = Score2;
    }
    
}
