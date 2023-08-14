package com.thekingoftime.game.domains.game.map.monsters;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.thekingoftime.game.domains.game.map.monsters.entities.MonsterEntity;
import com.thekingoftime.game.domains.game.shared.Shared;
import com.thekingoftime.game.domains.game.shared.ports.ServicesInterface;

public class MonsterService implements ServicesInterface {

    private MapLayers objectLayers = new MapLayers();

    public void execute(MapGroupLayer monsterGroupLayer) {
        separateLayers(monsterGroupLayer.getLayers());

        for (MapLayer layer : objectLayers) {
            for (MapObject objectMap : layer.getObjects()) {
                if (objectMap instanceof TextureMapObject) {
                    TextureMapObject textureObj = (TextureMapObject) objectMap;

                    var monsterEntity = new MonsterEntity().createGenericMonster();

                    MonsterActor mtObject =
                            new MonsterActor(monsterEntity, textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());

                    Shared.getInstance().getData().stage.addActor(mtObject);
                }

            }
        }

    }


    private void separateLayers(MapLayers layers) {
        for (MapLayer layer : layers) {
            if (layer instanceof TiledMapTileLayer) {
            } else if (layer instanceof MapGroupLayer) {
                // Chama a função recursivamente para explorar subgrupos
                separateLayers(((MapGroupLayer) layer).getLayers());
            } else {
                // Adiciona a layer genérica, que pode incluir objetos TMX
                objectLayers.add(layer);
            }
        }
    }

    @Override
    public void execute() {
    }
}
