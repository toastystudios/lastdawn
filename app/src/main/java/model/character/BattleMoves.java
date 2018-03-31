/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package model.character;

import model.character.Stats;

/**
 *
 * @author Toasty Studios
 */
public interface BattleMoves{
    
    /**
     * Uses the character basic attack
     * @return 
     */
    public int basicAttack();
    
    /**
     * Uses the character ability
     * @return 
     */
    public int ability();
    
    /**
     * Uses the character ultimate
     * @return 
     */
    public int ultimate();

}
