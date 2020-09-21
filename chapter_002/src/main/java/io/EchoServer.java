package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {

    private final static String EXIT = "msg=Exit";
    private final static String HELLO = "msg=Hello";
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());


    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9000);
            boolean isTurnOn = true;
            while (isTurnOn) {
                Socket socket = server.accept();
                try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains(HELLO)) {
                            out.writeUTF("Hello, dear friend!");
                            out.flush();
                        } else if (str.contains(EXIT)) {
                            isTurnOn = false;
                            break;
                        } else {
                            out.write(str.split("=")[1].replaceFirst(" HTTP/1.1", "").getBytes());
                        }
                    }

                }
            }
        } catch (Exception e) {
            LOG.error("Exception in socket", e);
        }
    }
}

