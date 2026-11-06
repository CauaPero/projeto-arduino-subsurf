package br.mackenzie.objetos;

import br.mackenzie.Main;
import com.badlogic.gdx.graphics.Texture;

public class Fruta extends GameObject {

    protected float velocidadeQueda;
    protected float velocidadeX;

    public Fruta(Texture texture, float x, float y, float tamanho, float velocidadeQueda, float velocidadeX) {
        super(texture, x, y, tamanho, tamanho);
        this.velocidadeQueda = velocidadeQueda;
        this.velocidadeX = velocidadeX;
    }

    public int getPontos() {
        return 10;
    }

    @Override
    public void update(float dt) {
        sprite.translateY(-velocidadeQueda * dt);
        sprite.translateX(velocidadeX * dt);

        if (sprite.getX() < 0f) {
            sprite.setX(0f);
            velocidadeX = -velocidadeX;
        } else if (sprite.getX() > Main.LARGURA_MUNDO - sprite.getWidth()) {
            sprite.setX(Main.LARGURA_MUNDO - sprite.getWidth());
            velocidadeX = -velocidadeX;
        }
    }

    public boolean saiuDaTela() {
        return sprite.getY() + sprite.getHeight() < 0f;
    }
    
}