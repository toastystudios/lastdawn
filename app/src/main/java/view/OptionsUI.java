/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import view.options.VolumeControl;

/**
 *
 * @author Toasty Studios
 */
public class OptionsUI extends JDialog {
    
    private JLabel volume;
    private JLabel autosave;
    private JRadioButton volumeOnRB;
    private JRadioButton volumeOffRB;
    private JRadioButton autosaveOnRB;
    private JRadioButton autosaveOffRB;
    private JButton OK;

    public OptionsUI(JFrame main) {
        super(main);
        initUI();
    }

    private void initUI() {
        setTitle("Last Dawn - Options");
        setSize(400, 250);
        setLocationRelativeTo(getParent());
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setBackgroundImage();
        initButtons();
        setVisible(true);
    }
    
    private void setBackgroundImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(OptionsUI.class.getResourceAsStream("/bg/options.jpg"));
        } catch (IOException ex) {
            System.out.println("The background file for the main menu has imploded!");
        }
        setContentPane(new backImage(img));
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
        volume.setBounds(50,50,125,20);
        
        autosave = new JLabel();
        autosave.setText("Auto Save");
        autosave.setFont(font);
        autosave.setForeground(Color.WHITE);
        autosave.setBounds(50,100,125,20);
        
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
                VolumeControl.manageSound(true);
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
                VolumeControl.manageSound(false);
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
