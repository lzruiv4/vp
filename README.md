# Service zur Berechnung einer Versicherungsprämie
- Die Idee: Ein typisches Springboot wird als Backend benutzt, um kunden- und fahrzeugbezogene Informationen zu speichern.
  1. Um eine neue Kunde anzulegen, soll man den Namen, die Straße und die Postleitzahl eingeben, dann wird der entsprechende Ort automatisch durch CSV-Dokuments (`RegionService`) und der Postleitzahl ermittelt.
  2. Nach dem Erstellen eines Kunden kann ein versichertes Auto hinzugefügt werden. Gleichfalls können der Fahrzeugtyp, der Jahreskilometerstand und die Fahrzeugzulassungsadresse eingeben und dann wird Versicherungsprämie automatisch über `VersicherungspraemieBerechnenService` erhalten.
- In diesem Projekt habe ich H2 als Datenbank verwendet. Der Vorteil liegt daran, dass es einfach zu bedienen ist und keine zusätzlichen Einstellungen erfordert. Ich habe die Speicherstrategie so geändert, damit alle Daten in `src/main/resources/db/database.mv.db` gespeichert werden.
- In diesem Projekt habe ich 4 Services erstellt.
  - CarService
  - ClientService
  - RegionalService
  - VersicherungspraemieBerechnenService
- Der Backend-Test basiert auf `TestRestTemplate`. Der Frontend-Test habe ich hier nicht gemacht. Deswegen sieht man hier weniger Testabdeckungen. Aber wie gesehen, das Backend ist schon fast alles abgedeckt. ;)
- Anbei sieht man auch ein `vp_postman.postman_collection.json`, damit man durch postman die Request testen kann.
