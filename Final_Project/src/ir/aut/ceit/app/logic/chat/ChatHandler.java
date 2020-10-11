package ir.aut.ceit.app.logic.chat;

import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class ChatHandler extends Thread {
    private Socket socket;
    private DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private static final Vector handlers = new Vector();

    ChatHandler(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void run() {
        try {
            handlers.addElement(this);
            while (true) {
                String msg = inputStream.readUTF();
                broadcast(msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            handlers.removeElement(this);
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void broadcast(String message) {
        synchronized (handlers) {
            Enumeration enumeration = handlers.elements();
            while (enumeration.hasMoreElements()) {
                ChatHandler chatHandler = (ChatHandler) enumeration.nextElement();
                try {
                    synchronized (chatHandler.outputStream) {
                        chatHandler.outputStream.writeUTF(message);
                    }
                    chatHandler.outputStream.flush();
                } catch (IOException ex) {
                    chatHandler.interrupt();
                }
            }
        }
    }
}
