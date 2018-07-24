/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package toastystudios.lastdawn.domain.model.game;

import java.io.Serializable;
import java.util.Objects;
import toastystudios.lastdawn.domain.model.player.PlayerChar;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.player);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameSlot other = (GameSlot) obj;
        return true;
    }
    
    
}
