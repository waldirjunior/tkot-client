package com.thekingoftime.game;

import com.badlogic.gdx.Game;
import com.thekingoftime.game.domains.game.GameScreen;

public class TKOTCore extends Game {

    public GameScreen gameScreen;
    @Override
    public void create() {
        gameScreen = new GameScreen(this);

        setScreen(gameScreen);
    }
}
