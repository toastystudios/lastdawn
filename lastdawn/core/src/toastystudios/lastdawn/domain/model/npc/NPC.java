/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package toastystudios.lastdawn.domain.model.npc;

import toastystudios.lastdawn.domain.model.character.BattleMoves;
import toastystudios.lastdawn.domain.model.enums.StatTypes;
import toastystudios.lastdawn.domain.utils.NumberUtils;
import toastystudios.lastdawn.domain.model.character.Character;

/**
 *
 * @author Toasty Studios
 */
public class NPC extends Character implements BattleMoves {

    private static final double MODIFIER = 0.15;

    private final String name;
    private final String description;

    /**
     * init
     *
     * @param name
     * @param description
     */
    public NPC(String name, String description, int constitution, int strength, int intelligence, int dexterity, int level) {
        super(name, level, constitution, strength, intelligence, dexterity);
        this.name = name;
        this.description = description;
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
        return (int) NumberUtils.roundDown(((stats.strength() + inventory.equipmentStats().get(StatTypes.STRENGTH.toString())) * MODIFIER) + inventory.weaponDamage());
    }

    @Override
    public int ability() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ultimate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
