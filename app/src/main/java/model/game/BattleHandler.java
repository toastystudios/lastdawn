/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.game;

import model.enums.Abilities;
import model.npc.NPC;
import model.player.PlayerChar;
import utils.NumberUtils;
import model.character.Character;
import scala.Console;

/**
 *
 * @author Toasty Studios
 */
public class BattleHandler {

    private static final int CRITICAL_MISS = 2;
    private static final int CRITICAL_HIT = 19;
    private static final int MISS = 0;
    private static final double CRITICAL_HIT_MODIFIER = 1.3;
    private static final int ROLL_BONUS_HIT = 4;
    private static final int ROLL_INITIATIVE = 10;

    private int attack(Character defender, Character attacker, Abilities ability) {

        //Roll a dice from 1 to 20
        int diceRoll = Dice.rollDie();

        //Calculate attacker damage if hits
        int damage = 0;

        if (ability.equals(Abilities.BASIC)) {
            damage = attacker.basicAttack() + Dice.customRollDie(ROLL_BONUS_HIT) - defender.defense();
        } else if (ability.equals(Abilities.ABILITY)) {
            damage = attacker.ability() + Dice.customRollDie(ROLL_BONUS_HIT) - defender.defense();
        } else if (ability.equals(Abilities.ULTIMATE)) {
            damage = attacker.ultimate() + Dice.customRollDie(ROLL_BONUS_HIT) - defender.defense();
        }

        //Try critical hit
        if (diceRoll >= CRITICAL_HIT) {
            return (int) NumberUtils.roundDown(damage * CRITICAL_HIT_MODIFIER);
        }

        //Try critical miss
        if (diceRoll <= CRITICAL_MISS) {
            attacker.decreaseHealth(1);
            return MISS;
        }

        //Compare the roll against enemy's dexterity
        if (diceRoll >= defender.dexterity()) {

            if (damage >= 1) {
                return damage;
            } else {
                return MISS;
            }

        } else {
            return MISS;
        }
    }

}
