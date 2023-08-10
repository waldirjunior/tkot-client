package backup1.domain.usecases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import backup1.domain.entity.PlayerEntity;
import backup1.shared.common.LabelUtils;
import backup1.shared.common.ProgressBarUtils;

public class BottomBarUseCase {

    public Label levelLabel;
    public ProgressBar healthBar;
    public Label positionLabel;

    // Exemplos de valores que você pode atualizar
    public int playerLevel = 5;
    public float playerHealth = 0.5f; // 50%
    public Vector2 playerPosition = new Vector2(100, 200);

    public BottomBarUseCase execute(Stage stage) {

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0, 0, 0, 0.5f)); // Preto com 50% de opacidade
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();


        Table bottomBar = new Table();
        bottomBar.setBackground(new TextureRegionDrawable(new TextureRegion(texture)));
        //bottomBar.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("imgs/white.png")))); // Substitua pelo seu fundo
        bottomBar.setSize(Gdx.graphics.getWidth(), 80);
        bottomBar.setPosition(0, 0);

        levelLabel = LabelUtils.create("Level: " + playerLevel);

        healthBar = ProgressBarUtils.create(0, 1, 0.01f, false); // Cria uma barra de progresso (veja o código em ProgressBarUtils.java
        healthBar.setValue(playerHealth);
        positionLabel = LabelUtils.create("Posição: (" + playerPosition.x + ", " + playerPosition.y + ")");

        bottomBar.add(levelLabel).expandX().pad(10);
        bottomBar.add(healthBar).expandX().pad(10);
        bottomBar.add(positionLabel).expandX().pad(10);

        stage.addActor(bottomBar);

        Gdx.input.setInputProcessor(stage);

        return this;
    }

    public void render(Stage stage, PlayerEntity playerEntity) {
        levelLabel.setText("Level: " + playerLevel);
        healthBar.setValue(playerHealth);
        positionLabel.setText("Posição: (" + playerEntity.positionX + ", " + playerEntity.positionY + ")");

    }
}
