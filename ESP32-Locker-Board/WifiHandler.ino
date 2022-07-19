
void WIFI_INIT() {
  WiFi.mode(WIFI_OFF); //Prevents reconnection issue (taking too long to connect)
  delay(1000);
  WiFi.mode(WIFI_STA);
  WiFi.begin(SSID_WIFI, PASS_WIFI);

  Serial.print("Connecting to ");
  Serial.println(SSID_WIFI);

  while (WiFi.status() != WL_CONNECTED) {
    digitalWrite(pinLedMerah, HIGH);
    Serial.print(".");
      digitalWrite(pinLedMerah, LOW);
  }

  Serial.println("\n Connected");
  Serial.print("IP address: "); Serial.println(WiFi.localIP()); //IP address assigned to your ESP

  statusWifi = 1;
  delay(100);
}

void koneksiWifiChecker() {
  if ((WiFi.status() != WL_CONNECTED)) {
    statusWifi = 0;
   WIFI_INIT();
  }
  else if (WiFi.isConnected()) statusWifi = 1;
}
