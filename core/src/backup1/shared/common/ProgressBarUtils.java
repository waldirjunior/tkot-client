package backup1.shared.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ProgressBarUtils {

    public static ProgressBar create(float min, float max, float stepSize, boolean vertical) {
        Texture healthBarTexture = new Texture(Gdx.files.internal("imgs/healthBar.png"));
        Texture healthFillTexture = new Texture(Gdx.files.internal("imgs/healthFill.png"));

        Drawable healthBarDrawable = new TextureRegionDrawable(new TextureRegion(healthBarTexture));
        Drawable healthFillDrawable = new TextureRegionDrawable(new TextureRegion(healthFillTexture));

        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = healthBarDrawable;
        progressBarStyle.knobBefore = healthFillDrawable; // A parte da barra à esquerda do indicador (se houver)
        progressBarStyle.knobAfter = healthFillDrawable;  // A parte da barra à direita do indicador (se houver)
        progressBarStyle.knob = new Image(healthFillTexture).getDrawable(); // Indicador da barra, se aplicável

        ProgressBar healthProgressBar = new ProgressBar(min, max, stepSize, vertical, progressBarStyle);

        return healthProgressBar;
    }
}

