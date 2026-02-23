package org.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServeCustomer extends Thread {
    private Socket client;

    public ServeCustomer(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String message;
            while((message = input.readLine()) != null) {
                System.out.println("You received a message: " + message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
