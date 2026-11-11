// joystick.ino (Arduino)
// Eixo X do joystick ligado ao pino A0.

void setup() {
  Serial.begin(9600);          // mesma velocidade do Java (setBaudRate(9600))
}

void loop() {
  int valor = analogRead(A0);  // 0 a 1023 (esquerda ~0, centro ~515, direita ~1023)
  Serial.println(valor);       // manda o numero + quebra de linha (o '\n' que a thread espera)
  delay(20);                   // uma leitura a cada 20 ms (~50 por segundo)
}
