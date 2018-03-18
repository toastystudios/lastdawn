/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.repo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.enums.StatTypes;
import model.item.Armour;
import model.item.Item;
import model.item.Weapon;
import model.item.armour.Chest;
import model.item.armour.Head;
import model.item.armour.Jewelry;
import model.item.armour.Legs;

/**
 *
 * @author Toasty Studios
 */
public class Inventory {

    private HashMap<String, Item> items;
    private HashMap<String, Item> equipped;
    private static final int MAX_SLOTS = 20;

    /**
     * init
     */
    public Inventory() {
        this.items = new HashMap();
        this.equipped = new HashMap();
    }

    /**
     * Calculates the number of items the character has
     *
     * @return
     */
    public int filledSlots() {
        int sum = 0;
        for (Iterator<Item> it = items.values().iterator(); it.hasNext(); it.next()) {
            sum++;
        }
        return sum;
    }

    /**
     * Adds an item to the inventory
     *
     * @param item
     * @return
     */
    public boolean addItem(Item item) {
        String itemName = item.name();
        int i = 1;

        if (filledSlots() >= MAX_SLOTS) {
            return false;
        }

        if (items.containsValue(item) && item.isUnique()) {
            return false;
        }

        while (items.containsKey(itemName)) {
            itemName = item.name() + "-" + i;
            i++;
        }

        items.put(itemName, item);

        return items.containsKey(itemName);
    }

    /**
     * Removes an item from the inventory
     *
     * @param item
     * @return
     */
    public boolean removeItem(Item item) {
        return items.remove(item.name(), item);
    }

    /**
     * Given a name, returns the item if exists
     *
     * @param name
     * @return
     */
    public Item searchItem(String name) {
        return items.get(name);
    }

    /**
     * Equips a head in the player head slot
     *
     * if the player already has an i tem equipped, switches the equipped with
     * the one with the inventory if the player has not an item equipped, simply
     * equips it and removes it from the inventory
     *
     * @param item
     * @param level
     * @return
     */
    public boolean equipItem(Item item, int level) {
        if (level >= item.level()) {

            if (item instanceof Armour) {
                return equipArmour((Armour) item);
            }

            if (item instanceof Weapon) {
                return equipItem(Weapon.class.getSimpleName(), (Weapon) item);
            }

            return false;

        } else {
            return false;
        }
    }

    /**
     * Verifies if the item is a piece of armour and tries to equip it, if not,
     * returns false
     *
     * @param armour
     * @return
     */
    public boolean equipArmour(Armour armour) {
        if (armour instanceof Head) {
            return equipItem(Head.class.getSimpleName(), armour);
        } else if (armour instanceof Chest) {
            return equipItem(Chest.class.getSimpleName(), armour);
        } else if (armour instanceof Legs) {
            return equipItem(Legs.class.getSimpleName(), armour);
        } else if (armour instanceof Jewelry) {
            return equipItem(Jewelry.class.getSimpleName(), armour);
        } else {
            return false;
        }

    }

    /**
     * Equips an item in the player equipped slot.
     *
     * If the player already has an equipped item, puts the item in the
     * inventory and equips the new and removes the new one from the inventory.
     * If the player does not have an equipped item, simply equips the new one
     * and removes the new from the inventory.
     *
     * @param slot
     * @param item
     * @return
     */
    private boolean equipItem(String slot, Item item) {
        Item current = equipped.get(slot);

        if (current != null) {          
            items.put(current.name(), current);
        }

        items.remove(item.name());
        equipped.put(slot, item);
        return equipped.containsKey(slot);
    }

    /**
     * Unequips an item from a slot and puts it in the inventory. If the
     * inventory does not have enough space, returns false
     *
     * @param slot
     * @return
     */
    public boolean unequipItem(String slot) {
        if (filledSlots() >= MAX_SLOTS) {
            return false;
        } else {
            Item item = equipped.get(slot);
            
            if (item == null)
                return false;
            
            equipped.remove(slot);
            items.put(item.name(), item);
            
            return items.containsKey(item.name());
        }
    }

    /**
     * Creates a hashmap with all the stat bonus of all theh equipment of the
     * player Key = stat name Value = Bonus value
     *
     * @return
     */
    public Map<String, Integer> equipmentStats() {
        Map<String, Integer> equipmentStats = new HashMap<>();

        int constitution = 0;
        int strength = 0;
        int intelligence = 0;
        int dexterity = 0;

        for (Item item : equipped.values()) {

            if (item instanceof Armour && item != null) {

                constitution += ((Armour) item).stats().constitution();
                strength += ((Armour) item).stats().strength();
                intelligence += ((Armour) item).stats().intelligence();
                dexterity += ((Armour) item).stats().dexterity();

            } else if (item instanceof Weapon && item != null) {

                constitution += ((Weapon) item).stats().constitution();
                strength += ((Weapon) item).stats().strength();
                intelligence += ((Weapon) item).stats().intelligence();
                dexterity += ((Weapon) item).stats().dexterity();

            }
        }

        equipmentStats.put(StatTypes.STRENGTH.toString(), strength);
        equipmentStats.put(StatTypes.CONSTITUTION.toString(), constitution);
        equipmentStats.put(StatTypes.INTELLIGENCE.toString(), intelligence);
        equipmentStats.put(StatTypes.DEXTERITY.toString(), dexterity);

        return equipmentStats;
    }
    
    /**
     * Returns the current equipped weapon damage
     * @return 
     */
    public int weaponDamage() {
        Weapon weapon = (Weapon) equipped.get(Weapon.class.getSimpleName());
        return weapon.damage();
    }

}
