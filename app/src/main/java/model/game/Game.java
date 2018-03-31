/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package model.game;

/**
 *
 * @author Toasty Studios
 */
public class Game {
    
    /**
     * Return in-game introduction
     */
    public static String getGameIntro(){
        //deve ir buscar a um ficheiro externo o texto a inserir aqui
        
        return FileManager.getGameIntro();
    }
    
    /**
     * Return in-game character introduction
     */
    public static String getClassIntro(){
        return FileManager.getClassIntro();
    }
}
