package com.thekingoftime.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.thekingoftime.game.screens.GameScreen;
import com.thekingoftime.game.screens.MainMenuScreen;

public class TheKingOfTimeClient extends Game {

	public MainMenuScreen mainMenuScreen;
	public GameScreen gameScreen;

	@Override
	public void create () {
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(mainMenuScreen);
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
