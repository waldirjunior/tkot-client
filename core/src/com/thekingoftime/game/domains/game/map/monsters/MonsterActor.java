package com.thekingoftime.game.domains.game.map.monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.thekingoftime.game.domains.game.entities.AnimationEntity;
import com.thekingoftime.game.domains.game.map.monsters.entities.MonsterEntity;
import com.thekingoftime.game.domains.game.map.monsters.usescases.CreateTextureUseCase;
import com.thekingoftime.game.domains.game.map.monsters.usescases.MovimentUseCase;
import com.thekingoftime.game.domains.game.player.PlayerActor;
import com.thekingoftime.game.domains.game.shared.Shared;

public class MonsterActor extends Actor {
    private TextureRegion textureRegion;
    private float stateTime; // Tempo acumulado no estado atual
    private boolean isWalking; // Se o monstro está andando ou parado
    public boolean targetPlayer; // Se o monstro está perseguindo o jogador
    private AnimationEntity animation;
    private MovimentUseCase movimentUseCase;
    public PlayerActor playerActor;
    private float initialX, initialY;
    public MonsterEntity monsterEntity;

    public MonsterActor(MonsterEntity monsterEntity, TextureRegion textureRegion, float x, float y) {
        this.textureRegion = textureRegion;
        this.monsterEntity = monsterEntity;

        setPosition(x, y);
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

        initialX = x;
        initialY = y;

        isWalking = false;
        stateTime = 0f;
        targetPlayer = false;

        animation = new CreateTextureUseCase(monsterEntity.name).execute();
        movimentUseCase = new MovimentUseCase(this, x, y, animation);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;

        if (isWalking) {
            if (stateTime > monsterEntity.walkTime && !targetPlayer) {
                // Mudar para o estado parado
                isWalking = false;
                stateTime = 0f;
            } else {
                // Lógica de movimento (como descrito anteriormente)
                movimentUseCase.execute(delta);
            }
        } else {
            if (stateTime > monsterEntity.walkTime) {
                // Mudar para o estado de movimento
                isWalking = true;
                stateTime = 0f;
            }
            // Lógica opcional para o estado parado (como animação de espera)
        }

        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame = animation.currentAnimation.getKeyFrame(stateTime);
        batch.draw(frame, getX(), getY());
    }

    public float getInitialX() {
        return initialX;
    }

    public float getInitialY() {
        return initialY;
    }
}
