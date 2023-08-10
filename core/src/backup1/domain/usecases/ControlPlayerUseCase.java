package backup1.domain.usecases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import backup1.domain.entity.PlayerEntity;

public class ControlPlayerUseCase {

    public PlayerEntity playerEntity;

    public ControlPlayerUseCase execute() {
        playerEntity = new PlayerEntity().create();
        return this;
    }

    public void renderStage(Stage stage) {
        stage.getBatch().draw(playerEntity.texturePlayerEntity.currentAnimation.getKeyFrame(
                playerEntity.texturePlayerEntity.animationTime, true), playerEntity.positionX, playerEntity.positionY);
    }

    public void detectMotion(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerEntity.positionY += playerEntity.playerSpeed * delta;
            playerEntity.texturePlayerEntity.currentAnimation = playerEntity.texturePlayerEntity.playerAnimationUp;
            playerEntity.isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerEntity.positionY -= playerEntity.playerSpeed * delta;
            playerEntity.texturePlayerEntity.currentAnimation = playerEntity.texturePlayerEntity.playerAnimationDown;
            playerEntity.isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerEntity.positionX -= playerEntity.playerSpeed * delta;
            playerEntity.texturePlayerEntity.currentAnimation = playerEntity.texturePlayerEntity.playerAnimationLeft;
            playerEntity.isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerEntity.positionX += playerEntity.playerSpeed * delta;
            playerEntity.texturePlayerEntity.currentAnimation = playerEntity.texturePlayerEntity.playerAnimationRight;
            playerEntity.isMoving = true;
        } else {
            playerEntity.isMoving = false;
        }

        if (playerEntity.isMoving) {
            playerEntity.texturePlayerEntity.animationTime += delta;
        }

    }


}
