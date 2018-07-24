/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package toastystudios.lastdawn.domain.model.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import toastystudios.lastdawn.domain.model.player.PlayerChar;

/**
 *
 * @author Toasty Studios
 */
public class FileManager {

    public static void saveGame(GameSlot slot, String filename) throws FileNotFoundException, IOException {

        try (FileOutputStream outputFile = new FileOutputStream(filename)) {

            ObjectOutputStream outputObj = new ObjectOutputStream(outputFile);

            try {

                outputObj.writeObject(slot);

            } finally {

                outputObj.close();

            }
        } catch (IOException e) {
            System.err.println("File not found");
        }
    }

    public static GameSlot loadGame(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {

        GameSlot slot = null;
        try (FileInputStream inputFile = new FileInputStream(filename)){

            ObjectInputStream inputObj = new ObjectInputStream(inputFile);

            try {

                slot = (GameSlot) inputObj.readObject();

            } finally {
                inputObj.close();
            }
        } catch (NullPointerException | IOException e) {
            System.err.println("File not found");
        }
        return slot;
    }

    public void getItemlist() {
        //RETORNA LISTA DE TODOS OS OBJECTOS ITEM (INICIO DE JOGO);
    }

    /**
     * Return in-game introduction
     */
    public static String getGameIntro() {
        //deve ir buscar a um ficheiro externo o texto a inserir aqui

        return "";
    }

    /**
     * Return in-game character introduction
     */
    public static String getClassIntro(PlayerChar player) {
        //deve ir buscar a um ficheiro externo o texto a inserir aqui
        //faz a identificação da classe do player para saber qual o ficheiro a ler

        String classIntro = null;
        if (player.getClass().getSimpleName() == "Knight") {

        }
        if (player.getClass().getSimpleName() == "Paladin") {

        }
        if (player.getClass().getSimpleName() == "Archer") {

        }
        if (player.getClass().getSimpleName() == "Scientist") {

        }

        return classIntro;
    }

}


