package com.thekingoftime.game.domains.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.thekingoftime.game.TKOTCore;
import com.thekingoftime.game.domains.game.map.MapService;
import com.thekingoftime.game.domains.game.player.PlayerService;
import com.thekingoftime.game.domains.game.shared.Shared;

public class GameScreen implements Screen {
    TKOTCore game;
    private SpriteBatch batch;
    private Stage stage;
    private OrthographicCamera camera;

    public GameScreen(TKOTCore game) {
        this.game = game;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);

        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), batch);

        Shared.getInstance().getData().stage = stage;
        Shared.getInstance().getData().batch = batch;
        Shared.getInstance().getData().camera = camera;

        new MapService().execute();
        new PlayerService().execute();

        // Definir o palco como processador de entrada, se necessário
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Shared.getInstance().getData().delta = delta;

        // Limpar a tela
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Shared.getInstance().getData().executeMethodPreRender(Shared.getInstance().getData());

        stage.getBatch().begin();
        Shared.getInstance().getData().executeMethodStageBatchRender(Shared.getInstance().getData());
        stage.getBatch().end();

        // Atualizar e desenhar o palco
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        // Descartar os recursos
        batch.dispose();
        stage.dispose();

        Shared.getInstance().getData().executeMethodDispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
