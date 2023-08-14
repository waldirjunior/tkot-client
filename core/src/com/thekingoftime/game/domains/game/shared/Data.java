package com.thekingoftime.game.domains.game.shared;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.thekingoftime.game.domains.game.player.PlayerActor;
import com.thekingoftime.game.domains.game.player.entity.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Data {

    public Stage stage;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public PlayerEntity playerEntity;
    public PlayerActor playerActor;

    public float delta;
    public OrthogonalTiledMapRenderer tiledMapRenderer;
    public TiledMap tiledMap;

    // Metodos e atributos privados

    private List<Consumer> listMethodPreRender = new ArrayList<>();

    private List<Consumer> listMethodDispose = new ArrayList<>();

    private List<Consumer> listMethodStageBatchRender = new ArrayList<>();

    public Array<TiledMapTileLayer> collisionLayers = new Array<>();

    public boolean canMove(float positionX, float positionY, float deltaX, float deltaY) {
        var newX = positionX + deltaX;
        var newY = positionY + deltaY;

        // Iterar sobre todas as camadas relevantes
        boolean saveDecision = true;
        for (TiledMapTileLayer layer : collisionLayers) {

            int playerTileX = (int) (newX / layer.getTileWidth());
            int playerTileY = (int) (newY / layer.getTileHeight());

            TiledMapTileLayer.Cell cell = layer.getCell(playerTileX, playerTileY);

            if (cell != null && cell.getTile() != null) {
                // Camada encontrada contendo o tile na posição dada
                Boolean isBlocked = layer.getProperties().get("block", Boolean.class);
                if (isBlocked != null && isBlocked) {
                    saveDecision = false; // Não pode mover
                } else {
                    saveDecision = true;
                }
            }

            /*int tileWidth = (int) layer.getTileWidth();
            int tileHeight = (int) layer.getTileHeight();

            int tileX = (int) (x / tileWidth);
            int tileY = (int) (y / tileHeight);

            TiledMapTileLayer.Cell cell = layer.getCell(tileX, tileY);
            if (cell != null) {
                TiledMapTile tile = cell.getTile();
                if (tile != null) {
                    // Verificar a propriedade personalizada "blocked"
                    Boolean isBlocked = tile.getProperties().get("block", Boolean.class);
                    if (isBlocked != null && isBlocked) {
                        return false; // Não pode mover
                    }
                }
            }*/
        }

        return saveDecision; // Pode mover
    }


    public void addMethodPreRender(Consumer method) {
        listMethodPreRender.add(method);
    }

    public void executeMethodPreRender(Data data) {
        for (Consumer method : listMethodPreRender) {
            try {
                method.accept(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addMethodDispose(Consumer method) {
        listMethodDispose.add(method);
    }

    public void executeMethodDispose() {
        for (Consumer method : listMethodDispose) {
            try {
                method.accept(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addMethodStageBatchRender(Consumer method) {
        listMethodStageBatchRender.add(method);
    }

    public void executeMethodStageBatchRender(Data data) {
        for (Consumer method : listMethodStageBatchRender) {
            try {
                method.accept( data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
