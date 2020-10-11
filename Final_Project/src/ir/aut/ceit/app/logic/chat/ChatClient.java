package ir.aut.ceit.app.logic.chat;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ChatClient extends Frame implements Runnable {
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private TextArea output;
    private TextField input;
    private Thread listener;

    ChatClient(String title, InputStream inputStream, OutputStream outputStream) {
        super(title);
        this.setPreferredSize(new Dimension(1000 , 1500));
        this.inputStream = new DataInputStream(new BufferedInputStream(inputStream));
        this.outputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
        setLayout(new BorderLayout());
        add("Center", output = new TextArea());
        output.setEditable(false);
        add("South", input = new TextField());
        pack();
        setVisible(true);
        input.requestFocus();
        listener = new Thread(this);
        listener.start();
    }

    public void run() {
        try {
            while (true) {
                String line = inputStream.readUTF();
                output.append(line + "\n");
                output.setFont(new Font("Georgian" , Font.PLAIN , 40));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            listener = null;
            input.setVisible(false);
            validate();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean handleEvent(Event e) {
        if ((e.target == input) && (e.id == Event.ACTION_EVENT)) {
            try {
                outputStream.writeUTF((String) e.arg);
                outputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
                listener.interrupt();
            }
            input.setText(" ");
            return true;
        } else if ((e.target == this) && (e.id == Event.WINDOW_DESTROY)) {
            if (listener != null)
                listener.interrupt();
            setVisible(false);
            return true;
        }
        return
                super.handleEvent(e);
    }

    public static void main(String args[]) throws IOException {
        Socket s = new Socket(args[0], Integer.parseInt(args[1]));
        new ChatClient("chat " + args[0] + ":" + args[1],
                s.getInputStream(), s.getOutputStream());
    }
}
