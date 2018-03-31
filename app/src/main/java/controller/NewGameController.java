/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package controller;

import java.util.List;
import model.enums.ClassTypes;
import model.game.Game;

/**
 *
 * @author Henrique Moura Costa
 */
public class NewGameController {
    
    private Game game;
    private List<ClassTypes> listClass;
    
    
    public NewGameController(){
    }
    
    /**
     * Get in-game introduction
     * 
     * @return String with game introduction
     */
    public String getGameIntro(){
        return game.getGameIntro();
    }
    
    /**
     * Get the list of available classes in game
     * 
     * @return list of classes in game
     */
    public List<Enum> getClasses(){
        return ClassTypes.getListClass();
    }
    
}
