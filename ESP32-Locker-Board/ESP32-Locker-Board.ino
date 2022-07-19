int pinLedMerah = 12;
int pinLedHijau = 14;
int pinBuzzer = 13;

int prox[5] = {35, 34, 39, 36};
int relay[5] = {32, 33, 25, 26};

int readProx[5] = {0, 0, 0, 0, 0} ;

int stateLocker[5] = {0, 0, 0, 0, 0} ;

//DEBUG
bool Read = true;

// WIFI
#include <WiFi.h>
#include <HTTPClient.h>

#define SSID_WIFI "PROJECT"
#define PASS_WIFI ""
#define DOMAIN "http://192.168.100.247/smart-locker"

int statusWifi = 0;


void setup() {
  Serial.begin(9600);

  // GPIO INISIALISASI
  Serial.println("System Init");
  Serial.println("...");

  proximity_init();
  Led_Init();
  Relay_Init();

  WIFI_INIT();

  //  buzzer(1, 500);
  //  buzzer(0, 10);
}


void loop() {
  koneksiWifiChecker();
  bacaProximity();
  
  terimaDariServer_CommandLocker();
  kirimKeServer_LockerState();
  
  Locker_Handler();

}
