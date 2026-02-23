package org.p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    ServerSocket server;

    public Server(int port) throws IOException {
        try {
            server = new ServerSocket(port);
            System.out.println("Server is runner on port: " + port);
        } catch (IOException e) {
            System.out.println("Has a exception during start the server: " + e.getMessage());
        }
    }

    public void run() {
        try {
            while(true) {
                Socket client = server.accept();
                new ServeCustomer(client).start();
            }
        } catch (IOException e) {
            System.out.println("Has a exception during accept the client on server: " + e.getMessage());
        }
    }
}
