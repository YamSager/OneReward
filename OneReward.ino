// SPI - Version: Latest 
#include <SPI.h>

// MFRC522 - Version: Latest 
#include <MFRC522.h>

/*

*/

#define SS_PIN 10
#define RST_PIN 9
 
MFRC522 rfid(SS_PIN, RST_PIN); // Instance of the class

MFRC522::MIFARE_Key key;

int code[] = {69,141,8,136}; //This is the stored UID
int codeRead = 0;
String uidString;

void setup() {
  Serial.begin(9600);
  //Serial.print("Get Ready ...\n");
  SPI.begin(); // Init SPI bus
  rfid.PCD_Init(); // Init MFRC522 
}

void loop() {
  if (rfid.PICC_IsNewCardPresent()) {
  // Select one of the cards
    if (rfid.PICC_ReadCardSerial()) {
      //Show UID on serial monitor
      //Serial.println();
      //Serial.print("UID tag: ");
      String content= "";
      byte letter;
      for (byte i = 0; i < rfid.uid.size; i++) {
        //Serial.print(rfid.uid.uidByte[i] < 0x10 ? "0" : " ");
        //Serial.print(rfid.uid.uidByte[i], HEX);
        content.concat(String(rfid.uid.uidByte[i] < 0x10 ? " 0" : " "));
        content.concat(String(rfid.uid.uidByte[i], HEX));
      }
      content.trim();
      content.toUpperCase();
      Serial.print(content);
      delay(3000);
    }
  }
}

