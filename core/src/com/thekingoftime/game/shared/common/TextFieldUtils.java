package com.thekingoftime.game.shared.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class TextFieldUtils {

    public static TextField create(String placeholder, boolean focusActived) {
        BitmapFont font = new BitmapFont();

        // Criar um NinePatchDrawable simples para o fundo
        NinePatchDrawable background = new NinePatchDrawable(new NinePatch(new Texture("imgs/white.png"), 3, 3, 3, 3));
        Drawable cursorDrawable = new TextureRegionDrawable(new Texture("imgs/cursor.png")); // Substitua "cursor.png" por sua imagem de cursor

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.cursor = cursorDrawable;
        textFieldStyle.background = background;

        final TextField textField = new TextField(placeholder, textFieldStyle);

        if (focusActived) {
            textField.addListener(new ClickListener() {
                private boolean firstTime = true;

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (firstTime) {
                        textField.setText("");
                        firstTime = false;
                    }
                }
            });
        }

        return textField;
    }
}
