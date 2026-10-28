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

        uiStage.addActor(game.criarLabelCentral("Colheita na Floresta", 3.2f, Color.YELLOW, 400f));
        uiStage.addActor(game.criarLabelCentral("Reabilitação motora", 1.6f, Color.WHITE, 360f));

        uiStage.addActor(game.criarLabelCentral("ENTER  -  Jogar a partir do nível 1", 1.6f, Color.WHITE, 280f));
        uiStage.addActor(game.criarLabelCentral("1 a 5  -  Escolher o nível", 1.6f, Color.WHITE, 240f));
        uiStage.addActor(game.criarLabelCentral("ESC  -  Sair", 1.6f, Color.WHITE, 160f));
    }

    @Override
    public void render(float delta) {
        input();
        draw(delta);
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.iniciarJogo(1);
            return;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        for (int i = 1; i <= 5; i++) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1 + i - 1)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1 + i - 1)) {
                game.iniciarJogo(i);
                return;
            }
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