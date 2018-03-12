/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.npc;

import model.character.Stats;
import model.enums.StatTypes;
import model.item.Item;
import model.repo.Inventory;

/**
 *
 * @author Toasty Studios
 */
public abstract class NPC implements model.character.Character {

    private final String name;
    private final String description;
    private Stats stats;
    private Inventory inventory;
    private int currentHealth;

    /**
     * init
     *
     * @param name
     * @param description
     * @param constitution
     * @param strength
     * @param intelligence
     * @param dexterity
     */
    public NPC(String name, String description, int constitution, int strength, int intelligence, int dexterity) {
        this.name = name;
        this.description = description;
        initStats(constitution, strength, intelligence, dexterity);
        this.inventory = new Inventory();
    }

    /**
     * Init the NPC stats
     *
     * @param constitution
     * @param strength
     * @param intelligence
     * @param dexterity
     */
    public void initStats(int constitution, int strength, int intelligence, int dexterity) {
        this.stats = new Stats(constitution, strength, intelligence, dexterity);
    }

    @Override
    /**
     * Returns the character max health (Base stats + Armour and Weapon)
     */
    public int getMaxHealth() {
        return stats.constitution() + inventory.equipmentStats().get(StatTypes.CONSTITUTION.toString());
    }

    @Override
    /**
     * Returns the current character health
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    /**
     * Adds an item to the NPC inventory
     * @param item
     * @return 
     */
    @Override
    public boolean addItem(Item item) {
        return inventory.addItem(item);
    }

    /**
     * Removes an item from the NPC inventory
     * @param item
     * @return 
     */
    @Override
    public boolean removeItem(Item item) {
        return inventory.removeItem(item);
    }

    /**
     * Searches for an item in the npc inventory
     * @param name
     * @return 
     */
    @Override
    public Item searchItem(String name) {
        return inventory.searchItem(name);
    }

    /**
     * Equips the NPC with an item
     * @param item
     * @param npcLevel
     * @return 
     */
    @Override
    public boolean equipItem(Item item, int npcLevel) {
        return inventory.equipItem(item, npcLevel);
    }

    /**
     * Unequips an item from the said equipment slot
     * @param slot
     * @return 
     */
    @Override
    public boolean unequipItem(String slot) {
        return inventory.unequipItem(slot);
    }

}
