/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.npc;

import model.character.Stats;
import model.enums.StatTypes;

/**
 *
 * @author Toasty Studios
 */
public abstract class NPC extends model.character.Character {

    private final String name;
    private final String description;
    /**
     * init
     *
     * @param name
     * @param description
     */
    public NPC(String name, String description, int constitution, int strength, int intelligence, int dexterity, int level) {
        super(level, constitution, strength, intelligence, dexterity);
        this.name = name;
        this.description = description;
    }

}
