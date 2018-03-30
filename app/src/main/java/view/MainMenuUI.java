/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Toasty Studios
 */
public class MainMenuUI extends JFrame {

    private static final String CLOSE_MESSAGE = "<html><font color=#ffffdd>Are you sure you want to exit the game?</font>";
    private JLabel header;
    private JLabel subText;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton optionsButton;
    private JButton exitButton;
    private JDialog optionsUI;

    public MainMenuUI() {
        super();
        initUI();
    }

    private void initUI() {
        try {
            UIManager.put("OptionPane.background", Color.DARK_GRAY);
            UIManager.put("Panel.background", Color.DARK_GRAY);
            UIManager.put("Button.background", Color.DARK_GRAY);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalArgumentException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
            System.out.println("The Main UI Look and Feel exploded!");
        }
        setTitle("Last Dawn");
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setBackgroundImage();
        initHeader();
        initButtons();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(rootPane,
                        CLOSE_MESSAGE, "Warning",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        setVisible(true);
    }

    private void setBackgroundImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(MainMenuUI.class.getResourceAsStream("/bg/mainmenu.jpg"));
        } catch (IOException ex) {
            System.out.println("The background file for the main menu has imploded!");
        }
        setContentPane(new backImage(img));
    }

    private void initHeader() {
        header = new JLabel();
        header.setText("Last Dawn");
        Font headerFont = new Font("Parchment", Font.PLAIN, 200);
        header.setFont(headerFont);
        header.setForeground(Color.LIGHT_GRAY);
        header.setBounds(110, -120, 800, 500);

        Font subFont = new Font("Harrington", Font.PLAIN, 24);
        subText = new JLabel();
        subText.setText("The journey of a thousand miles begins with one step");
        subText.setFont(subFont);
        subText.setForeground(Color.LIGHT_GRAY);
        subText.setBounds(130, 0, 800, 500);

        add(header);
        add(subText);
    }

    private void initButtons() {
        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load Game");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");

        newGameButton.setBounds(247, 330, 300, 50);
        newGameButton.setForeground(Color.ORANGE);
        newGameButton.setFocusPainted(false);
        newGameAction();

        loadGameButton.setBounds(247, 385, 300, 50);
        loadGameButton.setForeground(Color.ORANGE);
        loadGameButton.setFocusPainted(false);
        loadGameAction();

        optionsButton.setBounds(247, 440, 300, 50);
        optionsButton.setForeground(Color.ORANGE);
        optionsButton.setFocusPainted(false);
        optionsAction();

        exitButton.setBounds(247, 495, 300, 50);
        exitButton.setForeground(Color.ORANGE);
        exitButton.setFocusPainted(false);
        exitGameAction();

        add(newGameButton);
        add(loadGameButton);
        add(optionsButton);
        add(exitButton);
    }

    private void newGameAction() {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
    }

    private void loadGameAction() {
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
    }

    private void optionsAction() {
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionsUI = new OptionsUI(MainMenuUI.this);
            }
        });
    }

    private void exitGameAction() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = CLOSE_MESSAGE;
                if (JOptionPane.showOptionDialog(rootPane, string, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null)
                        == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

}

class backImage extends JComponent {

    Image i;

//Creating Constructer
    public backImage(Image i) {
        this.i = i;

    }

//Overriding the paintComponent method
    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(i, 0, 0, null);  // Drawing image using drawImage method

    }
}
