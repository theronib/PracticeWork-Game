package doodlejava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import util.Resource;

public class Frame extends JFrame {

    private static final Dimension SIZE;

    static {
        int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - 100;
        if (screenHeight > 800) {
            screenHeight = 800;
        }
        SIZE = new Dimension(500, screenHeight);
    }
    private GamePanel gamePanel;
    private MainMenuPanel mainMenuPanel;

    public Frame() {
        super();
        this.setIconImage(Resource.getImage("doodle.png"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setSizeWithoutInsets(SIZE);
        this.setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Error " + ex);
        }

        this.setTitle(0);
        this.gamePanel = new GamePanel(SIZE);
        this.gamePanel.setNewGameListener(new NewGameListener() {

            @Override
            public void updateScore(int score) {
                setTitle(score);
            }

            @Override
            public void gameOver() {
                setTitle(0);
            }
        });

        mainMenuPanel = new MainMenuPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        this.add(mainMenuPanel);
        mainMenuPanel.requestFocus();
    }

    private void startGame() {
        this.remove(mainMenuPanel);
        this.add(gamePanel);
        gamePanel.requestFocus();
        this.validate();
        this.repaint();
        JOptionPane.showOptionDialog(null, "Ready...?",
                "Start", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"GO!"}, 0);
        this.gamePanel.start();
    }

    public static void main(String[] args) {
        Frame f = new Frame();
        f.setVisible(true);
    }

    private void setTitle(int points) {
        this.setTitle("Doodle Jump - " + points + " points");
    }

    private void setSizeWithoutInsets(Dimension d) {
        Insets i = this.getInsets();
        this.setSize(d.width + i.left + i.right, d.height + i.top + i.bottom);
    }

    class MainMenuPanel extends JPanel {
        private Image backgroundImage;

        public MainMenuPanel(ActionListener startGameListener) {
            setLayout(new BorderLayout());
            loadBackgroundImage();

         // Створення кнопки "Start Game"
            JButton startButton = new JButton("Start Game");
            startButton.addActionListener(startGameListener);

            // Встановлення відступів для кнопки
            Insets buttonInsets = new Insets(20, 20, 20, 20); // Встановіть потрібні значення відступів
            startButton.setMargin(buttonInsets);


            // Створення кнопки "Exit"
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            // Додавання компонентів до панелі меню
            add(startButton, BorderLayout.NORTH);
            add(exitButton, BorderLayout.SOUTH);
        }

        private void loadBackgroundImage() {
            try {
                backgroundImage = ImageIO.read(new File("bin/images/bg.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}


