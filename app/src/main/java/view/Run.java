/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
<<<<<<< HEAD
=======
import model.game.Game;
>>>>>>> 35be38d49089fd17d925e28abd755f5b262e8238
import view.options.Sound;

/**
 *
 * @author Toasty Studios
 */
public class Run {

    private static Logger logger = Logger.getLogger("InfoLogging");

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        logger.info("Logging an INFO-level message");
<<<<<<< HEAD
          
        new Sound();
        new MainMenuUI();
=======

        Game game = new Game();
>>>>>>> 35be38d49089fd17d925e28abd755f5b262e8238
        
        new MainFrame(game);
        for (int i = 0; i < 20; i++) {
            System.out.println("system out");
        }
        
        Sound sound = new Sound();
        sound.initSound();

    }

}
