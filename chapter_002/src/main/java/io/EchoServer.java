package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private final static String EXIT = "msg=Exit";
    private final static String HELLO = "msg=Hello";

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean isTurnOn = true;
            while (isTurnOn) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains(HELLO)) {
                            out.write("Hello, dear friend!".getBytes());
                        } else if (str.contains(EXIT)) {
                            isTurnOn = false;
                            break;
                        } else {
                            out.write(str.split("=")[1].replaceFirst(" HTTP/1.1", "").getBytes());
                        }
                    }

                }
            }
        }
    }
}
