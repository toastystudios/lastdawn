package toastystudios.lastdawn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Map;

public class AppSettings {

    private static final String PREF_MUSIC_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_SOUND_VOL = "sound";
    private static final String FULLSCREEN = "fullscreen";
    private static final String RESOLUTION_WIDTH = "resolutionWidth";
    private static final String RESOLUTION_HEIGHT = "resolutionHeight";
    private static final String PREFS_NAME = "usersettings";

    protected Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    /**
     * Returns the current music volume
     * @return
     */
    public float getMusicVolume() {
        return getPrefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    /**
     * Sets a new music volume
     * @param volume
     */
    public void setMusicVolume(float volume) {
        getPrefs().putFloat(PREF_MUSIC_VOLUME, volume);
        getPrefs().flush(); //saves to disk
    }

    public boolean isSoundEffectsEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPrefs().flush();
    }

    public boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public void setMusicEnabled(boolean musicEnabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        getPrefs().flush();
    }

    public boolean isFullscreen() {
        return getPrefs().getBoolean(FULLSCREEN, false);
    }

    public void setFullscreen(boolean fullscreen) {
        getPrefs().putBoolean(FULLSCREEN, fullscreen);
        getPrefs().flush();
    }

    public float getSoundVolume() {
        return getPrefs().getFloat(PREF_SOUND_VOL, 0.5f);
    }

    public void setSoundVolume(float volume) {
        getPrefs().putFloat(PREF_SOUND_VOL, volume);
        getPrefs().flush();
    }

    public int[] getResolution() {
        int[] resolution = {getPrefs().getInteger(RESOLUTION_WIDTH, 800), getPrefs().getInteger(RESOLUTION_HEIGHT, 600)};
        return resolution;
    }

    public String getSelectedResolution() {
        int[] resolution = {getPrefs().getInteger(RESOLUTION_WIDTH, 800), getPrefs().getInteger(RESOLUTION_HEIGHT, 600)};
        return "" + resolution[0] + "x" + resolution[1] + "";
    }

    public void setResolution(int width, int height) {
        getPrefs().putInteger(RESOLUTION_WIDTH, width);
        getPrefs().putInteger(RESOLUTION_HEIGHT, height);
        getPrefs().flush();
    }

}
