package br.mackenzie.config;

public class Nivel {

    public static final Nivel[] NIVEIS = {
        new Nivel(1, 1f / 3f, 3.0f, 2, 0.00f, 1.2f, 0.0f, 0.0f, 150),
        new Nivel(2, 1f / 3f, 2.5f, 2, 0.15f, 1.5f, 0.0f, 0.0f, 200),
        new Nivel(3, 1f / 3f, 2.2f, 3, 0.18f, 1.8f, 0.0f, 0.0f, 250),
        new Nivel(4, 1f / 4f, 2.0f, 3, 0.21f, 2.0f, 1.0f, 0.3f, 300),
        new Nivel(5, 1f / 6f, 1.8f, 3, 0.24f, 2.4f, 1.6f, 0.3f, 350)
    };

    public final int numero;
    public final float fracaoCesta;
    public final float intervaloSpawn;
    public final int maxNaTela;
    public final float chanceCoco;
    public final float velocidadeQueda;
    public final float velocidadeLateral;
    public final float chanceQuedaReta;
    public final int metaPontos;

    public Nivel(int numero, float fracaoCesta, float intervaloSpawn, int maxNaTela, float chanceCoco,
                 float velocidadeQueda, float velocidadeLateral, float chanceQuedaReta, int metaPontos) {
        this.numero = numero;
        this.fracaoCesta = fracaoCesta;
        this.intervaloSpawn = intervaloSpawn;
        this.maxNaTela = maxNaTela;
        this.chanceCoco = chanceCoco;
        this.velocidadeQueda = velocidadeQueda;
        this.velocidadeLateral = velocidadeLateral;
        this.chanceQuedaReta = chanceQuedaReta;
        this.metaPontos = metaPontos;
    }
}