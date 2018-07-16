/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.game;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.enums.Abilities;
import model.npc.NPC;
import model.player.PlayerChar;
import utils.NumberUtils;
import model.character.Character;

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
            damage = attacker.basicAttack() - defender.defense();
        } else if (ability.equals(Abilities.ABILITY)) {
            damage = attacker.ability()     - defender.defense();
        } else if (ability.equals(Abilities.ULTIMATE)) {
            damage = attacker.ultimate()    - defender.defense();
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

    /**
     * Prompts user for their input regarding choosing an attack move
     *
     * @param player
     * @return
     */
    private int promptUser(PlayerChar player) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        boolean resources = true;
        boolean thrown = false;

        do {
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Unrecognized move!");
                thrown = true;
            }

        } while (option < 0 || option > 3 || thrown == true || resources == false);
        return option;
    }

    /**
     * Prints the player damage and decreases npc hp
     * @param damage
     * @param npc 
     */
    private void printPlayerDamage(int damage, NPC npc) {
        if (damage > 0) {
            System.out.println("\nYou hit your enemy for " + damage + " damage!");
            npc.decreaseHealth(damage);
        } else {
            System.out.println("\nYour attack missed!");
        }
    }

    /**
     * Prints the NPC damage and decreases player health
     * @param damage
     * @param player 
     */
    private void printNPCDamage(int damage, PlayerChar player) {
        if (damage > 0) {
            System.out.println("\nYou've been hit for " + damage + " damage!");
            player.decreaseHealth(damage);
        } else {
            System.out.println("\nYour enemy missed the attack!");
        }
    }

    /**
     * Tries to attack and verifies if the player can use the chosen attack move
     * @param player
     * @param npc 
     */
    private void attackMove(PlayerChar player, NPC npc) {
        int userMove = 0;
        boolean flag = true;
        int damage = 0;

        do {
            userMove = promptUser(player);

            if (userMove == 2) {

                if (player.ability() != -1) {
                    flag = true;
                    damage = attack(npc, player, Abilities.ABILITY);
                    printPlayerDamage(damage, npc);
                } else {
                    System.out.println("\nYou do not have enough resources to cast your ability!");
                    System.out.print("\n1. Basic Attack 2. Ability 3. Ultimate -> ");
                    flag = false;
                }

            } else if (userMove == 3) {

                if (player.ultimate() != -1) {
                    flag = true;
                    damage = attack(npc, player, Abilities.ULTIMATE);
                    printPlayerDamage(damage, npc);
                } else {
                    System.out.println("\nYou do not have enough resources to cast your ultimate!");
                    System.out.print("\n1. Basic Attack 2. Ability 3. Ultimate -> ");
                    flag = false;
                }

            } else {
                flag = true;
                damage = attack(npc, player, Abilities.BASIC);
                printPlayerDamage(damage, npc);
            }

        } while (flag == false);
    }

    /**
     * Prints the header for the player and npc health, and abilities available
     * @param player
     * @param npc 
     */
    private void printHeader(PlayerChar player, NPC npc) {
        System.out.println("\n------- Player -------  |  --------  NPC --------\n"
                + "Player Health: " + player.getCurrentHealth() + "\t|  Enemy Health: " + npc.getCurrentHealth() + "\n"
                + "Player Resources: " + player.getResources() + "\t|");
        System.out.print("\n1. Basic Attack 2. Ability 3. Ultimate -> ");
    }

    /**
     * Runs the battle
     * @param player
     * @param npc
     * @return
     * @throws InterruptedException 
     */
    public Character battlePVE(PlayerChar player, NPC npc) throws InterruptedException {
        int initiative = Dice.customRollDie(ROLL_INITIATIVE);
        int damage = 0;

        if (initiative >= ROLL_INITIATIVE / 2) {
            System.out.println("\nYou have gained initivate, you strike first!");
            Thread.sleep(250);
            printHeader(player, npc);
            Thread.sleep(250);
            attackMove(player, npc);
        }

        while (player.getCurrentHealth() > 0 && npc.getCurrentHealth() > 0) {

            //NPC attacks the player
            Thread.sleep(250);
            System.out.println("\nYour enemy is attacking!");
            damage = attack(player, npc, Abilities.BASIC);
            Thread.sleep(250);
            printNPCDamage(damage, player);

            if (player.getCurrentHealth() <= 0) {
                return npc;
            }
            
            Thread.sleep(250);
            System.out.println("\nIt's your turn!");
            Thread.sleep(250);
            printHeader(player, npc);
            Thread.sleep(250);
            attackMove(player, npc);

            if (npc.getCurrentHealth() <= 0) {
                return player;
            }

        }

        return null;

    }

}
