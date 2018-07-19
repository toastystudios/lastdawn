package toastystudios.lastdawn.engine;

import com.badlogic.gdx.Game;
import toastystudios.lastdawn.view.*;

public class GameLoader extends Game {

	private LoadingScreen loadingScreen;
	private OptionScreen optionScreen;
	private MenuScreen menuScreen;
	private NewGameScreen newGameScreen;
	private LoadGameScreen loadGameScreen;
	private AppSettings settings;

	public final static int MENU = 0;
	public final static int OPTIONS = 1;
	public final static int NEWGAME = 2;
	public final static int LOADGAME = 3;

	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		settings = new AppSettings();
		setScreen(loadingScreen);
	}

	public AppSettings getSettings() {
		return this.settings;
	}

	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case OPTIONS:
				if(optionScreen == null) optionScreen = new OptionScreen(this);
				this.setScreen(optionScreen);
				break;
			case NEWGAME:
				if(newGameScreen == null) newGameScreen = new NewGameScreen();
				this.setScreen(newGameScreen);
				break;
			case LOADGAME:
				if(loadGameScreen == null) loadGameScreen = new LoadGameScreen(this);
				this.setScreen(loadGameScreen);
				break;
		}
	}

	@Override
	public void render() {
		super.render();
	}
}
