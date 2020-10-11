package ir.aut.ceit.app.logic;

import ir.aut.ceit.app.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class Game {
    private final static int size = 10;
    private static JPanel checkeredPane1, checkeredPane2;
    private static JButton[][] buttons1, buttons2;
    private static JButton[] smallShip11 = new JButton[1];
    private static JButton[] smallShip12 = new JButton[1];
    private static JButton[] smallShip13 = new JButton[1];
    private static JButton[] smallShip14 = new JButton[1];

    private static JButton[] smallShip21 = new JButton[1];
    private static JButton[] smallShip22 = new JButton[1];
    private static JButton[] smallShip23 = new JButton[1];
    private static JButton[] smallShip24 = new JButton[1];

    private static JButton[] mediumShip11 = new JButton[2];
    private static JButton[] mediumShip12 = new JButton[2];
    private static JButton[] mediumShip13 = new JButton[2];

    private static JButton[] mediumShip21 = new JButton[2];
    private static JButton[] mediumShip22 = new JButton[2];
    private static JButton[] mediumShip23 = new JButton[2];

    private static JButton[] bigShip11 = new JButton[3];
    private static JButton[] bigShip12 = new JButton[3];

    private static JButton[] bigShip21 = new JButton[3];
    private static JButton[] bigShip22 = new JButton[3];

    private static JButton[] hugeShip1 = new JButton[4];

    private static JButton[] hugeShip2 = new JButton[4];

    private static JPanel shipsPanel1, shipsPanel2;
    private static int shipsPlaced;
    private static ArrayList<Integer> firstParamsOfShips1 = new ArrayList<>();
    private static ArrayList<Integer> secondParamsOfShips1 = new ArrayList<>();
    private static ArrayList<Integer> firstParamsOfShips2 = new ArrayList<>();
    private static ArrayList<Integer> secondParamsOfShips2 = new ArrayList<>();
    private static boolean turn1, turn2;
    private static int shipsDestroyed1, shipsDestroyed2;

    public static void playGame(MainFrame mainFrame) {
        checkeredPane1 = new JPanel(new GridLayout(size, size));
        checkeredPane2 = new JPanel(new GridLayout(size, size));
        buttons1 = new JButton[size][size];
        buttons2 = new JButton[size][size];

        setBoard(mainFrame, checkeredPane1, buttons1);
        shipsPanel1 = new JPanel();
        shipsPanel1.setPreferredSize(new Dimension(500, 900));
        shipsPanel1.setBackground(new Color(200, 200, 200));
        makeShip(mainFrame, smallShip11, shipsPanel1, buttons1);
        makeShip(mainFrame, smallShip12, shipsPanel1, buttons1);
        makeShip(mainFrame, smallShip13, shipsPanel1, buttons1);
        makeShip(mainFrame, smallShip14, shipsPanel1, buttons1);

        JPanel someSpace1 = new JPanel();
        someSpace1.setPreferredSize(new Dimension(460, 80));
        someSpace1.setBackground(new Color(200, 200, 200));
        shipsPanel1.add(someSpace1);
        makeShip(mainFrame, mediumShip11, shipsPanel1, buttons1);
        makeShip(mainFrame, mediumShip12, shipsPanel1, buttons1);
        makeShip(mainFrame, mediumShip13, shipsPanel1, buttons1);

        JPanel someSpace3 = new JPanel();
        someSpace3.setPreferredSize(new Dimension(460, 80));
        someSpace3.setBackground(new Color(200, 200, 200));
        shipsPanel1.add(someSpace3);
        makeShip(mainFrame, bigShip11, shipsPanel1, buttons1);
        makeShip(mainFrame, bigShip12, shipsPanel1, buttons1);

        JPanel someSpace5 = new JPanel();
        someSpace5.setPreferredSize(new Dimension(460, 80));
        someSpace5.setBackground(new Color(200, 200, 200));
        shipsPanel1.add(someSpace5);
        makeShip(mainFrame, hugeShip1, shipsPanel1, buttons1);

        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(500, 900));

        shipsPanel2 = new JPanel();
        shipsPanel2.setPreferredSize(new Dimension(500, 900));
        shipsPanel2.setBackground(new Color(200, 200, 200));
        makeShip(mainFrame, smallShip21, shipsPanel2, buttons2);
        makeShip(mainFrame, smallShip22, shipsPanel2, buttons2);
        makeShip(mainFrame, smallShip23, shipsPanel2, buttons2);
        makeShip(mainFrame, smallShip24, shipsPanel2, buttons2);

        JPanel someSpace2 = new JPanel();
        someSpace2.setPreferredSize(new Dimension(460, 80));
        someSpace2.setBackground(new Color(200, 200, 200));
        shipsPanel2.add(someSpace2);
        makeShip(mainFrame, mediumShip21, shipsPanel2, buttons2);
        makeShip(mainFrame, mediumShip22, shipsPanel2, buttons2);
        makeShip(mainFrame, mediumShip23, shipsPanel2, buttons2);

        JPanel someSpace4 = new JPanel();
        someSpace4.setPreferredSize(new Dimension(460, 80));
        someSpace4.setBackground(new Color(200, 200, 200));
        shipsPanel2.add(someSpace4);
        makeShip(mainFrame, bigShip21, shipsPanel2, buttons2);
        makeShip(mainFrame, bigShip22, shipsPanel2, buttons2);

        JPanel someSpace6 = new JPanel();
        someSpace6.setPreferredSize(new Dimension(460, 80));
        someSpace6.setBackground(new Color(200, 200, 200));
        shipsPanel2.add(someSpace6);
        makeShip(mainFrame, hugeShip2, shipsPanel2, buttons2);

        mainFrame.add(shipsPanel1);
        mainFrame.add(voidPanel);
        mainFrame.add(shipsPanel2);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (!Objects.equals(buttons1[i][j].getBackground(), Color.WHITE)) {
                            firstParamsOfShips1.add(i);
                            secondParamsOfShips1.add(j);
                            buttons1[i][j].setBackground(Color.WHITE);
                        }
                        if (!Objects.equals(buttons2[i][j].getBackground(), Color.WHITE)) {
                            firstParamsOfShips2.add(i);
                            secondParamsOfShips2.add(j);
                            buttons2[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Its left player turn to play");
                turn1 = true;
                GameThread1 thread1=new GameThread1();
                thread1.start();
                GameThread2 thread2=new GameThread2();
                thread2.start();
            }
        });

        setBoard(mainFrame, checkeredPane2, buttons2);
        mainFrame.add(startButton);
        mainFrame.repaint();
    }

    private static void setBoard(MainFrame main, JPanel checkeredPane, JButton[][] buttons) {
        checkeredPane.setPreferredSize(new Dimension(900, 900));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(new Dimension(90, 90));
                buttons[i][j].setBackground(Color.WHITE);
                checkeredPane.add(buttons[i][j]);
            }
        }
        main.add(checkeredPane);
        main.repaint();
    }

    private static void makeShip(MainFrame main, JButton[] ship, JPanel shipsPanel, JButton[][] gameButtons) {
        JPanel myShip;
        if (ship.length == 4) myShip = new JPanel(new GridLayout(1, ship.length));
        else myShip = new JPanel(new GridLayout(ship.length, 1));
        for (int j = 0; j < ship.length; j++) {
            ship[j] = new JButton();
            ship[j].setPreferredSize(new Dimension(80, 80));
            if (ship.length == 1) ship[j].setBackground(Color.CYAN);
            else if (ship.length == 2) ship[j].setBackground(Color.BLUE);
            else if (ship.length == 3) ship[j].setBackground(Color.GREEN);
            else if (ship.length == 4) ship[j].setBackground(Color.YELLOW);
            if (ship.length == 1)
                ship[0].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ship[0].setBackground(new Color(200, 200, 200));
                        for (int k = 0; k < size; k++) {
                            for (int l = 0; l < size; l++) {
                                int finalK = k;
                                int finalL = l;
                                gameButtons[k][l].addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        boolean condition1 = Objects.equals(gameButtons[finalK][finalL].getBackground(), Color.WHITE);
                                        boolean condition2 = true, condition3 = true, condition4 = true, condition5 = true, condition6 = true;
                                        boolean condition7 = true, condition8 = true, condition9 = true;
                                        if (finalK > 0 && finalL > 0)
                                            condition2 = Objects.equals(gameButtons[finalK - 1][finalL - 1].getBackground(), Color.WHITE);
                                        if (finalK > 0)
                                            condition3 = Objects.equals(gameButtons[finalK - 1][finalL].getBackground(), Color.WHITE);
                                        if (finalL > 0)
                                            condition4 = Objects.equals(gameButtons[finalK][finalL - 1].getBackground(), Color.WHITE);
                                        if (finalK < 9 && finalL < 9)
                                            condition5 = Objects.equals(gameButtons[finalK + 1][finalL + 1].getBackground(), Color.WHITE);
                                        if (finalK < 9)
                                            condition6 = Objects.equals(gameButtons[finalK + 1][finalL].getBackground(), Color.WHITE);
                                        if (finalL < 9)
                                            condition7 = Objects.equals(gameButtons[finalK][finalL + 1].getBackground(), Color.WHITE);
                                        if (finalK > 0 && finalL < 9)
                                            condition8 = Objects.equals(gameButtons[finalK - 1][finalL + 1].getBackground(), Color.WHITE);
                                        if (finalK < 9 && finalL > 0)
                                            condition9 = Objects.equals(gameButtons[finalK + 1][finalL - 1].getBackground(), Color.WHITE);
                                        if (condition1 && condition2 && condition3 && condition4 && condition5 && condition6 && condition7 && condition8 && condition9) {
                                            gameButtons[finalK][finalL].setBackground(Color.CYAN);
                                            shipsPlaced += 1;
                                            removeButtonListeners(gameButtons);
                                        }
                                    }
                                });
                            }
                        }
                        ActionListener[] als = ship[0].getActionListeners();
                        for (int i = 0; i < als.length; i++) {
                            ship[0].removeActionListener(als[i]);
                        }
                    }
                });
            if (ship.length == 2) {
                for (int i = 0; i < ship.length; i++) {
                    if (ship[i] != null) ship[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            ship[0].setBackground(new Color(200, 200, 200));
                            ship[1].setBackground(new Color(200, 200, 200));
                            for (int k = 0; k < size; k++) {
                                for (int l = 0; l < size; l++) {
                                    int finalK = k;
                                    int finalL = l;
                                    gameButtons[k][l].addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            int secondTargetK;
                                            if (finalK == size - 1) secondTargetK = size - 2;
                                            else secondTargetK = finalK + 1;
                                            boolean condition1 = Objects.equals(gameButtons[finalK][finalL].getBackground(), Color.WHITE);
                                            boolean condition10 = Objects.equals(gameButtons[secondTargetK][finalL].getBackground(), Color.WHITE);
                                            boolean condition2 = true, condition3 = true, condition4 = true, condition5 = true, condition6 = true;
                                            boolean condition7 = true, condition8 = true, condition9 = true;
                                            boolean condition20 = true, condition30 = true, condition40 = true, condition50 = true, condition60 = true;
                                            boolean condition70 = true, condition80 = true, condition90 = true;
                                            if (finalK > 0 && finalL > 0)
                                                condition2 = Objects.equals(gameButtons[finalK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (finalK > 0)
                                                condition3 = Objects.equals(gameButtons[finalK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition4 = Objects.equals(gameButtons[finalK][finalL - 1].getBackground(), Color.WHITE);
                                            if (finalK < 9 && finalL < 9)
                                                condition5 = Objects.equals(gameButtons[finalK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK < 9)
                                                condition6 = Objects.equals(gameButtons[finalK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition7 = Objects.equals(gameButtons[finalK][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK > 0 && finalL < 9)
                                                condition8 = Objects.equals(gameButtons[finalK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK < 9 && finalL > 0)
                                                condition9 = Objects.equals(gameButtons[finalK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean firstTargetConditions1 = condition2 && condition3 && condition4 && condition5;
                                            boolean firstTargetConditions2 = condition6 && condition7 && condition8 && condition9;
                                            boolean firstTargetConditions = firstTargetConditions1 && firstTargetConditions2;

                                            if (secondTargetK > 0 && finalL > 0)
                                                condition20 = Objects.equals(gameButtons[secondTargetK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (secondTargetK > 0)
                                                condition30 = Objects.equals(gameButtons[secondTargetK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition40 = Objects.equals(gameButtons[secondTargetK][finalL - 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9 && finalL < 9)
                                                condition50 = Objects.equals(gameButtons[secondTargetK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9)
                                                condition60 = Objects.equals(gameButtons[secondTargetK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition70 = Objects.equals(gameButtons[secondTargetK][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK > 0 && finalL < 9)
                                                condition80 = Objects.equals(gameButtons[secondTargetK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9 && finalL > 0)
                                                condition90 = Objects.equals(gameButtons[secondTargetK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean secondTargetConditions1 = condition20 && condition30 && condition40 && condition50;
                                            boolean secondTargetConditions2 = condition60 && condition70 && condition80 && condition90;
                                            boolean secondTargetConditions = secondTargetConditions1 && secondTargetConditions2;


                                            if (condition1 && firstTargetConditions && condition10 && secondTargetConditions) {
                                                gameButtons[finalK][finalL].setBackground(Color.BLUE);
                                                gameButtons[secondTargetK][finalL].setBackground(Color.BLUE);
                                                shipsPlaced += 1;
                                                removeButtonListeners(gameButtons);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
            if (ship.length == 3) {
                for (int i = 0; i < ship.length; i++) {
                    if (ship[i] != null) ship[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            ship[0].setBackground(new Color(200, 200, 200));
                            ship[1].setBackground(new Color(200, 200, 200));
                            ship[2].setBackground(new Color(200, 200, 200));
                            for (int k = 0; k < size; k++) {
                                for (int l = 0; l < size; l++) {
                                    int finalK = k;
                                    int finalL = l;
                                    gameButtons[k][l].addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            int secondTargetK, thirdTargetK;
                                            if (finalK == size - 1 || finalK == size - 2) {
                                                secondTargetK = finalK - 1;
                                                thirdTargetK = finalK - 2;
                                            } else {
                                                secondTargetK = finalK + 1;
                                                thirdTargetK = finalK + 2;
                                            }
                                            boolean condition1 = Objects.equals(gameButtons[finalK][finalL].getBackground(), Color.WHITE);
                                            boolean condition10 = Objects.equals(gameButtons[secondTargetK][finalL].getBackground(), Color.WHITE);
                                            boolean condition100 = Objects.equals(gameButtons[thirdTargetK][finalL].getBackground(), Color.WHITE);
                                            boolean condition2 = true, condition3 = true, condition4 = true, condition5 = true, condition6 = true;
                                            boolean condition7 = true, condition8 = true, condition9 = true;
                                            boolean condition20 = true, condition30 = true, condition40 = true, condition50 = true, condition60 = true;
                                            boolean condition70 = true, condition80 = true, condition90 = true;
                                            boolean condition200 = true, condition300 = true, condition400 = true, condition500 = true, condition600 = true;
                                            boolean condition700 = true, condition800 = true, condition900 = true;
                                            if (finalK > 0 && finalL > 0)
                                                condition2 = Objects.equals(gameButtons[finalK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (finalK > 0)
                                                condition3 = Objects.equals(gameButtons[finalK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition4 = Objects.equals(gameButtons[finalK][finalL - 1].getBackground(), Color.WHITE);
                                            if (finalK < 9 && finalL < 9)
                                                condition5 = Objects.equals(gameButtons[finalK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK < 9)
                                                condition6 = Objects.equals(gameButtons[finalK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition7 = Objects.equals(gameButtons[finalK][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK > 0 && finalL < 9)
                                                condition8 = Objects.equals(gameButtons[finalK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK < 9 && finalL > 0)
                                                condition9 = Objects.equals(gameButtons[finalK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean firstTargetConditions1 = condition1 && condition2 && condition3 && condition4 && condition5;
                                            boolean firstTargetConditions2 = condition6 && condition7 && condition8 && condition9;
                                            boolean firstTargetConditions = firstTargetConditions1 && firstTargetConditions2;

                                            if (secondTargetK > 0 && finalL > 0)
                                                condition20 = Objects.equals(gameButtons[secondTargetK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (secondTargetK > 0)
                                                condition30 = Objects.equals(gameButtons[secondTargetK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition40 = Objects.equals(gameButtons[secondTargetK][finalL - 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9 && finalL < 9)
                                                condition50 = Objects.equals(gameButtons[secondTargetK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9)
                                                condition60 = Objects.equals(gameButtons[secondTargetK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition70 = Objects.equals(gameButtons[secondTargetK][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK > 0 && finalL < 9)
                                                condition80 = Objects.equals(gameButtons[secondTargetK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9 && finalL > 0)
                                                condition90 = Objects.equals(gameButtons[secondTargetK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean secondTargetConditions1 = condition10 && condition20 && condition30 && condition40 && condition50;
                                            boolean secondTargetConditions2 = condition60 && condition70 && condition80 && condition90;
                                            boolean secondTargetConditions = secondTargetConditions1 && secondTargetConditions2;

                                            if (thirdTargetK > 0 && finalL > 0)
                                                condition200 = Objects.equals(gameButtons[thirdTargetK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK > 0)
                                                condition300 = Objects.equals(gameButtons[thirdTargetK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition400 = Objects.equals(gameButtons[thirdTargetK][finalL - 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK < 9 && finalL < 9)
                                                condition500 = Objects.equals(gameButtons[thirdTargetK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK < 9)
                                                condition600 = Objects.equals(gameButtons[thirdTargetK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition700 = Objects.equals(gameButtons[thirdTargetK][finalL + 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK > 0 && finalL < 9)
                                                condition800 = Objects.equals(gameButtons[thirdTargetK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK < 9 && finalL > 0)
                                                condition900 = Objects.equals(gameButtons[thirdTargetK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean thirdTargetConditions1 = condition100 && condition200 && condition300 && condition400 && condition500;
                                            boolean thirdTargetConditions2 = condition600 && condition700 && condition800 && condition900;
                                            boolean thirdTargetConditions = thirdTargetConditions1 && thirdTargetConditions2;


                                            if (firstTargetConditions && secondTargetConditions && thirdTargetConditions) {
                                                gameButtons[finalK][finalL].setBackground(Color.GREEN);
                                                gameButtons[secondTargetK][finalL].setBackground(Color.GREEN);
                                                gameButtons[thirdTargetK][finalL].setBackground(Color.GREEN);
                                                shipsPlaced += 1;
                                                removeButtonListeners(gameButtons);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
            if (ship.length == 4) {
                for (int i = 0; i < ship.length; i++) {
                    if (ship[i] != null) ship[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            ship[0].setBackground(new Color(200, 200, 200));
                            ship[1].setBackground(new Color(200, 200, 200));
                            ship[2].setBackground(new Color(200, 200, 200));
                            ship[3].setBackground(new Color(200, 200, 200));
                            for (int k = 0; k < size; k++) {
                                for (int l = 0; l < size; l++) {
                                    int finalK = k;
                                    int finalL = l;
                                    gameButtons[k][l].addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            int secondTargetK, thirdTargetK, forthTargetK;
                                            if (finalK == size - 1 || finalK == size - 2 || finalK == size - 3) {
                                                secondTargetK = finalK - 1;
                                                thirdTargetK = finalK - 2;
                                                forthTargetK = finalK - 3;
                                            } else {
                                                secondTargetK = finalK + 1;
                                                thirdTargetK = finalK + 2;
                                                forthTargetK = finalK + 3;
                                            }
                                            boolean condition1 = Objects.equals(gameButtons[finalK][finalL].getBackground(), Color.WHITE);
                                            boolean condition10 = Objects.equals(gameButtons[secondTargetK][finalL].getBackground(), Color.WHITE);
                                            boolean condition100 = Objects.equals(gameButtons[thirdTargetK][finalL].getBackground(), Color.WHITE);
                                            boolean condition1000 = Objects.equals(gameButtons[forthTargetK][finalL].getBackground(), Color.WHITE);
                                            boolean condition2 = true, condition3 = true, condition4 = true, condition5 = true, condition6 = true;
                                            boolean condition7 = true, condition8 = true, condition9 = true;
                                            boolean condition20 = true, condition30 = true, condition40 = true, condition50 = true, condition60 = true;
                                            boolean condition70 = true, condition80 = true, condition90 = true;
                                            boolean condition200 = true, condition300 = true, condition400 = true, condition500 = true, condition600 = true;
                                            boolean condition700 = true, condition800 = true, condition900 = true;
                                            boolean condition2000 = true, condition3000 = true, condition4000 = true, condition5000 = true, condition6000 = true;
                                            boolean condition7000 = true, condition8000 = true, condition9000 = true;
                                            if (finalK > 0 && finalL > 0)
                                                condition2 = Objects.equals(gameButtons[finalK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (finalK > 0)
                                                condition3 = Objects.equals(gameButtons[finalK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition4 = Objects.equals(gameButtons[finalK][finalL - 1].getBackground(), Color.WHITE);
                                            if (finalK < 9 && finalL < 9)
                                                condition5 = Objects.equals(gameButtons[finalK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK < 9)
                                                condition6 = Objects.equals(gameButtons[finalK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition7 = Objects.equals(gameButtons[finalK][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK > 0 && finalL < 9)
                                                condition8 = Objects.equals(gameButtons[finalK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (finalK < 9 && finalL > 0)
                                                condition9 = Objects.equals(gameButtons[finalK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean firstTargetConditions1 = condition1 && condition2 && condition3 && condition4 && condition5;
                                            boolean firstTargetConditions2 = condition6 && condition7 && condition8 && condition9;
                                            boolean firstTargetConditions = firstTargetConditions1 && firstTargetConditions2;

                                            if (secondTargetK > 0 && finalL > 0)
                                                condition20 = Objects.equals(gameButtons[secondTargetK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (secondTargetK > 0)
                                                condition30 = Objects.equals(gameButtons[secondTargetK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition40 = Objects.equals(gameButtons[secondTargetK][finalL - 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9 && finalL < 9)
                                                condition50 = Objects.equals(gameButtons[secondTargetK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9)
                                                condition60 = Objects.equals(gameButtons[secondTargetK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition70 = Objects.equals(gameButtons[secondTargetK][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK > 0 && finalL < 9)
                                                condition80 = Objects.equals(gameButtons[secondTargetK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (secondTargetK < 9 && finalL > 0)
                                                condition90 = Objects.equals(gameButtons[secondTargetK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean secondTargetConditions1 = condition10 && condition20 && condition30 && condition40 && condition50;
                                            boolean secondTargetConditions2 = condition60 && condition70 && condition80 && condition90;
                                            boolean secondTargetConditions = secondTargetConditions1 && secondTargetConditions2;

                                            if (thirdTargetK > 0 && finalL > 0)
                                                condition200 = Objects.equals(gameButtons[thirdTargetK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK > 0)
                                                condition300 = Objects.equals(gameButtons[thirdTargetK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition400 = Objects.equals(gameButtons[thirdTargetK][finalL - 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK < 9 && finalL < 9)
                                                condition500 = Objects.equals(gameButtons[thirdTargetK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK < 9)
                                                condition600 = Objects.equals(gameButtons[thirdTargetK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition700 = Objects.equals(gameButtons[thirdTargetK][finalL + 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK > 0 && finalL < 9)
                                                condition800 = Objects.equals(gameButtons[thirdTargetK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (thirdTargetK < 9 && finalL > 0)
                                                condition900 = Objects.equals(gameButtons[thirdTargetK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean thirdTargetConditions1 = condition100 && condition200 && condition300 && condition400 && condition500;
                                            boolean thirdTargetConditions2 = condition600 && condition700 && condition800 && condition900;
                                            boolean thirdTargetConditions = thirdTargetConditions1 && thirdTargetConditions2;

                                            if (forthTargetK > 0 && finalL > 0)
                                                condition2000 = Objects.equals(gameButtons[forthTargetK - 1][finalL - 1].getBackground(), Color.WHITE);
                                            if (forthTargetK > 0)
                                                condition3000 = Objects.equals(gameButtons[forthTargetK - 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL > 0)
                                                condition4000 = Objects.equals(gameButtons[forthTargetK][finalL - 1].getBackground(), Color.WHITE);
                                            if (forthTargetK < 9 && finalL < 9)
                                                condition5000 = Objects.equals(gameButtons[forthTargetK + 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (forthTargetK < 9)
                                                condition6000 = Objects.equals(gameButtons[forthTargetK + 1][finalL].getBackground(), Color.WHITE);
                                            if (finalL < 9)
                                                condition7000 = Objects.equals(gameButtons[forthTargetK][finalL + 1].getBackground(), Color.WHITE);
                                            if (forthTargetK > 0 && finalL < 9)
                                                condition8000 = Objects.equals(gameButtons[forthTargetK - 1][finalL + 1].getBackground(), Color.WHITE);
                                            if (forthTargetK < 9 && finalL > 0)
                                                condition9000 = Objects.equals(gameButtons[forthTargetK + 1][finalL - 1].getBackground(), Color.WHITE);
                                            boolean forthTargetConditions1 = condition1000 && condition2000 && condition3000 && condition4000 && condition5000;
                                            boolean forthTargetConditions2 = condition6000 && condition7000 && condition8000 && condition9000;
                                            boolean forthTargetConditions = forthTargetConditions1 && forthTargetConditions2;

                                            if (firstTargetConditions && secondTargetConditions && thirdTargetConditions && forthTargetConditions) {
                                                gameButtons[finalK][finalL].setBackground(Color.YELLOW);
                                                gameButtons[secondTargetK][finalL].setBackground(Color.YELLOW);
                                                gameButtons[thirdTargetK][finalL].setBackground(Color.YELLOW);
                                                gameButtons[forthTargetK][finalL].setBackground(Color.YELLOW);
                                                shipsPlaced += 1;
                                                removeButtonListeners(gameButtons);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
            myShip.add(ship[j]);
        }
        shipsPanel.add(myShip);
        JPanel someSpace = new JPanel();
        someSpace.setBackground(new Color(200, 200, 200));
        shipsPanel.add(someSpace);
    }

    private static void removeButtonListeners(JButton[][] gameButtons) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ActionListener[] als = gameButtons[i][j].getActionListeners();
                for (int m = 0; m < als.length; m++) {
                    gameButtons[i][j].removeActionListener(als[m]);
                }
            }
        }
    }

    private static void setActionListenersForButtons(JButton[][] buttons, ArrayList<Integer> firstParams, ArrayList<Integer> secondParams) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < firstParams.size(); k++) {
                    if (i == firstParams.get(k) && j == secondParams.get(k)) {
                        int finalI = i;
                        int finalJ = j;
                        buttons[i][j].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                buttons[finalI][finalJ].setBackground(Color.RED);
                                if (turn1) turn1=false;
                                else  turn1=true;
                                if (buttons==buttons1) shipsDestroyed1++;
                                else if (buttons==buttons2) shipsDestroyed2++;
                                if (shipsDestroyed1==20) JOptionPane.showMessageDialog(null,"right win");
                                if (shipsDestroyed2==20) JOptionPane.showMessageDialog(null,"left win");
                                removeButtonListeners(buttons);
                            }
                        });
                    } else {
                        int finalJ1 = j;
                        int finalI1 = i;
                        buttons[i][j].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                buttons[finalI1][finalJ1].setBackground(Color.BLACK);
                                if (turn1) turn1=false;
                                else  turn1=true;
                                removeButtonListeners(buttons);
                            }
                        });
                    }
                }
            }
        }
    }
    private static class GameThread1 extends Thread{
        public void run() {
            while (turn1){
                removeButtonListeners(buttons1);
                setActionListenersForButtons(buttons2,firstParamsOfShips2,secondParamsOfShips2);
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static class GameThread2 extends Thread{
        public void run() {
while (!turn1){
    removeButtonListeners(buttons2);
    setActionListenersForButtons(buttons1,firstParamsOfShips1,secondParamsOfShips1);
    try {
        sleep(10);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
        }
    }
}
