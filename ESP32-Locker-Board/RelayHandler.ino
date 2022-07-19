void Relay_Init() {
  pinMode (relay[0], OUTPUT);
  pinMode (relay[1], OUTPUT);
  pinMode (relay[2], OUTPUT);
  pinMode (relay[3], OUTPUT);


  digitalWrite (relay[0], HIGH);
  digitalWrite (relay[1], HIGH);
  digitalWrite (relay[2], HIGH);
  digitalWrite (relay[3], HIGH);
}
