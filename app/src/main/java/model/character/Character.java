/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.character;

import model.character.classes.Classless;
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
public abstract class Character {

    private final Inventory inventory;
    private int level;
    private int currentHealth;
    private model.character.Class characterClass;

    /**
     * init player character
     *
     * @param startingLevel
     * @param className
     */
    public Character(int startingLevel, String className) {
        this.inventory = new Inventory();
        this.level = startingLevel;
        initClass(className);
        this.currentHealth = getMaxHealth();
    }

    /**
     * init NPC
     *
     * @param startingLevel
     * @param constitution
     * @param strength
     * @param intelligence
     * @param dexterity
     */
    public Character(int startingLevel, int constitution, int strength, int intelligence, int dexterity) {
        this.inventory = new Inventory();
        this.level = startingLevel;
        initClassless(constitution, strength, intelligence, dexterity);
        this.currentHealth = getMaxHealth();
    }

    /**
     * Initializes a classless, programmer-defined class for NPCS
     *
     * @param constitution
     * @param strength
     * @param intelligence
     * @param dexterity
     */
    private void initClassless(int constitution, int strength, int intelligence, int dexterity) {
        this.characterClass = new Classless(constitution, strength, intelligence, dexterity);
    }

    /**
     * Initializes the character class
     *
     * @param playerClass
     */
    private void initClass(String className) {
        if (className.equals(Knight.class.getSimpleName())) {
            this.characterClass = new Knight();
        }

        if (className.equals(Paladin.class.getSimpleName())) {
            this.characterClass = new Paladin();
        }

        if (className.equals(Ranger.class.getSimpleName())) {
            this.characterClass = new Ranger();
        }

        if (className.equals(Scientist.class.getSimpleName())) {
            this.characterClass = new Scientist();
        }
    }

    /**
     * Calculates the character max health (Base Stats + Weapon and Armour)
     *
     * @return
     */
    public int getMaxHealth() {
        return (characterClass.baseStats().constitution() + inventory().equipmentStats().get(StatTypes.CONSTITUTION.toString()));
    }

    /**
     * Returns the current health of the character
     *
     * @return
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    /**
     * Decreases the character health points
     *
     * @return
     */
    public int decreaseHealth(int amount) {
        this.currentHealth -= amount;

        if (currentHealth < 0) {
            this.currentHealth = 0;
        }

        return currentHealth;
    }

    /**
     * Increases the character health points
     *
     * @return
     */
    public int increaseHealth(int amount) {
        this.currentHealth += amount;

        if (currentHealth > getMaxHealth()) {
            this.currentHealth = getMaxHealth();
        }

        return currentHealth;
    }

    /**
     * Returns the player inventory
     *
     * @return
     */
    public Inventory inventory() {
        return inventory;
    }

    ;

    /**
     * Adds an item to the inventory
     *
     * @return
     */
    public boolean addItem(Item item) {
        return inventory.addItem(item);
    }

    /**
     * Removes an item from the inventory
     *
     * @return
     */
    public boolean removeItem(Item item) {
        return inventory.removeItem(item);
    }

    /**
     * Searches for an item in the inventory
     *
     * @return
     */
    public Item searchItem(String name) {
        return inventory.searchItem(name);
    }

    /**
     * Equips an item in the equipped slots
     *
     */
    public boolean equipItem(Item item) {
        return inventory.equipItem(item, this.level);
    }

    /**
     * Unequips an item and puts it in the inventory
     *
     */
    public boolean unequipItem(String slot) {
        return inventory.unequipItem(slot);
    }

}
