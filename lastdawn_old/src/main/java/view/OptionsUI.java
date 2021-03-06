/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import view.utils.ImagePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import view.options.VolumeControl;

/**
 *
 * @author Toasty Studios
 */
public class OptionsUI extends JDialog {

    private VolumeControl volumeControl = new VolumeControl();
    private JLabel volume;
    private JLabel autosave;
    private JRadioButton volumeOnRB;
    private JRadioButton volumeOffRB;
    private JRadioButton autosaveOnRB;
    private JRadioButton autosaveOffRB;
    private JButton OK;
    private JPanel panel;

    public OptionsUI(JPanel main) {
        super();
        this.panel = main;
        initUI();
    }

    private void initUI() {
        setLayout(null);
        setTitle("Last Dawn - Options");
        setSize(400, 250);
        setLocationRelativeTo(getParent());
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        initButtons();
        setBackgroundImage();
        setVisible(true);
    }

    private void setBackgroundImage() {
        ImagePanel panel = new ImagePanel(new ImageIcon(getClass()
                                         .getResource("/bg/options.jpg"))
                                         .getImage());
        add(panel);
    }

    private void initButtons() {
        OK = new JButton("Confirm");
        OK.setBounds(140, 150, 125, 30);
        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(OK);

        initRadioButtons();
    }

    private void initRadioButtons() {
        Font font = new Font("Harrington", Font.PLAIN, 18);
        volume = new JLabel();
        volume.setText("Volume");
        volume.setFont(font);
        volume.setForeground(Color.WHITE);
        volume.setBounds(50, 50, 125, 20);

        autosave = new JLabel();
        autosave.setText("Auto Save");
        autosave.setFont(font);
        autosave.setForeground(Color.WHITE);
        autosave.setBounds(50, 100, 125, 20);

        add(volume);
        add(autosave);

        volumeOptions();
        autoSaveOptions();
    }

    private void volumeOptions() {
        volumeOnRB = new JRadioButton("On");
        volumeOnRB.setForeground(Color.WHITE);
        volumeOnRB.setBounds(175, 50, 50, 20);
        volumeOnRB.setOpaque(false);
        volumeOnRB.setFocusPainted(false);
        volumeOnRB.setSelected(true);
        volumeOnRB.setActionCommand("On");
        volumeOnRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volumeControl.manageSound(false);
            }
        });

        volumeOffRB = new JRadioButton("Off");
        volumeOffRB.setBounds(300, 50, 50, 20);
        volumeOffRB.setForeground(Color.WHITE);
        volumeOffRB.setOpaque(false);
        volumeOffRB.setFocusPainted(false);
        volumeOffRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volumeControl.manageSound(true);
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(volumeOnRB);
        group.add(volumeOffRB);
        add(volumeOnRB);
        add(volumeOffRB);

    }

    private void autoSaveOptions() {
        autosaveOnRB = new JRadioButton("On");
        autosaveOnRB.setForeground(Color.WHITE);
        autosaveOnRB.setBounds(175, 100, 50, 20);
        autosaveOnRB.setOpaque(false);
        autosaveOnRB.setFocusPainted(false);
        autosaveOnRB.setSelected(true);
        autosaveOnRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        autosaveOffRB = new JRadioButton("Off");
        autosaveOffRB.setBounds(300, 100, 50, 20);
        autosaveOffRB.setForeground(Color.WHITE);
        autosaveOffRB.setOpaque(false);
        autosaveOffRB.setFocusPainted(false);
        autosaveOffRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(autosaveOnRB);
        group.add(autosaveOffRB);
        add(autosaveOnRB);
        add(autosaveOffRB);

    }
}

class optionsBackground extends JPanel {

    BufferedImage bg = null;

    @Override
    public void paintComponent(Graphics g) {

        try {
            bg = ImageIO.read(MainMenuUI.class.getResourceAsStream("/bg/options.jpg"));
        } catch (IOException ex) {
            System.out.println("Options UI bg imploded on itself..");
        }

        g.drawImage(bg, 0, 0, this);
    }

}
