package org.p2p;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        try {
            System.out.println("Inform what's the port do you want to run your p2p server:");
            int serverPort = keyboard.nextInt();
            keyboard.nextLine();
            new Server(serverPort).start();

            System.out.println("Do you want to connect in other client? (S/N):");
            if (keyboard.nextLine().equalsIgnoreCase("n")) {
                System.out.println("Finished connection");
                keyboard.close();
            }

            System.out.println("Ip other client:");
            String ip = keyboard.nextLine();
            System.out.println("Port other client:");
            int port = keyboard.nextInt();
            keyboard.nextLine();

            try (Socket client = new Socket(ip, port); PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {
                System.out.println("Client connnected on ip: " + ip + " and port: " + port);
                while (true) {
                    System.out.println("Write your message:");
                    String message = keyboard.nextLine();
                    output.println(message);
                }
            }

        } catch (Exception e) {
            System.out.println("Has a exception during the client execute: " + e.getMessage());
        }
    }
}