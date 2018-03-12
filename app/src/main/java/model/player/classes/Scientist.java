/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.player.classes;

import model.character.Stats;

/**
 *
 * @author Toasty Studios
 */
public class Scientist implements model.player.Class {

    private static final int BASE_CONSTITUTION = 10;
    private static final int BASE_STRENGTH = 10;
    private static final int BASE_INTELLIGENCE = 10;
    private static final int BASE_DEXTERITY = 10;

    private Stats stats;

    public Scientist() {
        this.stats = new Stats(BASE_CONSTITUTION, BASE_STRENGTH, BASE_INTELLIGENCE, BASE_DEXTERITY);
    }

    /**
     * Returns the class base stats
     *
     * @return
     */
    @Override
    public Stats baseStats() {
        return stats;
    }
}
