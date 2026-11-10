package br.mackenzie.controle;

import br.mackenzie.arduino.ArduinoReaderThread;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

// Le o joystick do Arduino pela porta serial e tambem aceita as setas do teclado.
// Se o teclado estiver sendo usado, ele tem prioridade; se nao tiver Arduino, so o teclado funciona.
public class Joystick {

    private ArduinoReaderThread arduino;
    private float eixo = 0f;

    public Joystick(String porta) {
        arduino = new ArduinoReaderThread(porta);
        arduino.setDaemon(true);
        arduino.start();
    }

    public void update(float dt) {
        boolean esquerda = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
        boolean direita = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);

        if (esquerda && !direita) {
            eixo -= 1.2f * dt;
        } else if (direita && !esquerda) {
            eixo += 1.2f * dt;
        } else if (!lerArduino()) {
            if (eixo > 0f) {
                eixo = Math.max(0f, eixo - 3f * dt);
            } else {
                eixo = Math.min(0f, eixo + 3f * dt);
            }
        }

        if (eixo < -1f) eixo = -1f;
        if (eixo > 1f) eixo = 1f;
    }

    // O Arduino manda um numero de 0 a 1023 (centro apx 512).
    // Devolve false se ainda nao chegou nenhum numero valido.
    private boolean lerArduino() {
        try {
            int valor = Integer.parseInt(arduino.getLastData());
            eixo = (valor - 512) / 512f;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // -1 = todo a esquerda, 0 = centro, +1 = todo a direita
    public float getEixo() {
        return eixo;
    }

    public void dispose() {
        arduino.stopReading();
    }
}