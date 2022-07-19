void proximity_init() {
  pinMode (prox[0], INPUT);
  pinMode (prox[1], INPUT);
  pinMode (prox[2], INPUT);
  pinMode (prox[3], INPUT);
}

void bacaProximity() {
  
  readProx[0] = !digitalRead(prox[0]);
  readProx[1] = !digitalRead(prox[1]);
  readProx[2] = !digitalRead(prox[2]);
  readProx[3] = !digitalRead(prox[3]);
  
  if (Read == true) {
    Serial.print("PROXIMITY SENSOR : ");
    for (int i = 0; i < 4; i++) {
      Serial.print(" ");
      Serial.print(readProx[i]);
      Serial.print(" ");
    }
    Serial.println();
  }
}
