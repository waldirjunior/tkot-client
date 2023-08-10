package com.thekingoftime.game.domains.game.player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.thekingoftime.game.domains.game.player.entity.PlayerEntity;

public class PlayerActor extends Actor {

    private PlayerEntity playerEntity;
    private Label playerNameLabel;
    private OrthographicCamera camera;
    /*
    public PlayerActor() {
        this.camera = Shared.getInstance().getData().camera;

        playerEntity = new PlayerEntity();

        playerEntity.name = "Edward Elric";
        playerEntity.level = 1;

        setLabelNamePlayer();
        setPlayerAnimation();

        var currentAnimation =
                playerEntity.playerAnimationEntity.playerAnimation.get(0);
        TextureRegion texture = currentAnimation.getKeyFrames()[0];

        playerEntity.width = texture.getRegionWidth();
        playerEntity.height = texture.getRegionHeight();

        playerEntity.positionX = 14862;
        playerEntity.positionY = 16477;

        setBounds((Gdx.graphics.getWidth() - 44) / 2,
                (Gdx.graphics.getHeight() - 40) / 2,
                30,
                40);

        Shared.getInstance().getData().playerEntity = playerEntity;
    }

    @Override
    public void act(float delta) {
        Data data = Shared.getInstance().getData();
        PlayerEntity playerEntity = data.playerEntity;

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerEntity.positionY += playerEntity.playerSpeed * delta;
            playerEntity.playerAnimationEntity.currentAnimation =
                    playerEntity.playerAnimationEntity.playerAnimation.get(0);
            playerEntity.playerAnimationEntity.isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerEntity.positionY -= playerEntity.playerSpeed * delta;
            playerEntity.playerAnimationEntity.currentAnimation =
                    playerEntity.playerAnimationEntity.playerAnimation.get(1);
            playerEntity.playerAnimationEntity.isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerEntity.positionX -= playerEntity.playerSpeed * delta;
            playerEntity.playerAnimationEntity.currentAnimation  =
                    playerEntity.playerAnimationEntity.playerAnimation.get(2);
            playerEntity.playerAnimationEntity.isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerEntity.positionX += playerEntity.playerSpeed * delta;
            playerEntity.playerAnimationEntity.currentAnimation  =
                    playerEntity.playerAnimationEntity.playerAnimation.get(3);
            playerEntity.playerAnimationEntity.isMoving = true;
        } else {
            playerEntity.playerAnimationEntity.isMoving = false;
        }

        if (playerEntity.playerAnimationEntity.isMoving) {
            playerEntity.playerAnimationEntity.animationTime += delta;
        }

        moveBy(playerEntity.positionX, playerEntity.positionY);
        updateCamera();
    }

    private void updateCamera() {
        camera.position.set(playerEntity.positionX, playerEntity.positionY, 0);
        camera.update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Data data = Shared.getInstance().getData();

        // Desenhar a animação atual
        batch.draw(data.playerEntity.playerAnimationEntity.currentAnimation.getKeyFrame(
                data.playerEntity.playerAnimationEntity.animationTime, true), getWidth(), getHeight());
    }

    private void setPlayerAnimation() {
       /*
        CreateTexturePlayerUseCase createTexturePlayerUseCase = new CreateTexturePlayerUseCase();
        List<Animation<TextureRegion>> animations = createTexturePlayerUseCase.execute();

        playerEntity.playerAnimationEntity = new PlayerAnimationEntity(animations);
    }

    private void setLabelNamePlayer() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        playerNameLabel = new Label(playerEntity.name, labelStyle);
        playerNameLabel.setPosition(350, 285);  // Coloque a posição x, y correta aqui

        Shared.getInstance().getData().stage.addActor(playerNameLabel);
    }
*/
}
