package br.mackenzie.telas;

import br.mackenzie.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class CalibracaoScreen extends ScreenAdapter {

    private static final int ESQUERDA_AGUARDA = 0;
    private static final int ESQUERDA_MEDINDO = 1;
    private static final int DIREITA_AGUARDA = 2;
    private static final int DIREITA_MEDINDO = 3;
    private static final int CONCLUIDO = 4;

    // barra que mostra a posicao do joystick
    private static final float BARRA_CENTRO = Main.LARGURA_UI / 2f;
    private static final float BARRA_METADE = 300f;
    private static final float BARRA_Y = 150f;
    private static final float BARRA_ALTURA = 30f;

    private Main game;
    private Stage uiStage;

    private Label passoLabel;
    private Label instrucaoLabel;
    private Label tempoLabel;
    private Label picoLabel;
    private Label avisoLabel;

    private int fase;
    private float tempoRestante;
    private float pico;

    public CalibracaoScreen(Main game) {
        this.game = game;
        uiStage = new Stage(new FitViewport(Main.LARGURA_UI, Main.ALTURA_UI), game.batch);

        passoLabel = game.criarLabelCentral("", 2.2f, Color.YELLOW, 420f);
        instrucaoLabel = game.criarLabelCentral("", 1.6f, Color.WHITE, 340f);
        tempoLabel = game.criarLabelCentral("", 1.6f, Color.WHITE, 270f);
        picoLabel = game.criarLabelCentral("", 1.4f, Color.LIGHT_GRAY, 100f);
        avisoLabel = game.criarLabelCentral("", 1.3f, Color.SCARLET, 50f);

        uiStage.addActor(passoLabel);
        uiStage.addActor(instrucaoLabel);
        uiStage.addActor(tempoLabel);
        uiStage.addActor(picoLabel);
        uiStage.addActor(avisoLabel);

        mudarFase(ESQUERDA_AGUARDA);
    }

    @Override
    public void render(float delta) {
        input();
        logic(delta);
        draw(delta);
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.irParaMenu();
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (fase == ESQUERDA_AGUARDA) {
                mudarFase(ESQUERDA_MEDINDO);
            } else if (fase == DIREITA_AGUARDA) {
                mudarFase(DIREITA_MEDINDO);
            } else if (fase == CONCLUIDO) {
                game.irParaMenu();
            }
        }
    }

    private void logic(float delta) {
        float dt = Math.min(delta, 0.05f);
        game.joystick.update(dt);
        float eixo = game.joystick.getEixo();

        if (fase == ESQUERDA_MEDINDO || fase == DIREITA_MEDINDO) {
            tempoRestante -= dt;

            // na esquerda o valor e negativo, entao viramos o sinal
            float valor = eixo;
            if (fase == ESQUERDA_MEDINDO) {
                valor = -eixo;
            }
            if (valor > pico) {
                pico = valor;
            }

            tempoLabel.setText("Medindo... " + (int) Math.ceil(tempoRestante) + " s");
            picoLabel.setText("Pico atingido: " + Math.round(pico * 100f) + "%");

            if (tempoRestante <= 0f) {
                terminarMedicao();
            }
        } else {
            picoLabel.setText("");
        }
    }

    private void terminarMedicao() {
        if (pico < 0.1f) {
            avisoLabel.setText("Nenhum movimento detectado. Tente novamente.");
            if (fase == ESQUERDA_MEDINDO) {
                mudarFase(ESQUERDA_AGUARDA);
            } else {
                mudarFase(DIREITA_AGUARDA);
            }
            return;
        }

        if (fase == ESQUERDA_MEDINDO) {
            game.calibracao.setLimiteEsquerdo(pico);
            mudarFase(DIREITA_AGUARDA);
        } else {
            game.calibracao.setLimiteDireito(pico);
            mudarFase(CONCLUIDO);
        }
    }

    private void mudarFase(int novaFase) {
        fase = novaFase;
        pico = 0f;

        if (fase == ESQUERDA_AGUARDA) {
            passoLabel.setText("Calibração - Passo 1 de 2");
            instrucaoLabel.setText("Segure o joystick para a ESQUERDA o máximo que conseguir");
            tempoLabel.setText("Pressione ENTER para iniciar a medição");
        } else if (fase == DIREITA_AGUARDA) {
            passoLabel.setText("Calibração - Passo 2 de 2");
            instrucaoLabel.setText("Segure o joystick para a DIREITA o máximo que conseguir");
            tempoLabel.setText("Pressione ENTER para iniciar a medição");
        } else if (fase == CONCLUIDO) {
            passoLabel.setText("Calibração concluída!");
            instrucaoLabel.setText("A cesta só se move a partir de 50% da sua amplitude");
            tempoLabel.setText("Pressione ENTER para ir ao menu");
            avisoLabel.setText("");
        } else {
            tempoRestante = 5f;
            avisoLabel.setText("");
        }
    }

    private void draw(float delta) {
        ScreenUtils.clear(Color.BLACK);

        uiStage.getViewport().apply();
        game.batch.setProjectionMatrix(uiStage.getCamera().combined);
        game.batch.begin();
        game.batch.setColor(0.35f, 0.35f, 0.35f, 1f);
        game.batch.draw(game.backgroundTexture, 0f, 0f, Main.LARGURA_UI, Main.ALTURA_UI);
        game.batch.setColor(Color.WHITE);
        game.batch.end();

        drawBarra();

        uiStage.act(delta);
        uiStage.draw();
    }

    private void drawBarra() {
        float eixo = game.joystick.getEixo();
        float tamanho = MathUtils.clamp(Math.abs(eixo), 0f, 1f) * BARRA_METADE;

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        game.shapeRenderer.setProjectionMatrix(uiStage.getCamera().combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // fundo da barra
        game.shapeRenderer.setColor(0f, 0f, 0f, 0.7f);
        game.shapeRenderer.rect(BARRA_CENTRO - BARRA_METADE, BARRA_Y, BARRA_METADE * 2f, BARRA_ALTURA);

        // posicao atual do joystick
        game.shapeRenderer.setColor(0.3f, 0.8f, 0.4f, 1f);
        if (eixo < 0f) {
            game.shapeRenderer.rect(BARRA_CENTRO - tamanho, BARRA_Y, tamanho, BARRA_ALTURA);
        } else {
            game.shapeRenderer.rect(BARRA_CENTRO, BARRA_Y, tamanho, BARRA_ALTURA);
        }

        // marcador do pico (so durante a medicao)
        if (fase == ESQUERDA_MEDINDO || fase == DIREITA_MEDINDO) {
            float lado = 1f;
            if (fase == ESQUERDA_MEDINDO) {
                lado = -1f;
            }
            float marcadorX = BARRA_CENTRO + lado * MathUtils.clamp(pico, 0f, 1f) * BARRA_METADE;
            game.shapeRenderer.setColor(1f, 0.85f, 0.2f, 1f);
            game.shapeRenderer.rect(marcadorX - 2f, BARRA_Y - 8f, 4f, BARRA_ALTURA + 16f);
        }

        // linha do centro
        game.shapeRenderer.setColor(Color.WHITE);
        game.shapeRenderer.rect(BARRA_CENTRO - 1f, BARRA_Y - 8f, 2f, BARRA_ALTURA + 16f);

        game.shapeRenderer.end();
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
