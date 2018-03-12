/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package model.player;

import model.enums.StatTypes;
import model.item.Item;
import model.player.classes.Knight;
import model.player.classes.Paladin;
import model.player.classes.Ranger;
import model.player.classes.Scientist;
import model.repo.Inventory;

/**
 *
 * @author Toasty Studios
 */
public class PlayerChar implements model.character.Character {

    private Inventory inventory;
    private Class playerClass;
    private int currentHealth;
    
    public PlayerChar(String playerClass) {
        this.inventory = new Inventory();
        initClass(playerClass);
    }
    

    /**
     * Initializes the player class
     * @param playerClass 
     */
    private void initClass(String playerClass) {
        if (playerClass.equals(Knight.class.getSimpleName()))
            this.playerClass = new Knight();
            
        if (playerClass.equals(Paladin.class.getSimpleName()))
            this.playerClass = new Paladin();
            
        if (playerClass.equals(Ranger.class.getSimpleName()))
            this.playerClass = new Ranger();
            
        if (playerClass.equals(Scientist.class.getSimpleName()))
            this.playerClass = new Scientist();
    }
    
    /**
     * Calculates the player max health (Base stats + bonus stats from equipment)
     * @return 
     */
    @Override
    public int getMaxHealth() {
        return (playerClass.baseStats().constitution() + inventory.equipmentStats().get(StatTypes.CONSTITUTION.toString()));
    }

    /**
     * Returns the player current health
     * @return 
     */
    @Override
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    /**
     * Adds an item to the player inventory
     * @param item
     * @return 
     */
    @Override
    public boolean addItem(Item item) {
       return inventory.addItem(item);
    }

    /**
     * Removes an item from the player inventory
     * @param item
     * @return 
     */
    @Override
    public boolean removeItem(Item item) {
       return inventory.removeItem(item);
    }

    /**
     * Searches an item in the player inventory given an item name
     * @param name
     * @return 
     */
    @Override
    public Item searchItem(String name) {
        return inventory.searchItem(name);
    }

    /**
     * Equips an item in the equipment slot
     * @param item
     * @param playerLevel
     * @return 
     */
    @Override
    public boolean equipItem(Item item, int playerLevel) {
       return inventory.equipItem(item, playerLevel);
    }

    /**
     * Unequips an item from the equipment slot
     * @param slot
     * @return 
     */
    @Override
    public boolean unequipItem(String slot) {
        return inventory.unequipItem(slot);
    }


}
