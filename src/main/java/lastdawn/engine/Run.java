package lastdawn.engine;

import java.io.File;

public class Run {

    public static void main(String[] args) {
        System.setProperty("java.library.path", "natives");
        System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", new File("natives").getAbsolutePath());
        new GameLoader("Last Dawn").run();
    }
}
