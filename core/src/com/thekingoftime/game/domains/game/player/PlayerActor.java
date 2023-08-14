package com.thekingoftime.game.domains.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.thekingoftime.game.domains.game.entities.AnimationEntity;
import com.thekingoftime.game.domains.game.player.entity.PlayerEntity;
import com.thekingoftime.game.domains.game.player.usecases.CreateTexturePlayerUseCase;
import com.thekingoftime.game.domains.game.shared.Shared;

import static com.thekingoftime.game.domains.game.shared.Constants.POSITION_INIT_PLAYER_X;
import static com.thekingoftime.game.domains.game.shared.Constants.POSITION_INIT_PLAYER_Y;

public class PlayerActor extends Actor {
    private Texture texture;
    private float speed;
    private OrthographicCamera camera;
    private PlayerEntity playerEntity;
    private AnimationEntity animation;
    private Label playerNameLabel;

    public PlayerActor() {
        this.camera = Shared.getInstance().getData().camera;
        this.playerEntity = new PlayerEntity().createGenericPlayer();

        this.setPlayerAnimation();
        this.configInfoAttributeClass();
        this.configPositionAndSizeToPlayer();
        this.setLabelNamePlayer();

        Shared.getInstance().getData().playerEntity = playerEntity;
        Shared.getInstance().getData().playerActor = this;
    }

    private void configInfoAttributeClass() {
        texture = this.playerEntity.visualEntity.animationEntity.texture;
        speed = this.playerEntity.playerSpeed;
        animation = this.playerEntity.visualEntity.animationEntity;
    }

    private void configPositionAndSizeToPlayer() {
        setBounds((Gdx.graphics.getWidth() - texture.getWidth()) / 2,
                (Gdx.graphics.getHeight() - texture.getHeight()) / 2,
                texture.getWidth(),
                texture.getHeight());

        this.setX(POSITION_INIT_PLAYER_X);
        this.setY(POSITION_INIT_PLAYER_Y);
    }

    @Override
    public void act(float delta) {
        float deltaX = 0;
        float deltaY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            deltaX -= speed * delta;

            animation.currentAnimation = animation.leftAnimation;
            animation.isMoving = true;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            deltaX += speed * delta;

            animation.currentAnimation = animation.rightAnimation;
            animation.isMoving = true;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            deltaY += speed * delta;

            animation.currentAnimation = animation.upAnimation;
            animation.isMoving = true;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            deltaY -= speed * delta;

            animation.currentAnimation = animation.downAnimation;
            animation.isMoving = true;
        }
        else {
            animation.isMoving = false;
        }

        if (animation.isMoving) {
            animation.animationTime += delta;
        }

        // if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
        // Verificar se está sobre collisao
        if (Shared.getInstance().getData().canMove(getX(), getY(), deltaX, deltaY) == false) {
            return;
        }
        //}

        moveBy(deltaX, deltaY);
        updateCamera();

        // Atualizar a posição do Label junto com a textura do jogador:
        playerNameLabel.setPosition(getX(), getY() + texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(animation.currentAnimation.getKeyFrame(animation.animationTime, true), getX(), getY());

        // Desenhar o Label com o nome do jogador:
        playerNameLabel.draw(batch, parentAlpha);
    }

    private void updateCamera() {
        camera.position.set(getX() + getWidth() / 2, getY() + getHeight() / 2, 0);
        camera.update();
    }

    private void setPlayerAnimation() {
        CreateTexturePlayerUseCase createTexturePlayerUseCase = new CreateTexturePlayerUseCase();
        playerEntity.visualEntity.animationEntity = createTexturePlayerUseCase.execute();
    }

    private void setLabelNamePlayer() {
        // Criar estilo para o Label:
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        playerNameLabel = new Label(playerEntity.name, labelStyle);
        playerNameLabel.setPosition(getX(), getY() + texture.getHeight());  // Coloque a posição x, y correta aqui

        playerEntity.visualEntity.playerNameLabel = playerNameLabel;
    }
}
