/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.game;

import java.io.Serializable;
import model.player.PlayerChar;

/**
 *
 * @author Toasty Studios
 */
public class GameSlot implements Serializable{
    
    private PlayerChar player;
    //...posição no mapa
    
    
    public GameSlot(PlayerChar player){
        this.player=player;
    }
    
    
    public void updateGameSlot(){
        
    }
}
