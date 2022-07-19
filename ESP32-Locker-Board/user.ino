
//#include <pt.h> //Protothread
//
//#include <LiquidCrystal_I2C.h>
//
//#include <SPI.h>
//
//#include <MFRC522.h>
//
//#include <WiFi.h>
//
//#include <HTTPClient.h>
//
//#include <time.h>
//
//#define SS_PIN 5
//#define RST_PIN 9
//
//MFRC522 mfrc522(SS_PIN, RST_PIN);
//
//// set the LCD number of columns and rows
//#define lcdColumns 16
//#define lcdRows 2
//
//#define LED_MERAH 33
//#define LED_HIJAU 32
//#define BUZZERS 25
//
//#define HIDUP 1
//#define MATI 0
//
//#define SSID_WIFI "Rid"
//#define PASS_WIFI "asdfghjkl"
//
//#define DOMAIN "http://192.168.43.6:8080"
//
//#define DELAY_AMBIL 5000
//
//struct Sekat {
//  short barang1 = 0;
//  short barang2 = 0;
//  short barang3 = 0;
//  short barang4 = 0;
//};
//
//struct pt wifi1, RFID, PROX, serial1, TIME, db1;
//
//LiquidCrystal_I2C lcd(0x27, 16, 2);
//
//short bacaRfid;
//String StrUID, Link;
//String URL;
//
//unsigned long interval = 1000; // the time we need to wait
//unsigned long previousMillis = 0; // millis() returns an unsigned long.
//unsigned long previousMillis1 = 0; // millis() returns an unsigned long.
//unsigned long previousMillis2 = 0; // millis() returns an unsigned long.
//
//int pinProx[10];
//short bypassRFID;
//
//int timezone = 7 * 3600;
//int time_dst = 0;
//int timeResume = 1;
//
//unsigned short statusWifi = 0;
//
//#define WIFI_HIDUP 1
//#define WIFI_MATI 0
//
//static unsigned int ledKondisi(int _pin, int _kondisi);
//static unsigned int blinkyLED1(struct pt * pt, int _pin, int _interval);
//static unsigned int blinkyLED2(struct pt * pt, int _pin, int _interval);
//static int saySomething(struct pt * pt, char _word[], int _interval);
//static void array_to_string(byte array[], unsigned int len, char buffer[]);
//static void connectToWiFi();
//static unsigned int scanRFID(struct pt * pt, int _interval);
//static unsigned int cekKoneksiWifi(struct pt * pt, int _interval);
//static unsigned int tampilWaktu(struct pt * pt, int _interval);
//static unsigned int scanProximity(struct pt * pt, int _pin[], struct Sekat * dataSekat, String _link, int _interval);
//static void cekProximty(struct Sekat * dataSekat);
//
//
//
//// Callback from db
//uint8_t callbackBoardMode = 0;
//
//
////Solenoid
//#define SOLENOID_BUKA 0
//#define SOLENOID_TUTUP 1
//#define PIN_SOLENOID 26
//
//short statusSolenoid = SOLENOID_TUTUP;
//
//void setup() {
//  Serial.begin(9600);
//
//  pinMode(LED_MERAH, OUTPUT);
//  pinMode(LED_HIJAU, OUTPUT);
//  pinMode(BUZZERS, OUTPUT);
//  pinMode(PIN_SOLENOID , OUTPUT);
//
//  digitalWrite(PIN_SOLENOID, HIGH);
//  Serial.println("System Init");
//  Serial.println("...");
//
//  ledKondisi(BUZZERS, HIDUP);
//  delay(500);
//  ledKondisi(BUZZERS, LOW);
//
//  SPI.begin();
//  mfrc522.PCD_Init();
//
//  lcd.begin(); // initialize LCD
//  lcd.backlight(); // turn on LCD backlight
//
//  lcd.setCursor(0, 0);
//  lcd.print("System Init..");
//  lcd.setCursor(0, 1);
//  lcd.print("Starting..");
//
//  PT_INIT( & wifi1);
//  PT_INIT( & RFID);
//  PT_INIT( & PROX);
//  //  PT_INIT( & serial1);
//  PT_INIT( & TIME);
//  PT_INIT( & db1);
//
//  pinProx[0] = 35;
//  pinProx[1] = 34;
//  pinProx[2] = 39;
//  pinProx[3] = 36;
//
//  for (int i = 0; i < 4; i++) {
//    pinMode(pinProx[i], INPUT);
//  }
//  connectToWiFi();
//
//  Serial.println("1");
//  configTime(timezone, time_dst, "pool.ntp.org", "time.nist.gov");
//  Serial.println("2");
//  lcd.clear();
//}
//
//void loop() {
//  // static unsigned long currentMillis = millis(); // grab current time
//  struct Sekat dataSekat;
//
//  scanRFID( & RFID, &dataSekat, String(DOMAIN) + String("/tugas10/board/user/cekUser.php"), 200);
//  scanProximity( &PROX, pinProx, &dataSekat, String(DOMAIN) + "/tugas10/board/user/scanKotak.php", 2000);
//  getBoardMode(&db1,  String(DOMAIN) + "/tugas10/board/user/cekModeBoard.php", 2000);
//
//  //  db1
//  tampilWaktu( & TIME, 1000);
//  cekKoneksiWifi( &wifi1, 1000);
//  if (millis() - previousMillis2 >= 1000) {
//    previousMillis2 = millis();
//    if (callbackBoardMode == 1) digitalWrite(PIN_SOLENOID, SOLENOID_TUTUP); // Langsung Tutup Solenoid
//    else if (callbackBoardMode == 2) digitalWrite(PIN_SOLENOID, SOLENOID_BUKA); // Lansung Buka Solenoid
//  }
//
//  delay(60);
//}
//
////----------------- Fungsi ---------------------//
//void cekProximty(struct Sekat * dataSekat) {
//  if (dataSekat -> barang1 == 0) {
//    Serial.println("PROXIMITY 1 : OK");
//  } else {
//    Serial.println("PROXIMITY 1 : NULL");
//  }
//
//  if (dataSekat -> barang2 == 0) {
//    Serial.println("PROXIMITY 2 : OK");
//  } else {
//    Serial.println("PROXIMITY 2 : NULL");
//  }
//
//  if (dataSekat -> barang3 == 0) {
//    Serial.println("PROXIMITY 3 : OK");
//  } else {
//    Serial.println("PROXIMITY 3 : NULL");
//  }
//
//  if (dataSekat -> barang4 == 0) {
//    Serial.println("PROXIMITY 4 : OK");
//  } else {
//    Serial.println("PROXIMITY 4 : NULL");
//  }
//}
//
//
//
//static void array_to_string(byte array[], unsigned int len, char buffer[]) {
//  for (unsigned int i = 0; i < len; i++) {
//    byte nib1 = (array[i] >> 4) & 0x0F;
//    byte nib2 = (array[i] >> 0) & 0x0F;
//    buffer[i * 2 + 0] = nib1 < 0xA ? '0' + nib1 : 'A' + nib1 - 0xA;
//    buffer[i * 2 + 1] = nib2 < 0xA ? '0' + nib2 : 'A' + nib2 - 0xA;
//  }
//  buffer[len * 2] = '\0';
//}
//
//static void connectToWiFi() {
//  WiFi.mode(WIFI_OFF); //Prevents reconnection issue (taking too long to connect)
//  delay(1000);
//  WiFi.mode(WIFI_STA);
//
//  lcd.clear();
//  lcd.setCursor(0, 0);
//  lcd.print("Connecting to :");
//  lcd.setCursor(0, 1);
//  lcd.print(SSID_WIFI);
//
//  Serial.print("Connecting to ");
//  Serial.println(SSID_WIFI);
//  WiFi.begin(SSID_WIFI, PASS_WIFI);
//
//  unsigned int counter = 0;
//  while (WiFi.status() != WL_CONNECTED) {
//    ledKondisi(LED_MERAH, HIDUP);
//    delay(500);
//    Serial.print(".");
//    ledKondisi(LED_MERAH, MATI);
//  }
//
//  ledKondisi(LED_HIJAU, HIDUP);
//  ledKondisi(BUZZERS, HIDUP);
//  delay(500);
//  ledKondisi(BUZZERS, LOW);
//  ledKondisi(LED_HIJAU, LOW);
//
//  lcd.clear();
//  lcd.setCursor(0, 0);
//  lcd.print("Connected-IP:");
//  lcd.setCursor(0, 1);
//  lcd.print(WiFi.localIP());
//
//  Serial.println("");
//  Serial.println("Connected");
//
//  Serial.print("IP address: ");
//  Serial.println(WiFi.localIP()); //IP address assigned to your ESP
//
//  // LCD
//
//  statusWifi = 1;
//  delay(100);
//}
//
//// ----------------------- Protothread --------------------//
//static unsigned int cekKoneksiWifi(struct pt * pt, int _interval) {
//  static unsigned lastTime = 0;
//
//  PT_BEGIN(pt);
//
//  while (1) {
//
//    PT_WAIT_UNTIL(pt, millis() - lastTime > _interval);
//    lastTime = millis();
//    if ((WiFi.status() != WL_CONNECTED)) {
//      statusWifi = 0;
//
//    } else {
//      if (WiFi.isConnected())
//        statusWifi = 1;
//    }
//
//  }
//
//  PT_END(pt);
//}
//
//static unsigned int getBoardMode (struct pt * pt, String LinkModeBoard, int _interval) {
//  // Callback
//  //  callbackBoardMode = 0;
//
//  static unsigned lastTime = 0;
//  PT_BEGIN(pt);
//
//  while (1) {
//    PT_WAIT_UNTIL(pt, millis() - lastTime > _interval);
//    lastTime = millis();
//
//    String httpRequestData = LinkModeBoard;
//    if ((WiFi.status() != WL_CONNECTED)) {
//      statusWifi = 0;
//
//    } else {
//      if (WiFi.isConnected())
//        statusWifi = 1;
//    }
//    if (statusWifi == 1) {
//      HTTPClient http;
//
//      //GET methode
//      http.begin(httpRequestData); //initiate HTTP request   //Specify content-type header
//
//      int httpResponseCode = http.GET(); //Send the request
//      String payload = http.getString(); //Get the response payload
//
//      //      Serial.print("Mode Board: ");
//      Serial.println(httpResponseCode);
//      if (httpResponseCode > 0) {
//        //        Serial.print("Payload : ");
//        //        Serial.println(payload);
//        String payload_temp = payload.substring(0);
//        callbackBoardMode = payload_temp.toInt();
//
//      }
//      http.end();
//    } else {
//      delay(2000);
//      connectToWiFi();
//    }
//
//
//  }
//
//  PT_END(pt);
//
//}
//static void resetModeBoard() {
//  String httpRequestData = String(DOMAIN) + "/tugas10/board/user/resetBoardUserMode.php";
//  if ((WiFi.status() != WL_CONNECTED)) {
//    statusWifi = 0;
//
//  } else {
//    if (WiFi.isConnected())
//      statusWifi = 1;
//  }
//  if (statusWifi == 1) {
//    HTTPClient http;
//
//    //GET methode
//    http.begin(httpRequestData); //initiate HTTP request   //Specify content-type header
//
//    int httpResponseCode = http.GET(); //Send the request
//    String payload = http.getString(); //Get the response payload
//
//    //      Serial.print("Mode Board: ");
//    Serial.println(httpResponseCode);
//    if (httpResponseCode > 0) {
//      // if oke user valid { 1- valid, 0-kartu salah, 2-sudah ambil}
//
//      String payload_temp = payload.substring(0);
//      statusSolenoid = payload_temp.toInt();
//    }
//  }
//}
//static void solenoidLock (int kondisi) {
//  String httpRequestData = "";
//
//  if (kondisi == SOLENOID_TUTUP) {
//    httpRequestData = String(DOMAIN) + "/tugas10/board/user/solenoidController.php?solenoidState=" + String(0) + "";
//    digitalWrite(PIN_SOLENOID, SOLENOID_TUTUP);
//        
//  }
//  else if (kondisi == SOLENOID_BUKA) {
//    httpRequestData = String(DOMAIN) + "/tugas10/board/user/solenoidController.php?solenoidState=" + String(1) + "";
//    digitalWrite(PIN_SOLENOID, SOLENOID_BUKA);
//  }
//
//  if ((WiFi.status() != WL_CONNECTED)) {
//    statusWifi = 0;
//
//  } else {
//    if (WiFi.isConnected())
//      statusWifi = 1;
//  }
//  if (statusWifi == 1) {
//    HTTPClient http;
//
//    //GET methode
//    http.begin(httpRequestData); //initiate HTTP request   //Specify content-type header
//
//    int httpResponseCode = http.GET(); //Send the request
//    String payload = http.getString(); //Get the response payload
//
//    //      Serial.print("Mode Board: ");
//    Serial.println(httpResponseCode);
//    if (httpResponseCode > 0) {
//      // if oke user valid { 1- valid, 0-kartu salah, 2-sudah ambil}
//
//      String payload_temp = payload.substring(0);
//      statusSolenoid = payload_temp.toInt();
//    }
//  }
//}
//
//static void userModeInit(String dataRFID,  struct Sekat ** dataSekat, String _link) {
//  //  struct Sekat ** dataSekat_temp = dataSekat;
//  int statusUser = 0;
//
//  lcd.clear();
//  lcd.setCursor(0, 0);
//  lcd.print("Cek Status User..");
//
//  // Send data rfid http get
//  String httpRequestData = _link + "?rfidTagUser=" + String(dataRFID) + "";
//
//  if ((WiFi.status() != WL_CONNECTED)) {
//    statusWifi = 0;
//
//  } else {
//    if (WiFi.isConnected())
//      statusWifi = 1;
//  }
//  if (statusWifi == 1) {
//    HTTPClient http;
//
//    //GET methode
//    http.begin(httpRequestData); //initiate HTTP request   //Specify content-type header
//
//    int httpResponseCode = http.GET(); //Send the request
//    String payload = http.getString(); //Get the response payload
//
//    //      Serial.print("Mode Board: ");
//    Serial.println(httpResponseCode);
//    if (httpResponseCode > 0) {
//      // if oke user valid { 1- valid, 0-kartu salah, 2-sudah ambil}
//
//      String payload_temp = payload.substring(0);
//      statusUser = payload_temp.toInt();
//
//      if (statusUser == 1) { // User valid
//        // pass
//        //         Buka Solenoid
//
//
//        struct Sekat dataKotak, dataKotak_temp;
//        int counter = 0;
//
//ambilKotak:
//        solenoidLock(SOLENOID_BUKA);
//        
//        
//        dataKotak.barang1 = digitalRead(35);
//        dataKotak.barang2 = digitalRead(34);
//        dataKotak.barang3 = digitalRead(39);
//        dataKotak.barang4 = digitalRead(36);
//
//        dataKotak_temp = dataKotak;
//
//        Serial.println("TEMP");
//        Serial.println(dataKotak_temp.barang1);
//
//        delay(DELAY_AMBIL);
//        Serial.println("REAL");
//        dataKotak.barang1 = digitalRead(35);
//        dataKotak.barang2 = digitalRead(34);
//        dataKotak.barang3 = digitalRead(39);
//        dataKotak.barang4 = digitalRead(36);
//
//        Serial.println(dataKotak.barang1);
//
//        short flag1, flag2, flag3, flag4;
//
//        if (dataKotak_temp.barang1 != dataKotak.barang1) flag1 = 0;
//        else flag1 = 1;
//
//        if (dataKotak_temp.barang2 != dataKotak.barang2) flag2 = 0;
//        else flag2 = 1;
//
//        if (dataKotak_temp.barang3 != dataKotak.barang3) flag3 = 0;
//        else flag3 = 1;
//
//        if (dataKotak_temp.barang4 != dataKotak.barang4) flag4 = 0;
//        else flag4 = 1;
//
//        // delay 20s;
//        // jika delay selesai, cek data sekat temp apakah == sama dengan data sekat temp;
//
//        if (flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1) {
//          // Print Anda Belum Mengambil Kotak
//          counter++;
//          lcd.clear();
//          lcd.setCursor(0, 0);
//          lcd.print("Anda Belum");
//          lcd.setCursor(0, 1);
//          lcd.print("Ambil Kotak");
//
//          ledKondisi(LED_MERAH, HIDUP);
//          ledKondisi(BUZZERS, HIDUP);
//          delay(500);
//          ledKondisi(BUZZERS, LOW);
//          ledKondisi(LED_MERAH, LOW);
//          delay(2000);
//
//          // Goto Dellay
//
//          if (counter < 3) goto ambilKotak;
//          else {
//            lcd.clear();
//            lcd.setCursor(0, 0);
//            lcd.print("Waktu Sdh Habis");
//            lcd.setCursor(0, 1);
//            lcd.print("Silahkan Coba Lagi");
//
//            resetModeBoard();
//            ledKondisi(LED_MERAH, HIDUP);
//            ledKondisi(BUZZERS, HIDUP);
//            delay(500);
//            ledKondisi(BUZZERS, LOW);
//            ledKondisi(LED_MERAH, LOW);
//            delay(2000);
//            solenoidLock(SOLENOID_TUTUP);
//          }
//
//        }
//
//        else {
//          // Solenoid Tutup
//          delay(5000);
//          solenoidLock(SOLENOID_TUTUP);
//          // Update Datauser => LastDay Ambil hari ini - last login hari ini
//          String httpRequestData = String(DOMAIN) + "/tugas10/board/user/userSudahAmbil.php?dataRFID=" + dataRFID + "";
//
//          if ((WiFi.status() != WL_CONNECTED)) {
//            statusWifi = 0;
//
//          } else {
//            if (WiFi.isConnected())
//              statusWifi = 1;
//          }
//          if (statusWifi == 1) {
//            HTTPClient http;
//
//            http.begin(httpRequestData);
//
//            int httpResponseCode = http.GET(); //Send the request
//            String payload = http.getString(); //Get the response payload
//
//            //      Serial.print("Mode Board: ");
//            Serial.println(httpResponseCode);
//            String payload_temp;
//            int StatusSudahAmbil;
//            if (httpResponseCode > 0) {
//              Serial.print("Payload USER'S: ");
//              Serial.println(payload);
//              payload_temp = payload.substring(0);
//              StatusSudahAmbil = payload_temp.toInt();
//
//              if (StatusSudahAmbil == 1) {
//                lcd.clear();
//                lcd.setCursor(0, 0);
//                lcd.print("Terimakasih..");
//
//                ledKondisi(LED_HIJAU, HIDUP);
//                ledKondisi(BUZZERS, HIDUP);
//                delay(500);
//                ledKondisi(BUZZERS, LOW);
//                ledKondisi(LED_HIJAU, LOW);
//                delay(2000);
//              }
//              else {
//                lcd.clear();
//                lcd.setCursor(0, 0);
//                lcd.print("Ada Error..");
//
//                ledKondisi(LED_MERAH, HIDUP);
//                ledKondisi(BUZZERS, HIDUP);
//                delay(500);
//                ledKondisi(BUZZERS, LOW);
//                ledKondisi(LED_MERAH, LOW);
//                delay(2000);
//              }
//            }
//            http.end();
//          } else {
//            delay(2000);
//            connectToWiFi();
//          }
//        }
//
//      }
//      else if (statusUser == 2) { // User sudah ambil data
//        // pass
//        Serial.println("User  Valid");
//        Serial.println("User Sudah Ambil");
//        Serial.println("Solenoid Tutup");
//        solenoidLock(SOLENOID_TUTUP);
//
//        lcd.clear();
//        lcd.setCursor(0, 0);
//        lcd.print("Anda Sudah Ambil");
//
//        delay(2000);
//
//        ledKondisi(LED_MERAH, HIDUP);
//        ledKondisi(BUZZERS, HIDUP);
//        delay(500);
//        ledKondisi(BUZZERS, LOW);
//        ledKondisi(LED_MERAH, LOW);
//
//      }
//      else if (statusUser == 0) { // Kartu Salah
//        // pass
//        Serial.println("User Tidak Valid");
//        Serial.println("Solenoid Tutup");
//        solenoidLock(SOLENOID_TUTUP);
//        lcd.clear();
//        lcd.setCursor(0, 0);
//        lcd.print("Tidak Valid..");
//        lcd.setCursor(0, 1);
//        lcd.print("Coba Lagi");
//        delay(2000);
//
//        ledKondisi(LED_MERAH, HIDUP);
//        ledKondisi(BUZZERS, HIDUP);
//        delay(500);
//        ledKondisi(BUZZERS, LOW);
//        ledKondisi(LED_MERAH, LOW);
//        delay(2000);
//      }
//
//
//    }
//
//    http.end();
//  } else {
//    delay(2000);
//    connectToWiFi();
//  }
//
//}
//
//static unsigned int scanProximity(struct pt * pt, int _pin[], struct Sekat * dataSekat, String _link, int _interval) {
//  static unsigned lastTime = 0;
//
//  PT_BEGIN(pt);
//  while (1) {
//    PT_WAIT_UNTIL(pt, millis() - lastTime > _interval);
//    lastTime = millis();
//
//    dataSekat -> barang1 = digitalRead(_pin[0]);
//    dataSekat -> barang2 = digitalRead(_pin[1]);
//    dataSekat -> barang3 = digitalRead(_pin[2]);
//    dataSekat -> barang4 = digitalRead(_pin[3]); // PRox 4
//
//    String httpRequestData = _link + "?dataKotak1=" + String(dataSekat -> barang1)
//                             + "&dataKotak2=" + String(dataSekat -> barang2)
//                             + "&dataKotak3=" + String(dataSekat -> barang3)
//                             + "&dataKotak4=" + String(dataSekat -> barang4)
//                             + "";
//
//    if ((WiFi.status() != WL_CONNECTED)) {
//      statusWifi = 0;
//
//    } else {
//      if (WiFi.isConnected())
//        statusWifi = 1;
//    }
//    if (statusWifi == 1) {
//      HTTPClient http;
//
//      //GET methode
//      http.begin(httpRequestData); //initiate HTTP request   //Specify content-type header
//
//      int httpResponseCode = http.GET(); //Send the request
//      String payload = http.getString(); //Get the response payload
//
//      //      Serial.print("Mode Board: ");
//      Serial.println(httpResponseCode);
//      if (httpResponseCode > 0) {
//        Serial.print("Payload : ");
//        Serial.println(payload);
//        //        cekMode = payload.substring(0);
//      }
//
//      http.end();
//    } else {
//      delay(2000);
//      connectToWiFi();
//    }
//
//
//  }
//
//  PT_END(pt);
//
//}
//static unsigned int tampilWaktu(struct pt * pt, int _interval) {
//  static unsigned lastTime = 0;
//  PT_BEGIN(pt);
//  while (1) {
//    PT_WAIT_UNTIL(pt, millis() - lastTime > _interval);
//    lastTime = millis();
//    if (timeResume == 1) {
//      lcd.clear();
//      lcd.setCursor(1, 0);
//      lcd.print("Serakarang Jam:");
//
//      previousMillis1 = millis();
//      time_t now = time(nullptr);
//      struct tm * p_tm = localtime( & now);
//      // Serial.println(p_tm);
//
//      if ((p_tm -> tm_hour) < 10) {
//        // Serial.print("0");
//        // Serial.print(p_tm->tm_hour);
//
//        lcd.setCursor(4, 1);
//        lcd.print("0");
//        lcd.setCursor(5, 1);
//        lcd.print(p_tm -> tm_hour);
//      } else {
//
//        lcd.setCursor(4, 1);
//        lcd.print(p_tm -> tm_hour);
//      }
//      lcd.setCursor(6, 1);
//      lcd.print(":");
//
//      if ((p_tm -> tm_min) < 10) {
//
//        lcd.setCursor(7, 1);
//        lcd.print("0");
//        lcd.setCursor(8, 1);
//        lcd.print(p_tm -> tm_hour);
//      } else {
//        lcd.setCursor(7, 1);
//        lcd.print(p_tm -> tm_min);
//      }
//      lcd.setCursor(9, 1);
//      lcd.print(":");
//
//      if ((p_tm -> tm_sec) < 10) {
//        lcd.setCursor(10, 1);
//        lcd.print("0");
//        lcd.setCursor(11, 1);
//        lcd.print(p_tm -> tm_sec);
//      } else {
//
//        lcd.setCursor(10, 1);
//        lcd.print(p_tm -> tm_sec);
//      }
//    }
//
//  }
//  PT_END(pt);
//}
//
//static unsigned int scanRFID(struct pt * pt, struct Sekat * dataSekat, String _link, int _interval) {
//  static unsigned lastTime = 0;
//  char str[32] = "";
//  byte readcard[4];
//  PT_BEGIN(pt);
//  while (1) {
//
//    PT_WAIT_UNTIL(pt, millis() - lastTime > _interval);
//    lastTime = millis();
//    // Serial.println("...RFID..");
//    if (!mfrc522.PICC_IsNewCardPresent()) {
//      bacaRfid = 0;
//
//    } else {
//      if (!mfrc522.PICC_ReadCardSerial()) {
//        bacaRfid = 0;
//
//      } else {
//        for (int i = 0; i < 4; i++) {
//          readcard[i] = mfrc522.uid.uidByte[i]; //storing the UID of the tag in readcard
//          array_to_string(readcard, 4, str);
//          StrUID = str;
//        }
//        mfrc522.PICC_HaltA();
//
//        Serial.print("THE UID OF THE SCANNED CARD IS : ");
//        Serial.println(StrUID);
//        bacaRfid = 1;
//
//        struct Sekat * dataSekat_Temp = dataSekat;
//        if (callbackBoardMode == 1 && bacaRfid == 1) userModeInit(StrUID, &dataSekat_Temp, _link  );
//
//      }
//    }
//  }
//  PT_END(pt);
//}
//
//static unsigned int ledKondisi(int _pin, int _kondisi) {
//  if (_kondisi == HIDUP) {
//    digitalWrite(_pin, HIGH);
//    return 1;
//  } else {
//    digitalWrite(_pin, LOW);
//    return 0;
//  }
//}
//
//static unsigned int blinkyLED1(struct pt * pt, int _pin, int _interval) {
//  static unsigned int lastTime = 0;
//  static unsigned int led = ledKondisi(_pin, MATI);
//  //static unsigned ledKondisi
//  PT_BEGIN(pt);
//  while (1) {
//    lastTime = millis();
//    PT_WAIT_UNTIL(pt, millis() - lastTime > _interval);
//    if (led == MATI) {
//      led = ledKondisi(_pin, HIDUP);
//    } else {
//      led = ledKondisi(_pin, MATI);
//    }
//  }
//
//  PT_END(pt);
//}
//
//static unsigned int blinkyLED2(struct pt * pt, int _pin, int _interval) {
//  static unsigned lastTime = 0;
//  static unsigned led = ledKondisi(_pin, MATI);
//  //static unsigned ledKondisi
//  PT_BEGIN(pt);
//  while (1) {
//    lastTime = millis();
//    PT_WAIT_UNTIL(pt, millis() - lastTime > _interval);
//    if (led == MATI) {
//      led = ledKondisi(_pin, HIDUP);
//    } else {
//      led = ledKondisi(_pin, MATI);
//    }
//  }
//
//  PT_END(pt);
//}
