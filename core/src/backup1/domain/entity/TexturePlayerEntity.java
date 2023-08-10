package backup1.domain.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TexturePlayerEntity {
    private static final float FRAME_DURATION = 0.1f; // duração de cada frame da animação

    public Texture texture;
    public Animation<TextureRegion> playerAnimationUp;
    public Animation<TextureRegion> playerAnimationDown;
    public Animation<TextureRegion> playerAnimationLeft;
    public Animation<TextureRegion> playerAnimationRight;

    public Animation<TextureRegion> currentAnimation;
    public float animationTime;

    public TexturePlayerEntity execute() {
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

        playerAnimationUp = new Animation<TextureRegion>(FRAME_DURATION, playerFramesUp[0]);
        playerAnimationDown = new Animation<TextureRegion>(FRAME_DURATION, playerFramesDown[0]);
        playerAnimationLeft = new Animation<TextureRegion>(FRAME_DURATION, playerFramesLeft[0]);
        playerAnimationRight = new Animation<TextureRegion>(FRAME_DURATION, playerFramesRight[0]);

        currentAnimation = playerAnimationDown; // Começamos com o jogador olhando para baixo

        animationTime = 0;

        return this;
    }

    private TextureRegion getPositionTexture(int x, int y, int w, int h) {
        TextureRegion textureRegion = new TextureRegion(texture, x, y, w, h);
        return textureRegion;
    }
}
