package backup1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import backup1.screens.GameScreen;
import backup1.screens.MainMenuScreen;

public class TheKingOfTimeClient extends Game {

	public MainMenuScreen mainMenuScreen;
	public GameScreen gameScreen;

	@Override
	public void create () {
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);

		//setScreen(mainMenuScreen);
		setScreen(gameScreen);
	}

	public void switchScreen(Screen screen) {
		// Se a tela atual existe, descarta (dispose) ela
		if (this.screen != null) {
			this.screen.dispose();
		}

		// Altera para a nova tela
		setScreen(screen);
	}
}
