package org.alex.server;

import org.alex.phone.Phone;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Calendar;

public class Server {

    public static void main(String[] args) {
        long now = Calendar.getInstance().getTimeInMillis();
        System.out.println("Starting server...");
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started at " + (Calendar.getInstance().getTimeInMillis() - now) + " ms...");
            if (!serverSocket.isClosed()) {
                while (true) {
                    Phone phone = new Phone(serverSocket);
                    new Thread(() -> {
                        String request = phone.readLine();
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String response = "HELLO FROM SERVER: " + request;
                        phone.writeLine(response);
                        System.out.println(request);
                        try {
                            phone.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
