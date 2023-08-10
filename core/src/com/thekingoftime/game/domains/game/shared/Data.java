package com.thekingoftime.game.domains.game.shared;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thekingoftime.game.domains.game.player.entity.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Data {

    public Stage stage;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public PlayerEntity playerEntity;
    public float delta;
    public OrthogonalTiledMapRenderer tiledMapRenderer;

    // Metodos e atributos privados

    private List<Consumer> listMethodPreRender = new ArrayList<>();

    private List<Consumer> listMethodDispose = new ArrayList<>();

    private List<Consumer> listMethodStageBatchRender = new ArrayList<>();

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
