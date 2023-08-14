package com.thekingoftime.game.domains.game.map.objects.shared;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MapObjectsObject extends Actor {
    private TextureRegion textureRegion;

    public MapObjectsObject(TextureRegion textureRegion, float x, float y) {
        this.textureRegion = textureRegion;
        setPosition(x, y);
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY());
    }
}
