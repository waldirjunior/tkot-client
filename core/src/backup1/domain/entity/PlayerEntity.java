package backup1.domain.entity;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import backup1.shared.Singleton;

import static backup1.shared.Constants.*;

public class PlayerEntity {

    public int positionX, positionY;

    public TexturePlayerEntity texturePlayerEntity;

    public Label playerNameLabel;

    public boolean isMoving = false;

    public float playerSpeed = 200;


    public PlayerEntity create() {
        texturePlayerEntity = new TexturePlayerEntity().execute();

        setPositionPlayerStart();
        setLabelNamePlayer();

        return this;
    }

    private void setLabelNamePlayer() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        playerNameLabel = new Label("Nome do Jogador", labelStyle);
        playerNameLabel.setPosition(350, 285);  // Coloque a posição x, y correta aqui
    }

    private void setPositionPlayerStart() {
        int mapWidth = Singleton.getInstance().getSharedObject().mapWidth;
        int mapHeight = Singleton.getInstance().getSharedObject().mapHeight;
        int tileWidth = Singleton.getInstance().getSharedObject().tileWidth;
        int tileHeight = Singleton.getInstance().getSharedObject().tileHeight;

        // Todo: ajustar dps
        positionX = INIT_POSITION_X;
        positionY = INIT_POSITION_Y;
//        positionX = mapWidth * tileWidth / 2;
//        positionY = mapHeight * tileHeight / 2;

    }

}
