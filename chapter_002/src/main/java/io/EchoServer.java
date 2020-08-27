package io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private final static String BYE = "Bye";
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
                        if (str.contains(BYE)) {
                            isTurnOn = false;
                            break;
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
