/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package model.item;

import model.character.Stats;

/**
 *
 * @author Toasty Studios
 */
public abstract class Armour extends Item {

    private Stats stats;
    private int defense;

    /**
     * init
     * @param name
     * @param description
     * @param value
     * @param rarity
     * @param unique
     * @param level 
     */
    public Armour(String name, String description, int value, String rarity, boolean unique, int level, int defense) {
        super(name, description, value, rarity, unique, level);
        this.defense = defense;
    }
    
    /**
     * Returns the armour stat bonus
     * @return 
     */
    public Stats stats() {
        return this.stats;
    }

}
