/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
package toastystudios.lastdawn.domain.model.item;

import toastystudios.lastdawn.domain.model.character.Stats;

/**
 *
 * @author Toasty Studios
 */
public class Weapon extends Item {

    private Stats stats;
    private int damage;

    /**
     * init
     * @param name
     * @param description
     * @param value
     * @param rarity
     * @param unique
     * @param level
     * @param damage 
     */
    public Weapon(String name, String description, int value, String rarity, boolean unique, int level, int damage, int constitution, int strength, int intelligence, int dexterity) {
        super(name, description, value, rarity, unique, level);
        this.damage = damage;
        this.stats = new Stats(constitution, strength, intelligence, dexterity);
    }
    
    /**
     * Returns the stat bonus of the weapon
     * @return 
     */
    public Stats stats() {
        return this.stats;
    }

    /**
     * Returns the weapon damage
     * @return 
     */
    public int damage() {
        return damage;
    }
    
}
