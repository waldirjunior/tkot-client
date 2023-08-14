package com.thekingoftime.game.domains.game.map.monsters.usescases;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.thekingoftime.game.domains.game.entities.AnimationEntity;
import com.thekingoftime.game.domains.game.entities.PositionTextureEntity;
import com.thekingoftime.game.domains.game.shared.Shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateTextureUseCase {

    private static final float FRAME_DURATION = 0.1f; // duração de cada frame da animação

    private String name;
    private List<PositionTextureEntity> positionTextureEntities = new ArrayList<>();

    private List<TextureRegion> textureRegionsUP = new ArrayList<>();
    private List<TextureRegion> textureRegionsDOWN = new ArrayList<>();
    private List<TextureRegion> textureRegionsLEFT = new ArrayList<>();
    private List<TextureRegion> textureRegionsRIGHT = new ArrayList<>();

    public CreateTextureUseCase(String name) {
        this.name = name;
    }

    public AnimationEntity execute() {
        loadTexture();

        for (PositionTextureEntity positionTextureEntity : positionTextureEntities) {
            if (positionTextureEntity.referencePosition.contains("up")) {
                textureRegionsUP.add(positionTextureEntity.textureRegion);
            } else if (positionTextureEntity.referencePosition.contains("down")) {
                textureRegionsDOWN.add(positionTextureEntity.textureRegion);
            } else if (positionTextureEntity.referencePosition.contains("left")) {
                textureRegionsLEFT.add(positionTextureEntity.textureRegion);
            } else if (positionTextureEntity.referencePosition.contains("right")) {
                textureRegionsRIGHT.add(positionTextureEntity.textureRegion);
            }
        }

        TextureRegion[][] framesUp = new TextureRegion[textureRegionsUP.size()][1];
        for (int i = 0; i < textureRegionsUP.size(); i++) {
            framesUp[i][0] = textureRegionsUP.get(i);
        }

        TextureRegion[][] framesDown = new TextureRegion[textureRegionsDOWN.size()][1];
        for (int i = 0; i < textureRegionsDOWN.size(); i++) {
            framesDown[i][0] = textureRegionsDOWN.get(i);
        }

        TextureRegion[][] framesLeft = new TextureRegion[textureRegionsLEFT.size()][1];
        for (int i = 0; i < textureRegionsLEFT.size(); i++) {
            framesLeft[i][0] = textureRegionsLEFT.get(i);
        }

        TextureRegion[][] framesRight = new TextureRegion[textureRegionsRIGHT.size()][1];
        for (int i = 0; i < textureRegionsRIGHT.size(); i++) {
            framesRight[i][0] = textureRegionsRIGHT.get(i);
        }

        Animation<TextureRegion> animationUp = new Animation<>(FRAME_DURATION, framesUp[0]);
        Animation<TextureRegion> animationDown = new Animation<>(FRAME_DURATION, framesDown[0]);
        Animation<TextureRegion> animationLeft = new Animation<>(FRAME_DURATION, framesLeft[0]);
        Animation<TextureRegion> animationRight = new Animation<>(FRAME_DURATION, framesRight[0]);

        return new AnimationEntity(
                animationLeft,
                animationRight,
                animationUp,
                animationDown,
                animationDown,
                textureRegionsDOWN.get(0).getTexture()
        );
    }

    private void loadTexture() {
        TiledMap tiledMap = Shared.getInstance().getData().tiledMap;

        for (TiledMapTileSet tileSet : tiledMap.getTileSets()) {
            if (Objects.equals(tileSet.getName(), "monster")) {
                for (TiledMapTile tile : tileSet) {
                    if (tile instanceof StaticTiledMapTile) {
                        String nameProperty = tile.getProperties().get("name", String.class);
                        if (name.equals(nameProperty)) {
                            // Este é um dos tiles que você está procurando
                            // Você pode fazer algo com ele aqui
                            // Por exemplo:

                            var position = new PositionTextureEntity();
                            position.textureRegion = ((StaticTiledMapTile) tile).getTextureRegion();
                            position.referencePosition = tile.getProperties().get("direction", String.class);

                            positionTextureEntities.add(position);
                        }
                    }
                }

            }

        }
    }

}
