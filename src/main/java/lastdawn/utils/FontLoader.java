/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.utils;

import org.newdawn.slick.SlickException;
import  org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;

public class FontLoader {

    public static UnicodeFont getFont(int fontSize, String fontName) {
        UnicodeFont ufont = null;
        try {
            Font font = Font.createFont(java.awt.Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("/assets/fonts/" + fontName));
            ufont = new UnicodeFont(font, fontSize, font.isBold(), font.isItalic());
            ufont.addAsciiGlyphs();
            ufont.addGlyphs(400, 600);
            ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
            ufont.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ufont;
    }
}
