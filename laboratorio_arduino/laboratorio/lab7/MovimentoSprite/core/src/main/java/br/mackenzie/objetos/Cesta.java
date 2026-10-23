package br.mackenzie.objetos;

import br.mackenzie.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Cesta extends GameObject {

    private float alvoX;
    private Rectangle areaColeta = new Rectangle();

    public Cesta(Texture texture, float largura) {
        super(texture, (Main.LARGURA_MUNDO - largura) / 2f, 0.1f, largura, largura / 2f);
        alvoX = sprite.getX();
    }

    public void mover(float posicao) {
        float xMax = Main.LARGURA_MUNDO - sprite.getWidth();
        alvoX = (posicao + 1f) / 2f * xMax;
    }

    @Override
    public void update(float dt) {
        float xMax = Main.LARGURA_MUNDO - sprite.getWidth();
        float x = sprite.getX() + (alvoX - sprite.getX()) * Math.min(1f, 12f * dt);
        sprite.setX(MathUtils.clamp(x, 0f, xMax));
    }

    public Rectangle getAreaColeta() {
        areaColeta.set(sprite.getX(), sprite.getY() + sprite.getHeight() / 2f,
            sprite.getWidth(), sprite.getHeight() / 2f);
        return areaColeta;
    }
}