package com.thekingoftime.game.domains.game.map.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.thekingoftime.game.domains.game.map.objects.shared.MapObjectsObject;
import com.thekingoftime.game.domains.game.shared.Shared;
import com.thekingoftime.game.domains.game.shared.ports.ServicesInterface;

public class ObjectsService implements ServicesInterface {

    @Override
    public void execute() {

        var tiledMap = Shared.getInstance().getData().tiledMap;
        var stage = Shared.getInstance().getData().stage;

        Array<TiledMapTileLayer> tileLayers = new Array<>();
        Array<MapGroupLayer> groupLayers = new Array<>();
        Array<MapLayer> objectLayers = new Array<>();

        separateLayers(tiledMap.getLayers(), tileLayers, groupLayers, objectLayers);

        Array<MapObjects> listObjects = new Array<>();

        for (MapLayer objectLayer: objectLayers) {
            MapObjects objects = objectLayer.getObjects();
            listObjects.add(objects);
        }

        for (MapObjects objects: listObjects) {
            for (MapObject object : objects) {
                if (object instanceof TextureMapObject) {
                    TextureMapObject textureObj = (TextureMapObject) object;
                    MapObjectsObject gameObject =
                            new MapObjectsObject(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());
                    stage.addActor(gameObject);
                }
            }
        }

        System.out.println("tileLayers: " + tileLayers.size);
    }

    private void separateLayers(MapLayers layers, Array<TiledMapTileLayer> tileLayers, Array<MapGroupLayer> groupLayers, Array<MapLayer> objectLayers) {
        for (MapLayer layer : layers) {
            if (layer instanceof TiledMapTileLayer) {
                tileLayers.add((TiledMapTileLayer) layer);
            } else if (layer instanceof MapGroupLayer) {
                groupLayers.add((MapGroupLayer) layer);
                // Chama a função recursivamente para explorar subgrupos
                separateLayers(((MapGroupLayer) layer).getLayers(), tileLayers, groupLayers, objectLayers);
            } else {
                // Adiciona a layer genérica, que pode incluir objetos TMX
                objectLayers.add(layer);
            }
        }
    }

}
