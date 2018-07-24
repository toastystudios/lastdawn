/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package toastystudios.lastdawn.domain.model.character;

/**
 *
 * @author Toasty Studios
 */
public class Stats {

    private int constitution;
    private int strength;
    private int intelligence;
    private int dexterity;

    public Stats(int constitution, int strength, int intelligence, int dexterity) {
        this.constitution = constitution;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
    }

    public int constitution() {
        return constitution;
    }

    public int strength() {
        return strength;
    }

    public int intelligence() {
        return intelligence;
    }

    public int dexterity() {
        return dexterity;
    }
    
    

    
}
