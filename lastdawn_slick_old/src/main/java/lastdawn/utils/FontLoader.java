/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.utils;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;

/**
 * Converts a java awt font to a slick2d font
 */
public class FontLoader {

    /**
     * Loads a font from the disk and converts it from a java awt font to a slick 2d font.
     * Note: We must add an effect to the font for it to display - it's a slick2d problem and cannot be avoided.
     * The effect we added does not do anything noticeable, though.
     *
     * @param fontSize
     * @param fontName
     * @return
     */
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
