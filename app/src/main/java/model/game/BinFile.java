/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author jpfr8
 */
public class BinFile {
    
    public static void saveGame(Game game,String filename) throws FileNotFoundException, IOException{
        
        ObjectOutputStream outputObj = null;
        
        try{
            
            FileOutputStream outputFile = new FileOutputStream(filename);
            outputObj = new ObjectOutputStream(outputFile);
            
            outputObj.writeObject(game);
        }finally{
            outputObj.close();
        }
    }
    
    public static void loadGame(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        Game game = null;
        ObjectInputStream inputObj = null;
        
        try{
            
            FileInputStream inputFile = new FileInputStream(filename);
            inputObj = new ObjectInputStream(inputFile);
            
            game = (Game)inputObj.readObject();
        }finally{
            inputObj.close();
        }
    }
}
