package ir.aut.ceit.app.view;

import ir.aut.ceit.app.logic.Game;
import ir.aut.ceit.app.logic.chat.ChatClient;
import ir.aut.ceit.app.logic.chat.ChatServer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private JPanel hostPanel;
    private JPanel guestPanel;
    private String hostPort;
    private String guestPort;
    private String guestIP;
    private boolean isHost;

    public MainFrame() {
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(3400, 2000);
        this.setVisible(true);
        this.setTitle("Battleship War");
        this.setMyMenuBar();
    }

    private void setMyMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenuItem newGame = new JMenuItem("new game");
        newGame.addActionListener(e -> showDialogue());
        file.add(newGame);
        menuBar.add(file);
        menuBar.add(help);
        menuBar.setVisible(true);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    private void showDialogue() {
        JPanel getNamePanel = new JPanel();
        getNamePanel.setLayout(new GridLayout());
        JLabel nameLabel = new JLabel("Name:");
        JPanel hostOrGuestPanel = new JPanel();
        JLabel hostOrGuestLabel = new JLabel("Want to be a host or play as a guest?");
        JRadioButton hostButton = new JRadioButton("Host");
        hostButton.addActionListener(e -> {
            guestIP = null;
            guestPort = null;
            hostPanel = new JPanel();
            hostPanel.setSize(new Dimension(500, 100));
            JLabel portLabel = new JLabel("Port:");
            hostPanel.add(portLabel);
            hostPort = JOptionPane.showInputDialog(null, hostPanel);
            if (hostPort != null && guestIP == null && guestPort == null) {
                isHost = true;
                JPanel startGamePanel = new JPanel();
                startGamePanel.add(new JLabel("Do you want to start the game?"));
                int result = JOptionPane.showConfirmDialog(null, startGamePanel, "Starting...", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) chatGamePanel();
            }
        });
        JRadioButton guestButton = new JRadioButton("Guest");
        guestButton.addActionListener(e -> {
            hostPort = null;
            guestPanel = new JPanel();
            guestPanel.setSize(800, 100);
            JLabel portLabel = new JLabel("Port:");
            guestPanel.add(portLabel);
            JTextField portField = new JTextField(6);
            guestPanel.add(portField);
            JLabel ipLabel = new JLabel("IP:");
            guestPanel.add(ipLabel);
            JTextField ipField = new JTextField(17);
            guestPanel.add(ipField);
            int res = JOptionPane.showConfirmDialog(null, guestPanel, "Plz enter port and ip", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                guestPort = portField.getText();
                guestIP = ipField.getText();
            }
            if (hostPort == null && guestIP != null && guestPort != null) {
                isHost = false;
                JPanel startGamePanel = new JPanel();
                startGamePanel.add(new JLabel("Do you want to start the game?"));
                int result = JOptionPane.showConfirmDialog(null, startGamePanel, "Starting...", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) chatGamePanel();
            }
        });
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(hostButton);
        buttonGroup.add(guestButton);

        getNamePanel.add(nameLabel);
        hostOrGuestPanel.add(hostOrGuestLabel);
        hostOrGuestPanel.add(hostButton);
        hostOrGuestPanel.add(guestButton);
        String name = JOptionPane.showInputDialog(null, getNamePanel);
        if (name != null) JOptionPane.showMessageDialog(null, hostOrGuestPanel);
    }

    private void chatGamePanel() {
        JPanel chatGamePanel = new JPanel();
        JButton chat = new JButton("chat");
   chat.addActionListener(e -> {

       if (isHost) {
           String[] args=new String[2];
           args[0]="192.168.43.109";
           args[1]=hostPort;
           try {
               ChatServer.main(args);
           } catch (IOException e1) {
               e1.printStackTrace();
           }
       }else {
           String[] args=new String[2];
           args[0]=guestIP;
           args[1]=guestPort;
           try {
               ChatClient.main(args);
           } catch (IOException e1) {
               e1.printStackTrace();
           }
       }
   });

        JButton game = new JButton("game");
        game.addActionListener(e -> {
            chatGamePanel.setVisible(false);
            Game.playGame(MainFrame.this);
        });
        chatGamePanel.add(chat);
        chatGamePanel.add(game);
        MainFrame.this.add(chatGamePanel);
        MainFrame.this.setVisible(true);
    }

}