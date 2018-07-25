/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package toastystudios.lastdawn.domain.controller;

import java.io.IOException;
import java.util.List;
import toastystudios.lastdawn.domain.model.enums.ClassTypes;
import toastystudios.lastdawn.domain.model.game.GameManager;
import toastystudios.lastdawn.domain.model.game.GameSlot;
import toastystudios.lastdawn.domain.model.player.Knight;
import toastystudios.lastdawn.domain.model.player.Paladin;
import toastystudios.lastdawn.domain.model.player.PlayerChar;
import toastystudios.lastdawn.domain.model.player.Ranger;
import toastystudios.lastdawn.domain.model.player.Scientist;

/**
 *
 * @author Toasty Studios
 */
public class NewGameController {
    
    private List<Enum> listClass;
    private PlayerChar player;
    
    private GameSlot slot;

    /**
     * Constructor that has the responsibility of generating a new game, with
     * all the back-end requirements not related to libgdx
     *
     * @param name the name of the new player
     * @param classType the class type of the new player
     */
    public NewGameController(String name,ClassTypes classType){
            createChar(name,classType);
    }

    /**
     * Creates the new character
     * 
     * @param name the input name of the character
     * @param classType  the input class picked for the character
     */
    public void createChar(String name,ClassTypes classType){
       //If option picked was Knight
      switch(classType){
          case KNIGHT:
              player = new Knight(name);
              System.out.println("Knight created with name "+name);
              break;
          case RANGER:
              player = new Ranger(name);
              System.out.println("Ranger created with name "+name);
              break;
          case PALADIN:
              player = new Paladin(name);
              System.out.println("Paladin created with name "+name);
              break;
          case SCIENTIST:
              player = new Scientist(name);
              System.out.println("Scientist created with name "+name);
              break;
              default:
                  //TODO: error handling (maybe throw exception para ser mais facil?)
                  System.out.println("No player was created");
      }
    }

    public PlayerChar getPlayer(){
        return this.player;
    }
}
