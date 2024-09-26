# Service zur Berechnung einer Versicherungsprämie

## Vielen Dank für diese Codingsaufgabe. Ich habe es sehr genossen. :)

## Deine Aufgabe

Erstelle eine Anwendung mit folgenden Anforderungen:

- Einträge werden in einer Datenbank gespeichert. Welche würdest du nutzen? Begründe deine Entscheidung.
- Erstelle die notwendingen Services, mindestens zwei! Entscheide, wo die Services anhand der fachlichen Domäne und Anforderungen aufgeteilt werden.
- Verwende ein Test-Framework und erläutere dein Konzept zur Wahrung der Softwarequalität.
- Wie erfolgt die Kommunikation zwischen den Services?
- Erstelle sowohl Code als auch Dokumentation.
- (Optional) erstelle eine Web-basierte Oberfläche.

## Lösung
- Die Idee nutzt ein typisches Springboot als Backend, um kunden- und fahrzeugbezogene Informationen zu speichern.
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
- Auch ein UI (`linkki`) ist dabei. Nach dem Start der App sollte diese automatisch zum Browser springen. Wenn nicht, bitte kopiert die Adresse `http://localhost:8080/clients` in deinen Browser.
  - Eine neue Kunde kann durch `+ New Client` hinzugefügt. Hier sollen `Firstname`, `Lastname`, `street`, `House Number`, `PostCode`in einem Dialog eingegeben werden. 
    - Tipp: Bitte aktualisiert die Seite, nachdem einer Kunde hinzugefügt ist. Dies scheint ein Bug von Linkki zu sein.
  - Nachdem Aktualisierung bekommt man schon einen Kunden. Ganz rechts befindet sich ein `Cars` und ein `Stifticon`. Wenn man auf das Stiftsymbol klicken, kann die Kundeninformation geändert werden.
  - Wenn man auf `cars` klicken, wird die Seite zum Cardetail weitergeleitet. Falls noch kein Car gibt, kann auch durch `+` Button ein Auto hinzufügen. Gleichfalls gibt es auch ein Stifticon, damit die Autoinformationen geändert wird.
