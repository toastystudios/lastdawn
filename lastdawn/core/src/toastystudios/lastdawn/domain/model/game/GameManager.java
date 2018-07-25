/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package toastystudios.lastdawn.domain.model.game;

import toastystudios.lastdawn.domain.controller.NewGameController;
import toastystudios.lastdawn.domain.model.enums.ClassTypes;
import toastystudios.lastdawn.domain.model.player.Knight;
import toastystudios.lastdawn.domain.model.player.PlayerChar;

/**
 * Class responsible for all back-end management of resources not related to libgdx front-end and engine
 *
 * Can be viewed as a bridge between modules
 *
 * @author Toasty Studios
 */
public class GameManager {
    
    private static GameSlot currentSlot;
    private PlayerChar player;

    //for factory purposes
    private static GameManager gm;

    private GameManager(){
    }

    /**
     * @return GameManager instance
     */
    public static GameManager getGameManager(){
        if(gm==null){
            gm=new GameManager();
        }
        return gm;
    }

    /**
     * Generates a new game controller that is responsible for creating a new game and all the required components not
     * related to libgdx.
     *
     * Since the controller is a local variable, before terminating the method, the required parameters of the controller
     * are stored in GameManager instance variables.
     *
     * @param name the name of the new player
     * @param type the class type of the new player
     */
    public void createNewGame(String name, ClassTypes type){
        NewGameController controller = new NewGameController(name,type);
        this.player=controller.getPlayer();
    }
}
