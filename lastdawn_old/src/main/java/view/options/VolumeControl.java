/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view.options;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;

/**
 *
 * @author ruial
 */
public class VolumeControl {
    
    private boolean isMuted = false;

    /**
     * Tries to mute or unmute all the sound in the application
     * if true, it will mute the app
     * if false, it will unmute the app
     */
    public void manageSound(boolean mute) {
        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        for (Mixer.Info info : infos) {
            Mixer mixer = AudioSystem.getMixer(info);
            Line[] lines = mixer.getSourceLines();
            for (Line line : lines) {
                BooleanControl bc = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
                if (bc != null) {
                    bc.setValue(mute);
                    isMuted = mute;
                }
            }
        }
    }
    
    public boolean getMutedStatus() {
        return isMuted;
    }


}
