package com.thekingoftime.game.shared.common;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonUtils {

    public static TextButton createButton(String nameButton) {
        BitmapFont font = new BitmapFont();

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        TextButton button = new TextButton(nameButton, textButtonStyle);
        return button;
    }
}
