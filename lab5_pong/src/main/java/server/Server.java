package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.message.InitMessage;
import server.model.Model;

/**
 *
 * @author bratizgut
 */
public class Server extends Thread {

    private final Map<String, Socket> socketMap;
    private ServerSocket serverSocket;
    private final int port;

    ArrayList<modelCreationThread> games;

    private class modelCreationThread extends Thread {

        private final Socket socket1;
        private final Socket socket2;

        private Model model;

        public modelCreationThread(Socket socket1, Socket socket2) {
            this.socket1 = socket1;
            this.socket2 = socket2;
        }

        @Override
        public void run() {
            try {
                InputStream stream = socket1.getInputStream();
                BufferedReader socketReader1 = new BufferedReader(new InputStreamReader(stream));
                ObjectOutputStream socketOutputStream1 = new ObjectOutputStream(socket1.getOutputStream());

                stream = socket2.getInputStream();
                BufferedReader socketReader2 = new BufferedReader(new InputStreamReader(stream));
                ObjectOutputStream socketOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());

                int width = Integer.min(Integer.parseInt(socketReader1.readLine()),
                        Integer.parseInt(socketReader2.readLine()));
                int height = Integer.min(Integer.parseInt(socketReader1.readLine()),
                        Integer.parseInt(socketReader2.readLine()));
                int ballSpeed = Integer.min(Integer.parseInt(socketReader1.readLine()),
                        Integer.parseInt(socketReader2.readLine()));
                int ballRad = Integer.min(Integer.parseInt(socketReader1.readLine()),
                        Integer.parseInt(socketReader2.readLine()));
                int playerSpeed = Integer.min(Integer.parseInt(socketReader1.readLine()),
                        Integer.parseInt(socketReader2.readLine()));
                int paneLength = Integer.min(Integer.parseInt(socketReader1.readLine()),
                        Integer.parseInt(socketReader2.readLine()));
                int paneWidth = Integer.min(Integer.parseInt(socketReader1.readLine()),
                        Integer.parseInt(socketReader2.readLine()));

                socketOutputStream1.writeObject(new InitMessage(width, height, ballRad, paneLength, paneWidth, true));
                socketOutputStream2.writeObject(new InitMessage(width, height, ballRad, paneLength, paneWidth, true));

                model = new Model(width, height, ballSpeed, ballRad, playerSpeed, ballSpeed, paneLength,
                        paneWidth);
                Controller controller1 = new Controller(socketReader1, socketOutputStream1, 1, model);
                controller1.start();
                Controller controller2 = new Controller(socketReader2, socketOutputStream2, 2, model);
                controller2.start();
                model.addObserver(controller1);
                model.addObserver(controller2);
                model.start();
            } catch (IOException ex) {
                if (socket1.isConnected()) {
                    try {
                        ObjectOutputStream socketOutputStream = new ObjectOutputStream(socket1.getOutputStream());
                        socketOutputStream.writeObject(new InitMessage(0, 0, 0, 0, 0, true));
                    } catch (IOException ex1) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                if (socket2.isConnected()) {
                    try {
                        ObjectOutputStream socketOutputStream = new ObjectOutputStream(socket2.getOutputStream());
                        socketOutputStream.writeObject(new InitMessage(0, 0, 0, 0, 0, true));
                    } catch (IOException ex1) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
        }

        public void closeGame() {
            model.end();
            try {
                socket1.close();
                socket2.close();
            } catch (IOException ex) {
                return;
            }
        }
    }

    public Server(int port) {
        socketMap = new HashMap<>();
        this.port = port;
        games = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (!isInterrupted()) {
                try {
                    Socket fromClientSocket = serverSocket.accept();
                    InputStream stream = fromClientSocket.getInputStream();
                    BufferedReader socketReader = new BufferedReader(new InputStreamReader(stream));
                    String gameId = socketReader.readLine();
                    if (socketMap.containsKey(gameId)) {
                        modelCreationThread creationThread = new modelCreationThread(socketMap.get(gameId), fromClientSocket);
                        games.add(creationThread);
                        creationThread.start();
                        socketMap.remove(gameId);
                    } else {
                        socketMap.put(gameId, fromClientSocket);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeServer() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (modelCreationThread i : games) {
            i.closeGame();
        }
        interrupt();
        try {
            this.join();
            for (modelCreationThread i : games) {
                i.join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
