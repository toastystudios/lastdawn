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
public class FileManager {

    public static boolean saveGame(Game game, String filename) throws FileNotFoundException, IOException {

        ObjectOutputStream outputObj = null;

        try {

            FileOutputStream outputFile = new FileOutputStream(filename);
            outputObj = new ObjectOutputStream(outputFile);

            outputObj.writeObject(game);
            outputObj.close();
            return true;
        }catch(FileNotFoundException f){
            return false;
        }
    }

    public static boolean loadGame(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {

        Game game = null;
        ObjectInputStream inputObj = null;

        try {

            FileInputStream inputFile = new FileInputStream(filename);
            inputObj = new ObjectInputStream(inputFile);

            game = (Game) inputObj.readObject();
            inputObj.close();
            return true;
        } catch (FileNotFoundException f) {
            return false;
        }
    }
    
    public void getItemlist(){
        //RETORNA LISTA DE TODOS OS OBJECTOS ITEM (INICIO DE JOGO);
    }
}