/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 *
 * @author Toasty Studios
 */
public class ResourceUtil {
    
    public static final String CLOSE_MESSAGE = "<html><font color=#ffffdd>Are you sure you want to exit the game?</font>";

    public static Image getImage(final String pathAndFileName) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        return Toolkit.getDefaultToolkit().getImage(url);
    }
}
