package br.mackenzie.controle;

import com.badlogic.gdx.math.MathUtils;

public class Calibracao {

    //a cesta só sai do centro qnd o joystick passa de 50% do limite do paciente
    private static final float LIMIAR = 0.5f;

    private float limiteEsquerdo = 1f;
    private float limiteDireito = 1f;

    public void setLimiteEsquerdo(float pico) {
        limiteEsquerdo = Math.max(0.2f, Math.abs(pico));
    }

    public void setLimiteDireito(float pico) {
        limiteDireito = Math.max(0.2f, Math.abs(pico));
    }

    public float getLimiteEsquerdo() {
        return limiteEsquerdo;
    }

    public float getLimiteDireito() {
        return limiteDireito;
    }
}
