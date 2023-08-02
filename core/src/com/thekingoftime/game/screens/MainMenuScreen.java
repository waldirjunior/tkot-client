package com.thekingoftime.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.thekingoftime.game.TheKingOfTimeClient;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.thekingoftime.game.shared.common.ButtonUtils;
import com.thekingoftime.game.shared.common.LabelUtils;
import com.thekingoftime.game.shared.common.TextFieldUtils;

public class MainMenuScreen implements Screen {

    TheKingOfTimeClient game;

    private Stage stage;
    private TextField usernameField, passwordField;

    public MainMenuScreen(TheKingOfTimeClient game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        TextButton button = ButtonUtils.createButton("Login");
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // aqui você pode definir o que acontece quando o botão é clicado
                String username = usernameField.getText();
                String password = passwordField.getText();
                System.out.println("Clicked: " + username + password);
            }
        });

        Label usernameLabel = LabelUtils.create("Username");
        usernameField = TextFieldUtils.create("Username", true);

        Label passwordLabel = LabelUtils.create("Password");
        passwordField = TextFieldUtils.create("Password", true);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');


        Table table = new Table();

        table.add(usernameLabel);
        table.add(usernameField).width(100).height(50).padBottom(10);

        table.row();

        table.add(passwordLabel);
        table.add(passwordField).width(100).height(50).padBottom(10);

        table.row();

        table.setFillParent(true);

        table.center().add(button);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // renderize o menu principal aqui
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        // lidar com o redimensionamento da tela
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        // chamado quando esta tela é definida como a tela atual
    }

    @Override
    public void hide() {
        // chamado quando a tela atual muda de essa tela para uma tela diferente
    }
}
