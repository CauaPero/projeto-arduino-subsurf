package br.mackenzie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends Game {

    public static final float LARGURA_MUNDO = 8f;
    public static final float ALTURA_MUNDO = 5f;
    public static final float LARGURA_UI = 800f;
    public static final float ALTURA_UI = 500f;

    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;

    public Texture backgroundTexture;
    public Texture cestaTexture;
    public Texture cocoTexture;
    public Texture[] frutaTextures;

    public Sound acertoSound;
    public Sound perdaSound;
    public Sound erroSound;

    public Joystick joystick;
    public Calibracao calibracao;

    private Screen telaAntiga;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        backgroundTexture = new Texture("floresta.png");
        cestaTexture = new Texture("cesta.png");
        cocoTexture = new Texture("coco.png");
        frutaTextures = new Texture[] {
            new Texture("banana.png"),
            new Texture("maca.png"),
            new Texture("laranja.png"),
            new Texture("morango.png")
        };

        acertoSound = Gdx.audio.newSound(Gdx.files.internal("acerto.mp3"));
        perdaSound = Gdx.audio.newSound(Gdx.files.internal("fruta_perdida.mp3"));
        erroSound = Gdx.audio.newSound(Gdx.files.internal("erro.mp3"));

        joystick = new Joystick();
        calibracao = new Calibracao();
    }

    public void iniciarJogo(int nivel) {
        trocarTela(new GameScreen(this, nivel));
    }

    private void trocarTela(Screen nova) {
        telaAntiga = getScreen();
        setScreen(nova);
    }

    @Override
    public void render() {
        super.render();
        // a tela antiga so e descartada depois de terminar o render dela
        if (telaAntiga != null) {
            telaAntiga.dispose();
            telaAntiga = null;
        }
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
        backgroundTexture.dispose();
        cestaTexture.dispose();
        cocoTexture.dispose();
        for (Texture t : frutaTextures) {
            t.dispose();
        }
        acertoSound.dispose();
        perdaSound.dispose();
        erroSound.dispose();
    }
}