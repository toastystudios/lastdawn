/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.util.logging.Logger;
import model.item.Misc;
import model.item.Weapon;
import model.npc.GeneralNPC;

/**
 *
 * @author Toasty Studios
 */
public class Run {

    private static Logger logger = Logger.getLogger("InfoLogging");

    public static void main(String[] args) {
        logger.info("Logging an INFO-level message");

        model.character.Character character = new GeneralNPC("Joseph Manners", "The usual Manners, pacient and wise, he runs the tests.", 10, 10, 10, 10, 10);
        model.item.Item item = new Misc("Sword of only one pointy edge", "It's your usual sword..you stab them with the pointy end.", 3, "Rare", false, 10);
        System.out.println(character.addItem(item));

    }

}
