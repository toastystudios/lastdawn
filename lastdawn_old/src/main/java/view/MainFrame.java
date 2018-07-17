/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import view.utils.ResourceUtil;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.game.Game;

/**
 *
 * @author ruial
 */
public class MainFrame extends JFrame {
    
    private Game game;


    public MainFrame(Game game) {
        this.game = game;
        initUI();
        setVisible(true);
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
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(rootPane,
                        ResourceUtil.CLOSE_MESSAGE, "Warning",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }); 
        
//    add(new MainMenuUI(MainFrame.this, game));
    add(new GameUI(MainFrame.this, game));
    }


}
