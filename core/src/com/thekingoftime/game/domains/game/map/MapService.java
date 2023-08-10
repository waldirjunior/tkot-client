package com.thekingoftime.game.domains.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.thekingoftime.game.domains.game.shared.Data;
import com.thekingoftime.game.domains.game.shared.Shared;
import com.thekingoftime.game.domains.game.shared.ports.ServicesInterface;

import java.util.function.Consumer;

public class MapService implements ServicesInterface {

    @Override
    public void execute() {
        // Carregar o mapa
        TiledMap tiledMap = new TmxMapLoader().load("tkot-map/tmx.tmx");
        OrthogonalTiledMapRenderer tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        Shared.getInstance().getData().tiledMapRenderer = tiledMapRenderer;

        Shared.getInstance().getData().addMethodPreRender((Consumer<Data>) MapService::preRender);
    }

    public static void preRender(Data data) {
        data.tiledMapRenderer.setView(data.camera);
        data.tiledMapRenderer.render();
    }
}
