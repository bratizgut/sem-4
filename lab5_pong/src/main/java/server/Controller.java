package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import server.model.Model;
import server.model.Direction;
import server.model.Observable;
import server.model.Observer;

/**
 *
 * @author bratizgut
 */
public class Controller extends Thread implements Observer {

    private final BufferedReader in;
    private final ObjectOutputStream out;

    private final Model model;

    private final int playerNum;
    
    private boolean connected;

    public Controller(BufferedReader in, ObjectOutputStream out, int playerNum, Model model) {
        this.in = in;
        this.out = out;
        this.playerNum = playerNum;
        this.model = model;
        connected = true;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                String line = in.readLine();
                if (line.equals("end")) {
                    model.deleteObserver(this);
                    model.end();
                    interrupt();
                }
                if (line.equals("r")) {
                    if (model.isGameEnd()) {
                        model.setPlayerReady(playerNum);
                        model.update();
                        model.start();
                    }
                    continue;
                }
                if (line.endsWith(" ")) {
                    char dir = line.charAt(0);
                    switch (dir) {
                        case 'w':
                            model.movePlayer(Direction.UP, Direction.NONE, playerNum);
                            break;
                        case 's':
                            model.movePlayer(Direction.DOWN, Direction.NONE, playerNum);
                            break;
                        default:
                            model.movePlayer(Direction.NONE, Direction.NONE, playerNum);
                    }
                } else {
                    char dir = line.charAt(1);
                    switch (dir) {
                        case 'w':
                            model.movePlayer(Direction.NONE, Direction.UP, playerNum);
                            break;
                        case 's':
                            model.movePlayer(Direction.NONE, Direction.DOWN, playerNum);
                            break;
                        default:
                            model.movePlayer(Direction.NONE, Direction.NONE, playerNum);
                    }
                }
            } catch (IOException ex) {
                model.deleteObserver(this);
                model.end();
                break;
            }
        }
    }

    @Override
    public void handleEvent(Observable o) {
        if(connected) {
            try {
                out.writeObject(model.getModelState());
            } catch (IOException ex) {
                connected = false;
                model.end();
            }
        }
    }

}
