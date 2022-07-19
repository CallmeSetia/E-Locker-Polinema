void Locker_Handler() {

  Serial.println("relay : " + String(stateLocker[0]));
  if (stateLocker[0] == 1) {
    digitalWrite(relay[0], LOW);
    delay(1500);
    digitalWrite(relay[0], HIGH);

    kirimKeServer_LockerControl(1, 0);
  }
  if (stateLocker[1] == 1) {
    digitalWrite(relay[1], LOW);
    delay(1500);
    digitalWrite(relay[1], HIGH);
    
    kirimKeServer_LockerControl(2, 0);
  }
  if (stateLocker[2] == 1) {
    digitalWrite(relay[2], LOW);
    delay(1500);
    digitalWrite(relay[2], HIGH);
    
    kirimKeServer_LockerControl(3, 0);
  }
  if (stateLocker[3] == 1) {
    digitalWrite(relay[3], LOW);
    delay(1500);
    digitalWrite(relay[3], HIGH);
    
    kirimKeServer_LockerControl(4, 0);
  }

  if (stateLocker[0] == 0) {
    digitalWrite(relay[0], HIGH);
  }
  if (stateLocker[1] == 0) {
    digitalWrite(relay[1], HIGH);
  }
  if (stateLocker[2] == 0) {
    digitalWrite(relay[2], HIGH);
  }
  if (stateLocker[3] == 0) {
    digitalWrite(relay[3], HIGH);
  }
}
