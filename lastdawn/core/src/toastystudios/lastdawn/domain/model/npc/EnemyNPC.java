/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package toastystudios.lastdawn.domain.model.npc;

import java.util.Random;


/**
 *
 * @author Toasty Studios
 */
public class EnemyNPC extends NPC {

    private double rareDropChance;

    public EnemyNPC(String name, String description, int constitution, int strength, int intelligence, int dexterity, int level) {
        super(name, description, constitution, strength, intelligence, dexterity, level);
        this.rareDropChance = Math.random() * 5;
    }

    public double getDropChance() {
        return rareDropChance;
    }

}
