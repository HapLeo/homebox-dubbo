package top.hapleow.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {

        int port = 8000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器启动...");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Executors.newFixedThreadPool(1).execute(() -> {
                    try {
                        String client = socket.getInetAddress() + ":" + socket.getPort();
                        System.out.println("新的客户已连接：" + client);

                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String msg = null;
                        // 这里从流中读取的操作是阻塞的，即NIO,只有读到了内容才继续执行，否则一直阻塞在这里
                        while ((msg = reader.readLine()) != null) {
                            System.out.println(client + ":" + msg);
                            if (Objects.equals(msg, "exit")) {
                                System.out.println("当前socket关闭...");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        // 关闭socket，代码省略
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
