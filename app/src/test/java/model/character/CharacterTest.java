/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.character;

import model.item.Item;
import model.item.Misc;
import model.item.Weapon;
import model.item.armour.Head;
import model.npc.GeneralNPC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruial
 */
public class CharacterTest {

    private Character character;
    private Item item;

    public CharacterTest() {
    }

    @Before
    public void setUp() {
        this.character = new GeneralNPC("Joseph Manners", "The usual Manners, pacient and wise, he runs the tests.", 10, 10, 10, 10, 10);
        this.item = new Weapon("Sword of only one pointy edge", "It's your usual sword..you stab them with the pointy end.", 5000, "Rare", true, 10, 10, 1, 1, 1, 1);
    }

    /**
     * Test of getMaxHealth method, of class Character.
     */
    @Test
    public void testGetMaxHealth() {
        int expResult = 10;
        int result = this.character.getMaxHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentHealth method, of class Character.
     */
    @Test
    public void testGetCurrentHealth() {
        int expResult = 10;
        int result = this.character.getCurrentHealth();
        assertEquals(expResult, result);

        expResult = 5;
        this.character.decreaseHealth(5);
        result = character.getCurrentHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of addItem method, of class Character.
     */
    @Test
    public void testAddItem() {
        boolean expResult = true;
        boolean result = this.character.addItem(item);
        assertEquals("Should be able to add the item", expResult, result);

        expResult = false;
        result = this.character.addItem(item);
        assertEquals("Should not be able to add the item, as the item is unique", expResult, result);

        for (int i = 0; i < 20; i++) {
            this.character.addItem(new Misc("TestItem", "TestDescription", 10, "Epic", false, 10));
        }

        for (int i = 0; i < 19; i++) {
            if (i == 0) {
                assertEquals("Names should be generated accordingly", "TestItem", this.character.searchItem("TestItem").name());
            } else {
                assertEquals("Names should be generated accordingly", "TestItem", this.character.searchItem("TestItem-" + i).name());
            }
        }

        expResult = false;
        result = this.character.addItem(new Misc("TestItem", "TestDescription", 10, "Epic", false, 10));
        assertEquals("Should not be able to add the item, as the inventory is full", expResult, result);
    }

    /**
     * Test of removeItem method, of class Character.
     */
    @Test
    public void testRemoveItem() {
        boolean expResult = true;
        Item item = new Misc("TestItem", "TestDesc", 10, "Rare", false, 10);
        this.character.addItem(item);
        boolean result = this.character.removeItem(item);
        assertEquals("Should be able to remove the item, because item exists", expResult, result);
        expResult = false;
        result = this.character.removeItem(item);
        assertEquals("Should not be able to remove item if item does not exist", expResult, result);
    }

    /**
     * Test of searchItem method, of class Character.
     */
    @Test
    public void testSearchItem() {
        Item item = new Misc("TestItem", "TestDesc", 10, "Rare", false, 10);
        this.character.addItem(item);

        Item expResult = item;
        Item result = character.searchItem(item.name());
        assertEquals("Should be able to return the item, item exists", expResult, result);

        expResult = null;
        result = character.searchItem("Wolololo, you're now a priest");
        assertEquals("Should not be able to return an item if item does not exist", expResult, result);
    }

    /**
     * Test of equipItem method, of class Character.
     */
    @Test
    public void testEquipItem() {
        boolean expResult = true;
        boolean result = this.character.equipItem(item);
        assertEquals("Should be able to equip a sword below or equal char level", expResult, result);

        expResult = false;
        result = this.character.equipItem(new Head("Head made of heads!", "Brutal..!!! RAAAAAAAGRG", 10, "Rare", true, 21, 10, 1, 1, 1, 1));
        assertEquals("Should not be able to equip as head is > than char level", expResult, result);

    }

    /**
     * Test of unequipItem method, of class Character.
     */
    @Test
    public void testUnequipItem() {
        this.character.equipItem(item);
        boolean expResult = true;
        boolean result = this.character.unequipItem("Weapon");
        assertEquals("Should be able to unequip because item is equipped.", expResult, result);
        
        expResult = false;
        result = this.character.unequipItem("Weapon");
        assertEquals("Should not be able to as there is no weapon to unequip", expResult, result);
        
        }

    /**
     * Test of decreaseHealth()
     */
    @Test
    public void testDecreaseHealth() {
        int result = character.decreaseHealth(5);
        int expResult = character.getMaxHealth()-5; 
        assertEquals("Should be 5 if HP max is 10", expResult, result);
        
        result = character.decreaseHealth(character.getMaxHealth());
        expResult = 0;
        assertEquals("Should be 0 if HP goes below 0",expResult, result);
    }
    

    /**
     * Test of increaseHealth()
     */
    @Test
    public void testIncreaseHealth() {
        character.decreaseHealth(5);
        int result = character.increaseHealth(5);
        int expResult = character.getMaxHealth();
        assertEquals("Should be 10 if HP was 5", expResult,result);
        
        result = character.increaseHealth(1);
        expResult = character.getMaxHealth();
        assertEquals("Should be 10 if max HP is 10", expResult, result);
    }
}
