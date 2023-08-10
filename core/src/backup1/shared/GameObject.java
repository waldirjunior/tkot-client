package backup1.shared;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameObject extends Actor {
    private TextureRegion textureRegion;

    public GameObject(TextureRegion textureRegion, float x, float y) {
        this.textureRegion = textureRegion;
        setPosition(x, y);
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        System.out.println("Desenhando GameObject"); // Verificação
        batch.draw(textureRegion, getX(), getY());
    }
}
