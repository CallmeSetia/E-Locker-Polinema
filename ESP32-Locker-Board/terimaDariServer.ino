void terimaDariServer_CommandLocker() {
  String httpRequestData = String(DOMAIN)
                           + String("/api/board/CommandLocker.php");
  if (statusWifi == 1) {
    HTTPClient http;

    //GET methode
    http.begin(httpRequestData);
    int httpResponseCode = http.GET();
    String payload = http.getString();


    if (httpResponseCode == 200) {
      Serial.println(payload);
      String Ctrl_Locker1 = parseString(payload, "#", 0);
      String Ctrl_Locker2 = parseString(payload, "#", 1);
      String Ctrl_Locker3 = parseString(payload, "#", 2);
      String Ctrl_Locker4 = parseString(payload, "#", 3);

      stateLocker[0] = Ctrl_Locker1.toInt();
      stateLocker[1] = Ctrl_Locker2.toInt();
      stateLocker[2] = Ctrl_Locker3.toInt();
      stateLocker[3] = Ctrl_Locker4.toInt();
      Serial.println("oe" + String(stateLocker[0]));
    }
    http.end();
  }
  else {
    WIFI_INIT();
  }
}
