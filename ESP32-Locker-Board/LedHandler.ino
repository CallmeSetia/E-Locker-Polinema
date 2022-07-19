void Led_Init() {

  pinMode(pinLedMerah, OUTPUT);
  pinMode(pinLedHijau, OUTPUT);
  pinMode(pinBuzzer, OUTPUT);

}
void ledMerah (bool state, int interval) {
  digitalWrite(pinLedMerah, state);
  delay(interval);
}

void ledHijau (bool state, int interval) {
  digitalWrite(pinLedHijau, state);
  delay(interval);
}

void buzzer (bool state, int interval) {
  digitalWrite(pinBuzzer, state);
  delay(interval);
}
