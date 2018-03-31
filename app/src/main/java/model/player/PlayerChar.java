
/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.player;

import model.character.ResourceManager;

/**
 *
 * @author Toasty Studios
 */
public abstract class PlayerChar extends model.character.Character {

    protected ResourceManager resourceManager;
    protected model.character.BattleMoves playerClass;
    private static final int STARTING_LEVEL = 1;
    private static final int EXP_PER_HP = 4;
    /*
    private int CONSTITUTION;
    private int STRENGTH;
    private int INTELLIGENCE;
    private int DEXTERITY;
    private double MODIFIER;
    */

    private int xp;

    public PlayerChar(String name, String playerClass, int constitution, int strength, int intelligence, int dexterity) {
        super(name, STARTING_LEVEL, playerClass, constitution, strength, intelligence, dexterity);
        this.resourceManager = new ResourceManager();
    }

    /**
     * Uses the player basic attack
     *
     * @return
     */
    @Override
    public abstract int basicAttack();

    /**
     * Uses the player ability
     *
     * @return
     */
    @Override
    public abstract int ability();

    /**
     * Uses the player ultimate
     *
     * @return
     */
    @Override
    public abstract int ultimate();
    
    
    /**
     * Returns how many resources the player has
     * @return 
     */
    public int getResources() {
        return this.resourceManager.getResources();
    }

    /**
     * Increases the character level by 1, only usable by other method that
     * checks if xp is > than required
     *
     * @return
     */
    private int increaseLevel() {
        return level++;
    }

    /**
     * Calculates the required xp to level
     *
     * @return
     */
    private double xpToLevel() {
        double total = 0;
        for (int i = 1; i < level; i++) {
            total += Math.floor(i + 300 * Math.pow(2, i / 7.0));
        }

        return Math.floor(total / 4);
    }

    /**
     * Calculates the xp the player will receive based on the monster health
     *
     * @return
     */
    private int experienceGained(model.character.Character enemy) {
        return enemy.getMaxHealth() * EXP_PER_HP;
    }

    /**
     * Increases the player xp. If the player reaches the threshold to level up,
     * increases player level.
     *
     * @param amount
     * @return
     */
    public int increaseXP(model.character.Character enemy) {
        this.xp += experienceGained(enemy);

        if (xp >= (xpToLevel())) {
            increaseLevel();
        }

        return xp;
    }

}
