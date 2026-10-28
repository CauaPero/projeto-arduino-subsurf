package br.mackenzie.telas;

import br.mackenzie.Main;
import br.mackenzie.config.Nivel;
import br.mackenzie.objetos.Cesta;
import br.mackenzie.objetos.Fruta;
import br.mackenzie.objetos.Coco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends ScreenAdapter {

    private static final float TAMANHO_FRUTA = 0.7f;

    private static final int JOGANDO = 0;
    private static final int NIVEL_CONCLUIDO = 2;
    private static final int TEMPO_ESGOTADO = 3;
    private static final int JOGO_COMPLETO = 4;

    private static final float TEMPO_LIMITE = 180f;

    private Main game;
    private Nivel nivel;
    private FitViewport viewport;
    private Stage uiStage;
    private Label scoreLabel;
    private Label nivelLabel;
    private Label timeLabel;
    private Label tituloLabel;
    private Label detalheLabel;

    private Cesta cesta;
    private Array<Fruta> frutas;

    private int estado;
    private float spawnTimer;
    private float tempoRestante;
    private int pontos;

    public GameScreen(Main game, int numeroNivel) {
        this.game = game;
        nivel = Nivel.NIVEIS[numeroNivel - 1];

        viewport = new FitViewport(Main.LARGURA_MUNDO, Main.ALTURA_MUNDO);
        uiStage = new Stage(new FitViewport(Main.LARGURA_UI, Main.ALTURA_UI), game.batch);

        scoreLabel = game.criarLabel("", 1.6f, Color.WHITE, 20f, Main.ALTURA_UI - 45f, 300f, Align.left);
        nivelLabel = game.criarLabelCentral("Nível " + numeroNivel, 1.6f, Color.YELLOW, Main.ALTURA_UI - 45f);
        timeLabel = game.criarLabel("", 1.6f, Color.WHITE, Main.LARGURA_UI - 320f, Main.ALTURA_UI - 45f, 300f, Align.right);
        tituloLabel = game.criarLabelCentral("", 3f, Color.WHITE, 280f);
        detalheLabel = game.criarLabelCentral("", 1.5f, Color.LIGHT_GRAY, 210f);

        uiStage.addActor(scoreLabel);
        uiStage.addActor(nivelLabel);
        uiStage.addActor(timeLabel);
        uiStage.addActor(tituloLabel);
        uiStage.addActor(detalheLabel);

        cesta = new Cesta(game.cestaTexture, Main.LARGURA_MUNDO * nivel.fracaoCesta);
        frutas = new Array<Fruta>();

        estado = JOGANDO;
        pontos = 0;
        tempoRestante = TEMPO_LIMITE;
        spawnTimer = 0f;

        novaFruta();
    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.irParaMenu();
            return;
        }
    }

    private void logic() {
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 0.05f);

        game.joystick.update(delta);
        cesta.mover(game.calibracao.getPosicao(game.joystick.getEixo()));
        cesta.update(delta);

        spawnTimer += delta;
        if (spawnTimer >= nivel.intervaloSpawn) {
            spawnTimer = 0f;
            if (frutas.size < nivel.maxNaTela) {
                novaFruta();
            }
        }

        updateFrutas(delta);
        atualizarUI();
    }

    private void updateFrutas(float delta) {
        for (int i = frutas.size - 1; i >= 0; i--) {
            Fruta fruta = frutas.get(i);
            fruta.update(delta);

            if (fruta.getBounds().overlaps(cesta.getAreaColeta())) {
                pontos = Math.max(0, pontos + fruta.getPontos());
                if (fruta instanceof Coco) {
                    game.erroSound.play();
                } else {
                    game.acertoSound.play();
                }
                frutas.removeIndex(i);
                novaFruta();
            } else if (fruta.saiuDaTela()) {
                if (!(fruta instanceof Coco)) {
                    game.perdaSound.play();
                }
                frutas.removeIndex(i);
                novaFruta();
            }
        }
    }

    private void atualizarUI() {
        scoreLabel.setText("Pontos: " + pontos + " / " + nivel.metaPontos);
    }
    
    private void novaFruta() {
        float x = MathUtils.random(0f, Main.LARGURA_MUNDO - TAMANHO_FRUTA);
        float y = Main.ALTURA_MUNDO;
        float velocidadeX = 0f;

        if (MathUtils.random() < nivel.chanceCoco) {
            frutas.add(new Coco(game.cocoTexture, x, y, TAMANHO_FRUTA, nivel.velocidadeQueda, velocidadeX));
        } else {
            int tipo = MathUtils.random(0, game.frutaTextures.length - 1);
            frutas.add(new Fruta(game.frutaTextures[tipo], x, y, TAMANHO_FRUTA, nivel.velocidadeQueda, velocidadeX));
        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        game.batch.setProjectionMatrix(viewport.getCamera().combined);

        game.batch.begin();
        game.batch.draw(game.backgroundTexture, 0f, 0f, Main.LARGURA_MUNDO, Main.ALTURA_MUNDO);

        for (Fruta fruta : frutas) {
            fruta.draw(game.batch);
        }

        cesta.draw(game.batch);
        game.batch.end();

        uiStage.getViewport().apply();
        uiStage.act(Gdx.graphics.getDeltaTime());
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        uiStage.dispose();
    }
}