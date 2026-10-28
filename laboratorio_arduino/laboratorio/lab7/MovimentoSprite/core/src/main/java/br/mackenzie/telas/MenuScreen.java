package br.mackenzie.telas;

import br.mackenzie.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen extends ScreenAdapter {

    private Main game;
    private Stage uiStage;

    public MenuScreen(Main game) {
        this.game = game;
        uiStage = new Stage(new FitViewport(Main.LARGURA_UI, Main.ALTURA_UI), game.batch);

        uiStage.addActor(game.criarLabelCentral("COLHEITA NA FLORESTA", 2.0f, Color.YELLOW, 380f));
        uiStage.addActor(game.criarLabelCentral("Pressione 1 a 5 para escolher o nivel", 1.2f, Color.WHITE, 290f));
        uiStage.addActor(game.criarLabelCentral("Pressione ENTER para jogar o Nivel 1", 1.2f, Color.GREEN, 240f));
    }

    @Override
    public void render(float delta) {
        input();
        draw(delta);
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

    private void draw(float delta) {
        ScreenUtils.clear(Color.BLACK);

        uiStage.getViewport().apply();
        game.batch.setProjectionMatrix(uiStage.getCamera().combined);
        game.batch.begin();
        game.batch.setColor(0.45f, 0.45f, 0.45f, 1f);
        game.batch.draw(game.backgroundTexture, 0f, 0f, Main.LARGURA_UI, Main.ALTURA_UI);
        game.batch.setColor(Color.WHITE);
        game.batch.end();

        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        uiStage.dispose();
    }
}