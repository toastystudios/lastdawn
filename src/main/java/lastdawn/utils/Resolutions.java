/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.utils;

public class Resolutions {

    private int height;
    private int width;


    private static final int[] SMALL = {800, 600};
    private static final int[] HD = {1280, 720};
    private static final int[] MEDIUM = {1440, 900};
    private static final int[] MEDIUM_HD = {1600, 900};
    private static final int[] FULL_HD = {1920, 1080};

    public Resolutions(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int[] cycleResolutions() {
        if (this.width == SMALL[0] && this.height == SMALL[1]) {
            this.width = HD[0];
            this.height = HD[1];
            return HD;
        } else if (this.width == HD[0] && this.height == HD[1]) {
            this.width = MEDIUM[0];
            this.height = MEDIUM[1];
            return MEDIUM;
        } else if (this.width == MEDIUM[0] && this.height == MEDIUM[1]) {
            this.width = MEDIUM_HD[0];
            this.height = MEDIUM_HD[1];
            return MEDIUM_HD;
        } else if (this.width == MEDIUM_HD[0] && this.height == MEDIUM_HD[1]) {
            this.width = FULL_HD[0];
            this.height = FULL_HD[1];
            return FULL_HD;
        } else if (this.width == FULL_HD[0] && this.height == FULL_HD[1]) {
            this.width = SMALL[0];
            this.height = SMALL[1];
            return SMALL;
        }
        return null;
    }


}
