/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.item.armour;

import model.item.Armour;

/**
 *
 * @author Toasty Studios
 */
public class Jewelry extends Armour {

    public Jewelry(String name, String description, int value, String rarity, boolean unique, int level, int defense, int constitution, int strength, int intelligence, int dexterity) {
        super(name, description, value, rarity, unique, level, defense, constitution, intelligence, strength, dexterity);
    }
}
