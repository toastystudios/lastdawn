/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package toastystudios.lastdawn.domain.model.character;

import toastystudios.lastdawn.domain.model.enums.StatTypes;
import toastystudios.lastdawn.domain.model.item.Armour;
import toastystudios.lastdawn.domain.model.item.Item;
import toastystudios.lastdawn.domain.model.item.Weapon;
import toastystudios.lastdawn.domain.model.item.armour.Chest;
import toastystudios.lastdawn.domain.model.item.armour.Head;
import toastystudios.lastdawn.domain.model.item.armour.Jewelry;
import toastystudios.lastdawn.domain.model.item.armour.Legs;
import toastystudios.lastdawn.domain.model.repo.Inventory;

/**
 *
 * @author Toasty Studios
 */
public abstract class Character {

    protected final Inventory inventory;
    protected int level;
    protected Stats stats;
    private int currentHealth;
    private String name;

    /**
     * init player character
     *
     * @param startingLevel
     */
    public Character(String name, int startingLevel, int constitution, int strength, int intelligence, int dexterity) {
        this.inventory = new Inventory();
        this.level = startingLevel;
        this.stats = new Stats(constitution, strength, intelligence, dexterity);
        this.currentHealth = getMaxHealth();
    }

    //=======================================
    //------------ CLASS METHODS ------------
    //=======================================
    /**
     * Uses the character basic attack
     *
     * @return
     */
    public abstract int basicAttack();

    /**
     * Uses the character ability
     *
     * @return
     */
    public abstract int ability();

    /**
     * Uses the character ultimate
     *
     * @return
     */
    public abstract int ultimate();

    //=======================================
    //---------- CHAR. LVL METHODS ----------
    //=======================================
    /**
     * Returns the current level of a character
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    // =======================================
    // ---------- HEALTH MANAGEMENT ----------
    // =======================================
    /**
     * Calculates the character max health (Base Stats + Weapon and Armour)
     *
     * @return
     */
    public int getMaxHealth() {
        return (stats.constitution() + inventory().equipmentStats().get(StatTypes.CONSTITUTION.toString()));
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

    // =======================================
    // ---------- INVENTORY METHODS ----------
    // =======================================
    /**
     * Returns the player inventory
     *
     * @return
     */
    public Inventory inventory() {
        return inventory;
    }

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
        if (item instanceof Weapon) {
            Weapon currentEquipped = (Weapon) inventory.searchSlot(Weapon.class.getSimpleName());

            if (inventory.equipItem(item, level)) {

                int healthDiff;

                if (currentEquipped == null) {
                    healthDiff = ((Weapon) item).stats().constitution();
                } else {
                    healthDiff = ((Weapon) item).stats().constitution() - currentEquipped.stats().constitution();
                }

                if (healthDiff > 0) {
                    increaseHealth(healthDiff);
                } else {
                    decreaseHealth(healthDiff);
                    if (currentHealth == 0) {
                        currentHealth = 1;
                    }
                }

                return true;
            }
        } else if (item instanceof Armour) {
            Armour currentEquipped = null;

            if (item instanceof Head) {
                currentEquipped = (Armour) inventory.searchSlot(Head.class.getSimpleName());
            }
            if (item instanceof Chest) {
                currentEquipped = (Armour) inventory.searchSlot(Chest.class.getSimpleName());
            }
            if (item instanceof Legs) {
                currentEquipped = (Legs) inventory.searchSlot(Legs.class.getSimpleName());
            }
            if (item instanceof Jewelry) {
                currentEquipped = (Jewelry) inventory.searchSlot(Jewelry.class.getSimpleName());
            }

            if (inventory.equipItem(item, level)) {
                int healthDiff;

                if (currentEquipped == null) {
                    healthDiff = ((Armour) item).stats().constitution();
                } else {
                    healthDiff = ((Armour) item).stats().constitution() - currentEquipped.stats().constitution();
                }

                if (healthDiff > 0) {
                    increaseHealth(healthDiff);
                } else {
                    decreaseHealth(healthDiff);
                    if (currentHealth == 0) {
                        currentHealth = 1;
                    }
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Unequips an item and puts it in the inventory
     *
     */
    public boolean unequipItem(String slot) {

        Item item = inventory.searchSlot(slot);

        if (item instanceof Weapon) {

            if (inventory.unequipItem(slot)) {

                int healthDiff = ((Weapon) item).stats().constitution();

                decreaseHealth(healthDiff);

                if (currentHealth <= 0) {
                    currentHealth = 1;
                }

                return true;

            }
        } else if (item instanceof Armour) {

            if (inventory.unequipItem(slot)) {
                int healthDiff = ((Armour) item).stats().constitution();

                decreaseHealth(healthDiff);

                if (currentHealth <= 0) {
                    currentHealth = 1;
                }

                return true;
            }
        }
        return false;
    }

    // =======================================
    // ------------ STATS METHODS ------------
    // =======================================
    /**
     * Returns the character total dexterity
     *
     * @return
     */
    public int dexterity() {
        return this.stats.dexterity() + inventory.equipmentStats().get(StatTypes.DEXTERITY.toString());
    }

    /**
     * Returns the character defense
     *
     * @return
     */
    public int defense() {
        return inventory.getTotalDefense();
    }

}
