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
    
}