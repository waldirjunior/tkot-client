package backup1.domain.usecases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ControlCameraUseCase {
    public OrthographicCamera camera;

    public ControlCameraUseCase execute() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        return this;
    }

    public void cameraUpdated(int positionX, int positionY, Stage stage) {
        // Atualiza a posição da câmera para acompanhar o jogador
        camera.position.set(positionX, positionY, 0);
        camera.update();

        stage.getBatch().setProjectionMatrix(camera.combined);
    }
}
