package backup1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import backup1.TheKingOfTimeClient;
import backup1.domain.usecases.BottomBarUseCase;
import backup1.domain.usecases.ControlCameraUseCase;
import backup1.domain.usecases.ControlPlayerUseCase;
import backup1.domain.usecases.LoadMapUseCase;
import backup1.shared.GameObject;
import backup1.shared.Singleton;

public class GameScreen implements Screen {

    TheKingOfTimeClient game;

    private Stage stage;

    private SpriteBatch batch;

    private ControlPlayerUseCase controlPlayerUseCase;

    private ControlCameraUseCase controlCameraUseCase;

    private LoadMapUseCase loadMapUseCase;

    private BottomBarUseCase bottomBarUseCase;

    Image imageActor;

    Texture texture;

    public GameScreen(TheKingOfTimeClient game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        // Iniciar o SpriteBatch
        batch = new SpriteBatch();

        loadMapUseCase = new LoadMapUseCase().execute(stage); // 1
        controlPlayerUseCase = new ControlPlayerUseCase().execute(); // 2
        controlCameraUseCase = new ControlCameraUseCase().execute(); // 3
        bottomBarUseCase = new BottomBarUseCase().execute(stage); // 4

        // tkot-map/assets/general-sprites/1665.gif
        texture = new Texture(Gdx.files.internal("others/bunny.png"));
        TextureRegion textureRegion = new TextureRegion(texture);
        GameObject gameObject = new GameObject(textureRegion, 14983, 16880); // Exemplo de posição (x, y).

        imageActor = new Image(texture);


        stage.addActor(controlPlayerUseCase.playerEntity.playerNameLabel);
        stage.addActor(imageActor);

        // Opcionalmente, definir o palco como processador de entrada para detectar toques/clicks.
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        // Detecta o movimento do jogador
        controlPlayerUseCase.detectMotion(delta);

        // Atualiza a posição da câmera para acompanhar o jogador
        controlCameraUseCase.cameraUpdated(controlPlayerUseCase.playerEntity.positionX,
                controlPlayerUseCase.playerEntity.positionY, stage);

        loadMapUseCase.renderMap(controlCameraUseCase.camera);

        stage.getBatch().begin();

        // Desenha a textura do jogador
        controlPlayerUseCase.renderStage(stage);

        stage.getBatch().end();

        bottomBarUseCase.render(stage, controlPlayerUseCase.playerEntity);

        // Atualizar e desenhar o palco
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        // Desenhar usando o SpriteBatch
        batch.begin();
        batch.end();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    @Override
    public void dispose() {
        this.loadMapUseCase.dispose();
        stage.dispose();
        batch.dispose();
        Singleton.getInstance().getSharedObject().stageGameMain.dispose();
    }
}
