package br.mackenzie.config;

public class Nivel {

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