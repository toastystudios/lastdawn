/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package toastystudios.lastdawn.domain.model.game;

import toastystudios.lastdawn.domain.model.player.Knight;
import toastystudios.lastdawn.domain.model.player.PlayerChar;

/**
 *
 * @author Toasty Studios
 */
public class Game {
    
    private static GameSlot currentSlot;
    private PlayerChar player;
    
    /**
     * Return in-game introduction
     */
    public static String getGameIntro(){
        return FileManager.getGameIntro();
    }
    
    /**
     * Return in-game character introduction
     */
    public static String getClassIntro(PlayerChar player){
        return FileManager.getClassIntro(player);
    }
    
    public static GameSlot createGameSlot(PlayerChar player){
        currentSlot = new GameSlot(player);
        return currentSlot;
    }
    
    public PlayerChar player() {
        return new Knight("José");
    }
}
