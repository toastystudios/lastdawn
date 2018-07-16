/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.utils;

import java.io.*;

/**
 * Saves the user settings in a txt file and reads from it
 */
public class UserSettings {

    private boolean fullscreen;
    private int[] resolution = new int[2];

    public UserSettings(boolean fullscreen, int[] resolution){
        this.fullscreen = fullscreen;
        this.resolution = resolution;
    }

    public UserSettings() {}

    /**
     * If the file does not exist, creates a new file with the default settings.
     * Otherwise, reads the information from the file and parses it.
     * @return
     */
    public boolean readUserSettings() {

        File f = new File("usersettings.txt");
        if(f.exists() && !f.isDirectory()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader("usersettings.txt"));

                String[] line = in.readLine().split(";");

                if (line[0].equalsIgnoreCase("false")) this.fullscreen = false;
                else this.fullscreen = true;

                String[] resAux = line[1].split("-");

                this.resolution[0] = Integer.parseInt(resAux[0]);
                this.resolution[1] = Integer.parseInt(resAux[1]);

                in.close();
                return true;

            } catch (IOException e) {
                return false;
            }
        } else {
            this.resolution[0] = 1280;
            this.resolution[1] = 720;
            this.fullscreen = false;
            saveUserSettings();
            return true;
        }
    }

    /**
     * Saves the current user settings to a .txt file
     * @return
     */
    public boolean saveUserSettings() {
        try (PrintStream out = new PrintStream(new FileOutputStream("usersettings.txt"))) {
            out.print(fullscreen);
            out.print(";");
            out.print(resolution[0] + "-");
            out.print(resolution[1]);
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public void setResolution(int[] resolution) {
        this.resolution = resolution;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public int[] getResolution() {
        return resolution;
    }

}
