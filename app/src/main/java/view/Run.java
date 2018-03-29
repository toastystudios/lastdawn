/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.util.logging.Logger;
import model.game.BattleHandler;
import model.item.Weapon;
import model.item.armour.Chest;
import model.item.armour.Head;
import model.item.armour.Jewelry;
import model.item.armour.Legs;
import model.npc.NPC;
import model.player.Knight;
import model.player.PlayerChar;

/**
 *
 * @author Toasty Studios
 */
public class Run {

    private static Logger logger = Logger.getLogger("InfoLogging");

    public static void main(String[] args) throws InterruptedException {
        logger.info("Logging an INFO-level message");

        Knight player = new Knight("Rafastang");
        Head head = new Head("Capacete", "asdf", 10, "Rare", false, 1, 1, 1, 1, 1, 1);
        Chest chest = new Chest("Chest", "asdf", 10, "Rare", false, 1, 1, 1, 1, 1, 1);
        Legs legs = new Legs("Legs", "asdf", 10, "Rare", false, 1, 1, 1, 1, 1, 1);
        Jewelry jewelry = new Jewelry("Amulet", "asdf", 10, "Rare", false, 1, 1, 1, 1, 1, 1);
        Weapon weapon = new Weapon("Yes", "no", 10, "Rare", false, 1, 5, 1, 1, 1, 1);
        player.equipItem(head);
        player.equipItem(chest);
        player.equipItem(legs);
        player.equipItem(jewelry);
        player.equipItem(weapon);
        System.out.println("");
        NPC npc = new NPC("MonstroFdd", "asdf", 25, 10, 1, 1, 1);
        npc.equipItem(head);
        npc.equipItem(chest);
        npc.equipItem(legs);
        npc.equipItem(jewelry);
        npc.equipItem(weapon);
        npc.unequipItem("Head");
        BattleHandler battle = new BattleHandler();
        if (battle.battlePVE(player, npc) instanceof PlayerChar) {
            System.out.println("The player has won!");
            int level = player.getLevel();
            System.out.println("You have gained: " + player.increaseXP(npc) + " experience!\n");
            if (level > player.getLevel()) {
                System.out.println("You have leveled up!");
            }
        } else {
            System.out.println("The npc has won!");
        }

    }

}
