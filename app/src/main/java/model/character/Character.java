/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.character;

import model.item.Item;

/**
 *
 * @author Toasty Studios
 */
public interface Character {

    /**
     * Calculates the character max health (Base Stats + Weapon and Armour)
     *
     * @return
     */
    public int getMaxHealth();

    /**
     * Returns the current health of the character
     *
     * @return
     */
    public int getCurrentHealth();

    /**
     * Adds an item to the inventory
     *
     * @return
     */
    public boolean addItem(Item item);

    /**
     * Removes an item from the inventory
     *
     * @return
     */
    public boolean removeItem(Item item);

    /**
     * Searches for an item in the inventory
     *
     * @return
     */
    public Item searchItem(String name);

    /**
     * Equips an item in the equipped slots
     *
     */
    public boolean equipItem(Item item, int level);

    /**
     * Unequips an item and puts it in the inventory
     *
     */
    public boolean unequipItem(String slot);

}
