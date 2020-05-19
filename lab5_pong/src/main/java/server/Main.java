package server;

import java.util.Scanner;

/**
 *
 * @author bratizgut
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server(1777);
        server.start();
        Scanner in = new Scanner(System.in);

        in.nextLine();

        server.closeServer();

        in.close();
    }
}
