/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.player;

import model.enums.StatTypes;
import model.player.PlayerChar;
import utils.NumberUtils;

/**
 *
 * @author Toasty Studios
 */
public class Ranger extends PlayerChar implements model.character.BattleMoves {

    private static final int BASE_CONSTITUTION = 10;
    private static final int BASE_STRENGTH = 10;
    private static final int BASE_INTELLIGENCE = 10;
    private static final int BASE_DEXTERITY = 10;
    private static final double MODIFIER = 0.15;

    public Ranger(String name) {
        super(name, Ranger.class.getSimpleName(), BASE_CONSTITUTION, BASE_STRENGTH, BASE_INTELLIGENCE, BASE_DEXTERITY);
    }
    
    // =======================================
    // ----------- COMBAT METHODS ------------
    // =======================================
    /**
     * Uses the character basic attack
     *
     * @return
     */
    @Override
    public int basicAttack() {
        resourceManager.generateResource();
        return (int) NumberUtils.roundDown(((stats.strength() + inventory.equipmentStats().get(StatTypes.STRENGTH.toString())) * MODIFIER) + inventory.weaponDamage());
    }

    /**
     * Uses the character ability
     *
     * @return
     */
    @Override
    public int ability() {
        if (resourceManager.spendAbilityResource() != -1) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return -1;
        }
    }

    /**
     * Uses the character ultimate
     *
     * @return
     */
    @Override
    public int ultimate() {
        if (resourceManager.spendUltimateResources() != -1) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return -1;
        }
    }
}
