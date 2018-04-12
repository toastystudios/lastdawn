/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package controller;

import java.util.List;
import model.item.Item;

/**
 *
 * @author ruial
 */
public class InventoryController {
    
    private model.character.Character character;
    
    public InventoryController(model.character.Character character) {
        this.character = character;
    }
    
    public List<Item> invItems() {
        return character.inventory().getAllItems();
    }
}
