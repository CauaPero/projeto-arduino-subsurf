package br.mackenzie.controle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

// por enquanto simula o joystick com o teclado
// segurando a seta o valor vai de 0 ate 1 aos poucos. soltando antes fica menor
public class Joystick {

    private float eixo = 0f;

    public void update(float dt) {
        boolean esquerda = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
        boolean direita = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);

        if (esquerda && !direita) {
            eixo -= 1.2f * dt;
        } else if (direita && !esquerda) {
            eixo += 1.2f * dt;
        } else if (eixo > 0f) {
            eixo = Math.max(0f, eixo - 3f * dt);
        } else {
            eixo = Math.min(0f, eixo + 3f * dt);
        }

        if (eixo < -1f) eixo = -1f;
        if (eixo > 1f) eixo = 1f;
    }

    public float getEixo() {
        return eixo;
    }
}