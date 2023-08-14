package com.thekingoftime.game.domains.game.map;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.thekingoftime.game.domains.game.map.monsters.MonsterService;
import com.thekingoftime.game.domains.game.map.objects.ObjectsService;
import com.thekingoftime.game.domains.game.map.objects.shared.MapObjectsObject;
import com.thekingoftime.game.domains.game.shared.Data;
import com.thekingoftime.game.domains.game.shared.Shared;
import com.thekingoftime.game.domains.game.shared.ports.ServicesInterface;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class MapService implements ServicesInterface {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private Stage stage;
    private Array<MapLayer> objectLayers = new Array<>();
    private Array<TiledMapTileLayer> tileLayers = new Array<>();
    private Array<MapGroupLayer> groupLayers = new Array<>();

    @Override
    public void execute() {
        // Carregar o mapa
        tiledMap = new TmxMapLoader().load("tkot-map/tmx.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        stage = Shared.getInstance().getData().stage;

        Shared.getInstance().getData().tiledMapRenderer = tiledMapRenderer;
        Shared.getInstance().getData().tiledMap = tiledMap;

        Shared.getInstance().getData().addMethodPreRender((Consumer<Data>) MapService::preRender);

        organizeLayers();
    }

    public static void preRender(Data data) {
        data.tiledMapRenderer.setView(data.camera);
        data.tiledMapRenderer.render();
    }

    private void organizeLayers() {
        separateLayers(tiledMap.getLayers(), tileLayers, groupLayers, objectLayers);

        saveCollisionLayersSharedData();
        executeElementsMap();
    }

    private void executeElementsMap() {
        MapGroupLayer[] mapGroupLayers = groupLayers.toArray(MapGroupLayer.class);

        MapGroupLayer monster = Arrays.stream(mapGroupLayers).filter(mapGroupLayer -> mapGroupLayer.getName().equals("monsters")).findFirst().get();
        new MonsterService().execute(monster);
    }

    private void saveCollisionLayersSharedData() {
        Shared.getInstance().getData().collisionLayers = tileLayers;
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
