package com.thekingoftime.game.domains.game.player.usecases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thekingoftime.game.domains.game.player.entity.AnimationEntity;

import java.util.ArrayList;
import java.util.List;

public class CreateTexturePlayerUseCase {
    private static final float FRAME_DURATION = 0.1f; // duração de cada frame da animação

    private Texture texture;

    public AnimationEntity execute() {
        texture = new Texture(Gdx.files.internal("others/otsp_creatures_03.png"));

        TextureRegion down = getPositionTexture(26, 27, 44, 40);
        TextureRegion downWalk1 = getPositionTexture(26, 91, 44, 40);
        TextureRegion downWalk2 = getPositionTexture(26, 155, 44, 40);

        TextureRegion right = getPositionTexture(26, 218, 44, 40);
        TextureRegion rightWalk1 = getPositionTexture(26, 346, 44, 40);
        TextureRegion rightWalk2 = getPositionTexture(26, 281, 44, 40);

        TextureRegion up = getPositionTexture(26, 475, 44, 40);
        TextureRegion upWalk1 = getPositionTexture(26, 540, 44, 40);
        TextureRegion upWalk2 = getPositionTexture(26, 476, 44, 40);

        TextureRegion left = getPositionTexture(26, 603, 44, 40);
        TextureRegion leftWalk1 = getPositionTexture(26, 667, 44, 40);
        TextureRegion leftWalk2 = getPositionTexture(26, 730, 44, 40);

        TextureRegion[][] playerFramesUp = new TextureRegion[][] {
                {up, upWalk1, upWalk2, up}
        };
        TextureRegion[][] playerFramesDown = new TextureRegion[][] {
                {down, downWalk1, downWalk2, down}
        };
        TextureRegion[][] playerFramesLeft = new TextureRegion[][] {
                {left, leftWalk1, leftWalk2, left}
        };
        TextureRegion[][] playerFramesRight = new TextureRegion[][] {
                {right, rightWalk1, rightWalk2, right}
        };

        Animation<TextureRegion> playerAnimationUp = new Animation<>(FRAME_DURATION, playerFramesUp[0]);
        Animation<TextureRegion> playerAnimationDown = new Animation<>(FRAME_DURATION, playerFramesDown[0]);
        Animation<TextureRegion> playerAnimationLeft = new Animation<>(FRAME_DURATION, playerFramesLeft[0]);
        Animation<TextureRegion> playerAnimationRight = new Animation<>(FRAME_DURATION, playerFramesRight[0]);

        var newTexture = textureRegionToTexture(down);

        return new AnimationEntity(
                playerAnimationLeft,
                playerAnimationRight,
                playerAnimationUp,
                playerAnimationDown,
                playerAnimationDown,
                newTexture
        );
    }

    private TextureRegion getPositionTexture(int x, int y, int w, int h) {
        return new TextureRegion(texture, x, y, w, h);
    }

    public Texture textureRegionToTexture(TextureRegion region) {
        // Cria um Pixmap com as mesmas dimensões da TextureRegion
        Pixmap pixmap = new Pixmap(region.getRegionWidth(), region.getRegionHeight(), region.getTexture().getTextureData().getFormat());

        // Transfere os pixels da TextureRegion para o Pixmap
        region.getTexture().getTextureData().prepare();
        pixmap.drawPixmap(region.getTexture().getTextureData().consumePixmap(), 0, 0, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());

        // Cria uma nova Texture a partir do Pixmap
        Texture newTexture = new Texture(pixmap);

        // Descarta o Pixmap para liberar memória
        pixmap.dispose();

        return newTexture;
    }
}
