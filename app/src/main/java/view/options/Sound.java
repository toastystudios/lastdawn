/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view.options;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author ruial
 */
public class Sound {
    
    private AudioFormat af;
    private Clip clip;
    
    public Sound(){};

    public void initSound() {
        try {
            InputStream is = getClass().getResourceAsStream("/sound/menu.wav");
            AudioInputStream as1 = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            clip = AudioSystem.getClip();
            DataLine.Info info = new DataLine.Info(Clip.class, af);

            Line line = AudioSystem.getLine(info);

            if (!line.isOpen()) {
                clip.open(as1);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The audio file has imploded! Bad luck to whoever is going to fix this..");
        } catch (IOException ex) {
            System.out.println("IO Exception..not again!");
        } catch (LineUnavailableException ex) {
            System.out.println("Line is unavailable, please call on a later time!");
        }
    }
    
    public void stopClip() {
        clip.stop();
    }
    
    

}
