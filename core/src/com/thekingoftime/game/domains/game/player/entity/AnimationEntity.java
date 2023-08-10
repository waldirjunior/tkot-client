package com.thekingoftime.game.domains.game.player.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;

public class AnimationEntity {
    public Animation<TextureRegion> leftAnimation;
    public Animation<TextureRegion> rightAnimation;
    public Animation<TextureRegion> upAnimation;
    public Animation<TextureRegion> downAnimation;
    public Animation<TextureRegion> currentAnimation;
    public Texture texture;
    public float animationTime;
    public boolean isMoving;

    public AnimationEntity(
            Animation<TextureRegion> leftAnimation,
            Animation<TextureRegion> rightAnimation,
            Animation<TextureRegion> upAnimation,
            Animation<TextureRegion> downAnimation,
            Animation<TextureRegion> currentAnimation,
            Texture texture
            ) {

        this.currentAnimation = currentAnimation;
        this.animationTime = 0;
        this.isMoving = false;
        this.texture = texture;
        this.leftAnimation = leftAnimation;
        this.rightAnimation = rightAnimation;
        this.upAnimation = upAnimation;
        this.downAnimation = downAnimation;
    }

}
