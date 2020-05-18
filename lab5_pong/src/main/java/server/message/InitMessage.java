package server.message;

import java.io.Serializable;

/**
 *
 * @author bratizgut
 */
public class InitMessage implements Serializable {
    public final int width;
    public final int height;
    public final int ballRad;
    public final int paneLength;
    public final int paneWidth;
    
    public final boolean connectionFlag;

    public InitMessage(int width, int height, int ballRad, int paneLength, int paneWidth, boolean connectionFlag) {
        this.width = width;
        this.height = height;
        this.ballRad = ballRad;
        this.paneLength = paneLength;
        this.paneWidth = paneWidth;
        this.connectionFlag = connectionFlag;
    }

}
