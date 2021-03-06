/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.npc;

import model.map.Local;

/**
 *
 * @author Toasty Studios
 */
public class ShopNPC extends NPC implements QuestNPC {

    public ShopNPC(String name, String description, int constitution, int strength, int intelligence, int dexterity, int level) {
        super(name, description, constitution, strength, intelligence, dexterity, level);
    }

}
