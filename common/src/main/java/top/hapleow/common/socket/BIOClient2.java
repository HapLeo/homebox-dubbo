package top.hapleow.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class BIOClient2 {

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int port = 8000;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("客户端启动...");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg = consoleInput.readLine()) != null) {
                writer.println(msg);
                if (Objects.equals(msg, "exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
