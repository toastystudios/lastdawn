package lastdawn.engine;

import lastdawn.states.States;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;

public class GameLoader extends StateBasedGame {

    private static final String NAME = "Last Dawn";
    public static AppGameContainer appgc;

    public GameLoader(String name) {
        super(name);
        this.addState(new MenuState());
        this.addState(new InGame());
        this.addState(new LoadState());
        this.addState(new OptionsState());
        this.addState(new NewState());
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(States.MENU).init(gameContainer, this);
        this.enterState(States.MENU);
    }

    public void run() {
        try {
           appgc = new AppGameContainer(new GameLoader(NAME));
           appgc.setDisplayMode(800,600, false);
           appgc.setVSync(true);
           appgc.start();
        } catch(SlickException ex) {
            System.out.println("The thing went skrrraaa.." + ex.getMessage());
        }
    }
}
