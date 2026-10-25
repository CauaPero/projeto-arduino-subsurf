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

    // recebe o valor do joystick (-1 a +1) e devolve a posicao da cesta (-1 a +1)
    // ate 50% do limite: 0 (centro); de 50% a 100%: vai do centro ate a borda
    public float getPosicao(float eixo) {
        float limite = limiteDireito;
        if (eixo < 0f) {
            limite = limiteEsquerdo;
        }

        float proporcao = Math.abs(eixo) / limite;
        if (proporcao < LIMIAR) {
            return 0f;
        }

        float posicao = MathUtils.clamp((proporcao - LIMIAR) / (1f - LIMIAR), 0f, 1f);
        if (eixo < 0f) {
            return -posicao;
        }
        return posicao;
    }
}
