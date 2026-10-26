package br.mackenzie.telas;

import br.mackenzie.Main;
import br.mackenzie.config.Nivel;
import br.mackenzie.objetos.Cesta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ScreenAdapter {

    private Main game;
    private Nivel nivel;
    private FitViewport viewport;
    private Cesta cesta;

    public GameScreen(Main game, int numeroNivel) {
        this.game = game;
        nivel = Nivel.NIVEIS[numeroNivel - 1];

        viewport = new FitViewport(Main.LARGURA_MUNDO, Main.ALTURA_MUNDO);

        cesta = new Cesta(game.cestaTexture, Main.LARGURA_MUNDO * nivel.fracaoCesta);

    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    private void input() {
    
    }

    private void logic() {
        float delta = Math.min(
            Gdx.graphics.getDeltaTime(), 0.05f
        );

        game.joystick.update(delta);

        cesta.mover(
            game.calibracao.getPosicao(
                game.joystick.getEixo()
            )
        );

        cesta.update(delta);
    }

}

   