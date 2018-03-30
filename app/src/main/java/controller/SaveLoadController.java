/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.game.BinFile;
import model.game.Game;

/**
 *
 * @author jpfr8
 */
public class SaveLoadController {
    
    public static BinFile controller;
    
    public static boolean gameLoader(String filename) throws IOException, FileNotFoundException, ClassNotFoundException{
        return controller.loadGame(filename);
    }
    
    public static boolean gameSaver(Game game, String filename) throws IOException{
        return controller.saveGame(game,filename);
    }
}
