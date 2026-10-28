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

        input();

        stage.act(delta);
        stage.draw();
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.iniciarJogo(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.iniciarJogo(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            game.iniciarJogo(2);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            game.iniciarJogo(3);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            game.iniciarJogo(4);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            game.iniciarJogo(5);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}