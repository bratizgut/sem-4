package common.model;

import common.message.ConnectionProblemsError;
import common.message.CoordMessage;
import common.message.GameEndMessage;
import common.message.Message;

/**
 *
 * @author bratizgut
 */
public class ModelState {
    public final int player1X;
    public final int player1Y;
    public final int player2X;
    public final int player2Y;
    public final int ballX;
    public final int ballY;
    public final int Score1;
    public final int Score2;
    
    public final boolean gameEnd;
    public final boolean connectionFlag;
    public final boolean P1Ready;
    public final boolean P2Ready;

    public ModelState(int player1X, int player1Y, int player2X, int player2Y, int ballX, int ballY, int Score1, int Score2, boolean gameEnd, boolean connectionFlag, boolean P1Ready, boolean P2Ready) {
        this.player1X = player1X;
        this.player1Y = player1Y;
        this.player2X = player2X;
        this.player2Y = player2Y;
        this.ballX = ballX;
        this.ballY = ballY;
        this.Score1 = Score1;
        this.Score2 = Score2;
        this.gameEnd = gameEnd;
        this.connectionFlag = connectionFlag;
        this.P1Ready = P1Ready;
        this.P2Ready = P2Ready;
    }
    
    public Message getMessage() {
        if(!connectionFlag) {
            return new ConnectionProblemsError();
        }
        if(gameEnd) {
            return new GameEndMessage(P1Ready, P2Ready);
        }
        return new CoordMessage(player1X, player1Y, player2X, player2Y, ballX, ballY, Score1, Score2);
    }
    
}
