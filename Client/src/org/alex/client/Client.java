package org.alex.client;

import org.alex.phone.Phone;

import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        String ipAddress = "127.0.0.1";
        int ipPort = 8000;

        try (Phone phone = new Phone(ipAddress, ipPort)) {
            phone.writeLine("Request to server");
            String response = phone.readLine();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
