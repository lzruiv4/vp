# Service zur Berechnung einer Versicherungsprämie

Eine Versicherung berechnet die Versicherungsprämie auf Basis von:

- Jährliche Kilometerleistung
- Fahrzeugtyp
- Region der Fahrzeugzulassung

Für die regionale Zuordnung wird eine CSV "postcodes.csv" verwendet. Die wichtigsten Felder in der CSV sind:

- REGION1 Bundesland
- REGION3 Land
- REGION4 Stadt/Ort
- POSTLEITZAHL Postleitzahl
- LOCATION Bezirk

Interessenten sollen eine Anwendung zur Berechnung der Versicherungsprämie nutzen. Nutzereingaben und die berechnete Prämie sollen persistiert werden.
Der Antragsteller soll die geschätzte Kilometerleistung, Postleitzahl der Zulassungsstelle und den Fahrzeugtyp eingeben.

Zur Berechnung der Prämie wird folgende Formel verwendet:

    Kilometerleistung-Faktor * Fahrzeugtyp-Faktor * Region-Faktor

Der Faktor für die Kilometerleistung ist wie folgt festgelegt:

- 0 bis 5.000 km: 0.5
- 5.001 km bis 10.000 km: 1.0
- 10.001 km bis 20.000 km: 1.5
- ab 20.000km: 2.0

Der Faktor für die Region kann anhand des Bundeslandes gewählt werden. Der Faktor für die Fahrzeugtyp kann frei definiert werden.

Neben der Anwendung für Antragsteller soll eine Integration von Drittanbietern ermöglicht werden.
Dazu soll eine HTTP-API zur Berechnung der Prämie angeboten werden.

## Deine Aufgabe

Erstelle eine Anwendung mit folgenden Anforderungen:

- Einträge werden in einer Datenbank gespeichert. Welche würdest du nutzen? Begründe deine Entscheidung.
- Erstelle die notwendingen Services, mindestens zwei! Entscheide, wo die Services anhand der fachlichen Domäne und Anforderungen aufgeteilt werden.
- Verwende ein Test-Framework und erläutere dein Konzept zur Wahrung der Softwarequalität.
- Wie erfolgt die Kommunikation zwischen den Services?
- Erstelle sowohl Code als auch Dokumentation.
- (Optional) erstelle eine Web-basierte Oberfläche.

Wir legen Wert auf Einfachheit, Testbarkeit und Wartbarkeit.

## Ablauf/Evaluation

Wir setzen keine zeitlichen Einschränkungen. Die Umsetzung soll vielmehr als Gesprächsgrundlage dienen und du musst in der Lage sein, deine Entscheidungen zu erläutern.
Es gibt kein Richtig oder Falsch.

Die Anwendung muss bei uns lokal lauffähig sein.

Abgabe ist entweder per .zip-Datei oder per GitHub-Link möglich.

## Viel Erfolg!
