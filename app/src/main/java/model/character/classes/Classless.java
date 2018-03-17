/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.character.classes;

import model.character.Stats;

/**
 *
 * @author Toasty Studios
 */
public class Classless implements model.character.Class{

    
    private Stats stats;
    
    public Classless (int constitution, int strength, int intelligence, int dexterity) {
        this.stats = new Stats(constitution, strength, intelligence, dexterity);
    }

    /**
     * Returns the class base stats
     * @return 
     */
    @Override
    public Stats baseStats() {
        return stats;
    }
}
