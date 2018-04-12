/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.game.Game;
import view.utils.ImagePanel;
import view.OptionsUI;
import view.utils.ResourceUtil;

/**
 *
 * @author Toasty Studios
 */
public class MainMenuUI extends JPanel {

    private JLabel header;
    private JLabel subText;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton optionsButton;
    private JButton exitButton;
    private JDialog optionsUI;
    private Clip clip;
    private final JFrame main;
    private JPanel bg;
    private Game game;

    public MainMenuUI(JFrame main, Game game) {
        super();
        setLayout(null);
        this.main = main;
        this.game = game;
        initUI();
    }

    private void initUI() {
        initHeader();
        initButtons();
        setBackgroundImage();
        setVisible(true);
    }

    private void setBackgroundImage() {
        ImagePanel panel = new ImagePanel(new ImageIcon(getClass()
                                         .getResource("/bg/mainmenu.jpg"))
                                         .getImage());
        add(panel);
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
                GameUI gameUI = new GameUI(main, game);
            }
        });
    }

    private void loadGameAction() {
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                String string = ResourceUtil.CLOSE_MESSAGE;
                if (JOptionPane.showOptionDialog(MainMenuUI.this, string, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null)
                        == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

}

