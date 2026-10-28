package br.mackenzie.telas;

import br.mackenzie.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen implements Screen {

    private final Main game;
    private final Stage stage;

    public MenuScreen(Main game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(Main.LARGURA_UI, Main.ALTURA_UI));

        stage.addActor(game.criarLabelCentral("COLHEITA NA FLORESTA", 2.0f, Color.YELLOW, 380f));
        stage.addActor(game.criarLabelCentral("Pressione 1 a 5 para escolher o nivel", 1.2f, Color.WHITE, 290f));
        stage.addActor(game.criarLabelCentral("Pressione ENTER para jogar o Nivel 1", 1.2f, Color.GREEN, 240f));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.12f, 0.18f, 0.12f, 1f);

        stage.act(delta);
        stage.draw();
    }
}