void kirimKeServer_LockerState() {
  String httpRequestData = String(DOMAIN)
                           + String("/api/board/LockerState.php")
                           + String("?lockerState1=") + readProx[0]
                           + String("&lockerState2=") + readProx[1]
                           + String("&lockerState3=") + readProx[2]
                           + String("&lockerState4=") + readProx[3];
  if (statusWifi == 1) {
    HTTPClient http;

    //GET methode
    http.begin(httpRequestData);
    int httpResponseCode = http.GET();
    String payload = http.getString();

    if (httpResponseCode == 200) {
    
      //      kirimKeServer_LockerState();
    }
    http.end();
  }
  else {
    WIFI_INIT() ;
  }
}

void kirimKeServer_TickOTP() {
  String httpRequestData = String(DOMAIN)
                           + String("/api/board/TickOTP.php");

  if (statusWifi == 1) {
    HTTPClient http;

    //GET methode
    http.begin(httpRequestData);
    int httpResponseCode = http.GET();
    String payload = http.getString();

    Serial.println(httpResponseCode);
    //    if (httpResponseCode) {
    //      Serial.println(payload);
    //      kirimKeServer_LockerState();
    //    }
    http.end();
  }
  else {
    WIFI_INIT() ;
  }
}

void kirimKeServer_LockerControl(int num, int ctrl) {
  String httpRequestData = String(DOMAIN)
                           + String("/api/board/LockerControl.php")
                           + String("?lockerControl=") + String(ctrl)
                           + String("&num=") + String(num)
                           ;
  if (statusWifi == 1) {
    HTTPClient http;

    //GET methode
    http.begin(httpRequestData);
    int httpResponseCode = http.GET();
    String payload = http.getString();

    if (httpResponseCode == 200) {
    
      //      kirimKeServer_LockerState();
    }
    http.end();
  }
  else {
    WIFI_INIT() ;
  }
}
