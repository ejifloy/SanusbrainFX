Um die Fehlermeldungen deiner JavaFX-Anwendung, die als native App über das GluonFX-Plugin erstellt wurde, in einer Textdatei zu speichern und zu analysieren, kannst du die Konsole verwenden, um die Anwendung zu starten und den Fehler-Output zu protokollieren. Hier ist der Schritt-für-Schritt-Vorgang:

### 1. Anwendung über die Konsole starten
Um die Anwendung über die Konsole zu starten, musst du den Pfad der nativen App kennen, die durch das GluonFX-Plugin erstellt wurde.

- **Windows:**
  Öffne die **Eingabeaufforderung (cmd)** und navigiere in das Verzeichnis, in dem sich die native Anwendung befindet. Der Befehl könnte wie folgt aussehen:

   ```bash
   cd <Pfad-zur-app>
   ```

  Um die Anwendung zu starten, führe diesen Befehl aus:

   ```bash
   app.exe > output.txt 2>&1
   ```

    - `app.exe`: Hier musst du den Namen der ausführbaren Datei deiner App ersetzen.
    - `> output.txt`: Dieser Teil leitet den Standard-Output (normaler Output) in die Datei `output.txt` um.
    - `2>&1`: Dieser Teil leitet den Fehler-Output (Standardfehler) ebenfalls in die `output.txt` um.

  Dadurch werden sowohl normale Ausgaben als auch Fehlermeldungen in der Datei `output.txt` protokolliert.

- **Linux/macOS:**
  Öffne ein **Terminal** und navigiere in das Verzeichnis deiner nativen App:

   ```bash
   cd <Pfad-zur-app>
   ```

  Um die Anwendung zu starten und den Output in eine Textdatei zu leiten, verwende folgenden Befehl:

   ```bash
   ./app > output.txt 2>&1
   ```

    - `./app`: Dies ist der Name der nativen App (ersetze es mit dem richtigen Namen).
    - `> output.txt`: Leitet den normalen Output in die Datei `output.txt`.
    - `2>&1`: Leitet den Fehler-Output ebenfalls in diese Datei.

### 2. Verwendung des GluonFX-Plugins zum Debuggen
Falls du während der Entwicklung mit dem **GluonFX-Plugin** arbeitest und mehr Debugging-Informationen benötigst, kannst du den nativen Compiler so konfigurieren, dass mehr Informationen ausgegeben werden.

Hier sind ein paar Tipps:

- **Aktiviere das Debug-Logging**:
  Stelle sicher, dass du das Logging aktivierst, um detailliertere Informationen zu erhalten. Wenn du das Plugin über die Kommandozeile aufrufst, kannst du den Parameter `-Dgluonfx.logging=FINE` hinzufügen.

   ```bash
   mvn gluonfx:run -Dgluonfx.logging=FINE
   ```

  Dadurch wird das Logging-Level auf "FINE" gesetzt, was eine detaillierte Ausgabe ermöglicht.

### 3. Überprüfe Berechtigungen und Pfade
Bei nativen Anwendungen kann es vorkommen, dass Berechtigungen fehlen oder Pfade zur Datenbank nicht korrekt gesetzt sind. Überprüfe Folgendes:

- **Datenbankverbindungseinstellungen:** Vergewissere dich, dass die Datenbankkonfiguration korrekt ist (IP-Adresse, Port, Benutzername, Passwort).
- **Zugriffsrechte:** Stelle sicher, dass die Anwendung die erforderlichen Rechte hat, um auf die Datenbank und das Netzwerk zuzugreifen (besonders wichtig bei nativen Anwendungen auf verschiedenen Betriebssystemen).
- **Firewall- oder Netzwerkeinstellungen:** Überprüfe, ob die Firewall die Verbindung zur Datenbank möglicherweise blockiert.

### 4. Fehlersuche und Logs analysieren
Nachdem du die Anwendung gestartet und den Output in die Datei `output.txt` geleitet hast, öffne die Datei und überprüfe die dort protokollierten Fehlermeldungen. Achte insbesondere auf Hinweise wie:

- Fehlermeldungen bezüglich der Datenbankverbindung.
- Informationen zu fehlenden Bibliotheken oder Klassen (oft ein Problem bei der nativen Kompilierung).
- Netzwerkfehler oder Berechtigungsprobleme.

Durch diese Schritte solltest du in der Lage sein, den Fehler zu lokalisieren und entsprechende Anpassungen vorzunehmen.

### 5. Alternative: Debuggen in der IDE
Du kannst deine Anwendung auch direkt in der Entwicklungsumgebung (z. B. IntelliJ IDEA oder Eclipse) debuggen, bevor du die native Kompilierung durchführst. Das erleichtert die Fehlersuche und gibt dir direkten Zugriff auf den Stacktrace und die Variablen während der Ausführung.