package ir.aut.ceit.app.logic.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static ServerSocket server;
    private static Socket client;

    private ChatServer(int port) throws IOException {
        server = new ServerSocket(port);
        while (true) {
            client = server.accept();
            System.out.println("Accepted from " + client.getInetAddress());
            ChatHandler chatHandler = new ChatHandler(client);
            chatHandler.start();
            if (client != null) {
                break;
            }
        }
    }

    public static void main(String args[]) throws IOException {
        new ChatServer(Integer.parseInt(args[1]));
        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        new ChatClient("chat " + args[0] + ":" + args[1], socket.getInputStream(), socket.getOutputStream());
        while (true) {
            client = server.accept();
            System.out.println("Accepted from " + client.getInetAddress());
            ChatHandler chatHandler = new ChatHandler(client);
            chatHandler.start();
            if (client != null) {
                break;
            }
        }
    }
}
