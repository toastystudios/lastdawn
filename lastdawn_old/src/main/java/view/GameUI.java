/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import controller.InventoryController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import model.game.Game;
import model.item.Item;
import view.utils.Console;
import view.utils.ImagePanel;

/**
 *
 * @author ruial
 */
public class GameUI extends JPanel {

    private InventoryController inventoryController;

    private JScrollPane consoleScroll;
    private JTextPane consoleOutput;
    private JList<Item> inventory;
    private JTextPane playerHealth;
    private JTextPane playerResources;
    private JLabel currentLocation;
    private JLabel constitutionValue;
    private JLabel strengthValue;
    private JLabel dexterityValue;
    private JLabel intelligenceValue;
    private DefaultListModel<String> inventoryList;
    private JList<Item> equipped;
    private DefaultListModel<String> equippedList;
    private Game game;

    public GameUI(JFrame main, Game game) {
        super();
        setLayout(null);
        inventoryController = new InventoryController(game.player());
        initUI();
    }

    private void initUI() {
        initPlayerInfo();

        //Seperator between player info and console
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBounds(0, 110, 800, 5);
        separator.setBackground(Color.white.darker());
        separator.setForeground(Color.black);
        add(separator);

        initConsole();
        initInventory();
        initEquippedGear();
        initDynamicPanel();
    }
    
    private void initEquippedGear() {
        equippedList = new DefaultListModel<>();
        equipped = new JList(equippedList);
        equipped.setBackground(Color.DARK_GRAY.brighter());
        equipped.setForeground(Color.white);
        equipped.setPreferredSize(new Dimension(200, 600));
        equipped.setBorder(BorderFactory.createLineBorder(Color.black.brighter()));
        equipped.setBounds(610, 115, 180, 115);       
        
        refreshEquippedList();
        
        add(equipped);
        
    }

    private void initInventory() {
        inventoryList = new DefaultListModel<>();
        inventory = new JList(inventoryList);
        inventory.setBackground(Color.DARK_GRAY.brighter());
        inventory.setForeground(Color.white);
        inventory.setPreferredSize(new Dimension(200, 600));
        inventory.setBorder(BorderFactory.createLineBorder(Color.black.brighter()));
        inventory.setBounds(610, 235, 180, 365);

        refreshInventoryList();

        add(inventory);
    }

    private void initPlayerInfo() {
        //Draw the player avatar
        ImagePanel avatar = new ImagePanel(new ImageIcon(getClass()
                .getResource("/ingame/avatar.png"))
                .getImage());

        avatar.setBounds(5, 5, 100, 100);

        playerHealth = new JTextPane();
        playerHealth.setText("       Health");
        playerHealth.setEditable(false);
        playerHealth.setBorder(BorderFactory.createLineBorder(Color.yellow));
        playerHealth.setBounds(110, 30, 100, 25);

        playerResources = new JTextPane();
        playerResources.setText("     Resources");
        playerResources.setEditable(false);
        playerResources.setBorder(BorderFactory.createLineBorder(Color.yellow));
        playerResources.setBounds(110, 60, 100, 25);

        //Title of the stats
        JLabel stats = new JLabel("Stats");
        stats.setBounds(325, 5, 50, 10);
        stats.setForeground(Color.WHITE);

        JLabel strength = new JLabel("Strength:");
        strength.setBounds(265, 30, 75, 15);
        strength.setForeground(Color.WHITE);

        strengthValue = new JLabel();
        strengthValue.setBounds(320, 30, 75, 15);
        strengthValue.setForeground(Color.WHITE);

        JLabel constitution = new JLabel("Constitution:");
        constitution.setBounds(245, 60, 75, 15);
        constitution.setForeground(Color.WHITE);

        constitutionValue = new JLabel();
        constitutionValue.setBounds(320, 60, 75, 15);
        constitutionValue.setForeground(Color.WHITE);

        JLabel dexterity = new JLabel("Dexterity:");
        dexterity.setBounds(340, 60, 75, 15);
        dexterity.setForeground(Color.WHITE);

        dexterityValue = new JLabel();
        dexterityValue.setBounds(410, 60, 75, 15);
        dexterityValue.setForeground(Color.WHITE);

        JLabel intelligence = new JLabel("Intelligence:");
        intelligence.setForeground(Color.WHITE);
        intelligence.setBounds(340, 30, 75, 15);

        intelligenceValue = new JLabel();
        intelligenceValue.setBounds(410, 30, 75, 15);
        intelligenceValue.setForeground(Color.WHITE);

        this.strengthValue.setText("12");
        this.dexterityValue.setText("12");
        this.intelligenceValue.setText("12");
        this.constitutionValue.setText("12");

        JLabel playerMap = new JLabel("Current Location");
        playerMap.setForeground(Color.WHITE);
        playerMap.setBounds(490, 5, 100, 15);

        currentLocation = new JLabel();
        currentLocation.setText("Starting Area");
        currentLocation.setForeground(Color.white);
        currentLocation.setBounds(500, 30, 100, 15);

        JButton playerOptions = new JButton("Options");
        playerOptions.setOpaque(false);
        playerOptions.setBounds(650, 30, 100, 25);        
        playerOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OptionsUI(GameUI.this);
            }
        });

        //Player area -> Avatar, health and resources
        add(avatar);
        add(playerHealth);
        add(playerResources);

        //Player Stats
        add(stats);
        add(constitution);
        add(constitutionValue);
        add(dexterity);
        add(dexterityValue);
        add(strength);
        add(strengthValue);
        add(intelligence);
        add(intelligenceValue);

        //Player Map current location
        add(playerMap);
        add(currentLocation);

        //Options
        add(playerOptions);

    }

    private void initDynamicPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.red);
        panel.setPreferredSize(new Dimension(200, 600));
        panel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        panel.setBounds(5, 115, 600, 330);
        add(panel);
    }

    private void initConsole() {
        //Create the console output area
        consoleOutput = new JTextPane();
        consoleOutput.setPreferredSize(new Dimension(600, 300));
        consoleOutput.setBackground(Color.DARK_GRAY.brighter());
        consoleOutput.setBorder(BorderFactory.createLineBorder(Color.black.brighter()));
        consoleOutput.setBounds(5, 450, 600, 150);

        consoleScroll = new JScrollPane(consoleOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        consoleScroll.setBounds(5, 450, 600, 150);

        //Redirect the console output to the GUI
        Console console = new Console(consoleOutput);
        console.redirectOut(Color.WHITE, System.out);
        console.redirectErr(Color.red, System.err);
        console.setMessageLines(100);

        add(consoleScroll);
    }

    public void updateCurrentLocation(String location) {
        this.currentLocation.setText(location);
    }

    public void refreshInventoryList() {
        for (int i = 0; i < 20; i++) {
            inventoryList.addElement("I am an Inventory Item!");
        }
    }
    public void refreshEquippedList() {
        for (int i = 0; i < 6; i++) {
            equippedList.addElement("I am an equipment Item!");
        }
    }

}
