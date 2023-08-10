package com.thekingoftime.game.domains.game.player;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.thekingoftime.game.domains.game.player.entity.PlayerEntity;
import com.thekingoftime.game.domains.game.shared.Shared;
import com.thekingoftime.game.domains.game.shared.ports.ServicesInterface;

public class PlayerService implements ServicesInterface {

    private PlayerEntity playerEntity;
    private Label playerNameLabel;

    @Override
    public void execute() {
        var stage = Shared.getInstance().getData().stage;

        PlayerActorTwo playerActorTwo = new PlayerActorTwo();
        stage.addActor(playerActorTwo);

        /*playerEntity = new PlayerEntity();

        playerEntity.name = "Edward Elric";
        playerEntity.level = 1;

        setLabelNamePlayer();
        setPositionPlayerStart();
        setPlayerAnimation();

        Shared.getInstance().getData().playerEntity = playerEntity;

        Shared.getInstance().getData().addMethodPreRender((Consumer<Data>) PlayerService::preRender);
        Shared.getInstance().getData().addMethodStageBatchRender((Consumer<Data>) PlayerService::stageBatchRender);*/
    }

    /*
    public static void stageBatchRender(Data data) {
        data.stage.getBatch().draw(data.playerEntity.playerAnimationEntity.currentAnimation.getKeyFrame(
                data.playerEntity.playerAnimationEntity.animationTime, true), data.playerEntity.positionX, data.playerEntity.positionY);
    }

    public static void preRender(Data data) {
        float delta = data.delta;
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
    }

    private void setPlayerAnimation() {
        CreateTexturePlayerUseCase createTexturePlayerUseCase = new CreateTexturePlayerUseCase();
        List<Animation<TextureRegion>> animations = createTexturePlayerUseCase.execute();

        playerEntity.playerAnimationEntity = new PlayerAnimationEntity(animations);
    }

    private void setPositionPlayerStart() {
    }

    private void setLabelNamePlayer() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        playerNameLabel = new Label(playerEntity.name, labelStyle);
        playerNameLabel.setPosition(350, 285);  // Coloque a posição x, y correta aqui

        Shared.getInstance().getData().stage.addActor(playerNameLabel);
    }*/
}
