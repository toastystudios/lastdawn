/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package controller;

import java.io.IOException;
import java.util.List;
import model.enums.ClassTypes;
import model.game.FileManager;
import model.game.Game;
import model.game.GameSlot;
import model.player.Knight;
import model.player.Paladin;
import model.player.PlayerChar;
import model.player.Ranger;
import model.player.Scientist;

/**
 *
 * @author Toasty Studios
 */
public class NewGameController {
    
    private List<Enum> listClass;
    private PlayerChar player;
    
    private GameSlot slot;
    private String saveGameName;
    
    private String classIntro;
    
    public NewGameController(){
    }
    
    /**
     * Get in-game introduction
     * 
     * @return String with game introduction
     */
    public String getGameIntro(){
        return Game.getGameIntro();
    }
    
    /**
     * Get the list of available classes in game
     * 
     * @return list of classes in game
     */
    public List<Enum> getClasses(){
        listClass = ClassTypes.getListClass();
        return listClass;
    }
    
    /**
     * Creates the new character
     * 
     * @param name the input name of the character
     * @param playerClass  the input class picked for the character
     */
    public void createChar(String name,String playerClass) throws IOException{
       //If option picked was Knight
       if(playerClass==ClassTypes.KNIGHT.toString()){
           player=new Knight(name);
       }
       //If option picked was Paladin
       if(playerClass==ClassTypes.PALADIN.toString()){
           player=new Paladin(name);
       }
       //If option picked was Paladin
       if(playerClass==ClassTypes.RANGER.toString()){
           player=new Ranger(name);
       }
       //If option picked was Paladin
       if(playerClass==ClassTypes.SCIENTIST.toString()){
           player=new Scientist(name);
       }  
       
       //Create save game slot and the file to store the session
       slot = Game.createGameSlot(player);
       FileManager.saveGame(slot, saveGameName);      
    }
    
    public String getClassIntro(PlayerChar player){
        return Game.getClassIntro(player);
    }
    
    //...input start...
    
}
