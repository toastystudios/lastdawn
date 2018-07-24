/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package toastystudios.lastdawn.domain.controller;

import java.util.List;
import toastystudios.lastdawn.domain.model.item.Item;
import toastystudios.lastdawn.domain.model.character.Character;

/**
 *
 * @author Toasty Studios
 */
public class InventoryController {
    
    private Character character;
    
    public InventoryController(Character character) {
        this.character = character;
    }
    
    public List<Item> invItems() {
        return character.inventory().getAllItems();
    }
}
