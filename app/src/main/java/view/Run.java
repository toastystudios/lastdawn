/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import model.game.Game;
import view.options.Sound;

/**
 *
 * @author Toasty Studios
 */
public class Run {

    private static Logger logger = Logger.getLogger("InfoLogging");

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        logger.info("Logging an INFO-level message");

        Game game = new Game();
        
        new MainFrame(game);
        for (int i = 0; i < 20; i++) {
            System.out.println("system out");
        }
        
        Sound sound = new Sound();
        sound.initSound();

    }

}
