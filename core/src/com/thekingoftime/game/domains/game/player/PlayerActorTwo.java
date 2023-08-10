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
import com.thekingoftime.game.domains.game.player.entity.PlayerEntity;
import com.thekingoftime.game.domains.game.player.usecases.CreateTexturePlayerUseCase;
import com.thekingoftime.game.domains.game.shared.Shared;

public class PlayerActorTwo extends Actor {
    private Texture texture;
    private float speed;
    private OrthographicCamera camera;
    private PlayerEntity playerEntity;
    private Label playerNameLabel;

    public PlayerActorTwo() {
        this.camera = Shared.getInstance().getData().camera;
        this.playerEntity = new PlayerEntity();

        this.configInfoPlayer();
        this.setPlayerAnimation();
        this.configInfoAttributeClass();
        this.configPositionAndSizeToPlayer();
        this.setLabelNamePlayer();

        Shared.getInstance().getData().playerEntity = playerEntity;
    }

    private void configInfoPlayer() {
        this.playerEntity.name = "Edward Elric";
        this.playerEntity.playerSpeed = 200;
    }

    private void configInfoAttributeClass() {
        texture = this.playerEntity.visualEntity.animationEntity.texture;
        speed = this.playerEntity.playerSpeed;
    }

    private void configPositionAndSizeToPlayer() {
        setBounds((Gdx.graphics.getWidth() - texture.getWidth()) / 2,
                (Gdx.graphics.getHeight() - texture.getHeight()) / 2,
                texture.getWidth(),
                texture.getHeight());

        this.setX(14862);
        this.setY(16477);
    }

    @Override
    public void act(float delta) {
        float deltaX = 0;
        float deltaY = 0;

        var animation = playerEntity.visualEntity.animationEntity;

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

        moveBy(deltaX, deltaY);
        updateCamera();

        // Atualizar a posição do Label junto com a textura do jogador:
        playerNameLabel.setPosition(getX(), getY() + texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        var animation = playerEntity.visualEntity.animationEntity;
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
