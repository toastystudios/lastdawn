/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package toastystudios.lastdawn.domain.model.item;

/**
 *
 * @author Toasty Studios
 */
public abstract class Item {

    private String name;
    private String description;
    private int value;
    private String rarity;
    private boolean unique;
    private int level;

    public Item(String name, String description, int value, String rarity, boolean unique, int level) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.rarity = rarity;
        this.unique = unique;
        this.level = level;
    }
    
    /**
     * Returns the level of the item
     * @return 
     */
    public int level() {
        return level;
    }
    
    /**
     * Returns if the item is unique or not
     * @return 
     */
    public boolean isUnique() {
        return unique;
    }
    
    /**
     * Returns the item name
     * @return 
     */
    public String name() {
        return name;
    }

}
