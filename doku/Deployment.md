Um deine GluonFX-Anwendung als native Windows-Anwendung auf Basis von GraalVM, Java und JavaFX zu deployen, musst du einige Schritte durchlaufen, die sicherstellen, dass alle nativen Abhängigkeiten korrekt eingerichtet sind. Hier ist eine Schritt-für-Schritt-Anleitung, die auf der GluonFX-Dokumentation basiert und dir hilft, deine Anwendung erfolgreich zu deployen:

### Voraussetzungen:
1. **Visual Studio 2019**: Stelle sicher, dass Visual Studio 2019 installiert ist. Du benötigst die "Desktop development with C++" Workload, da sie die notwendigen Werkzeuge enthält.
2. **GraalVM**: Installiere die GraalVM, die als JDK verwendet wird, um Java-Anwendungen als native Images zu kompilieren. Du kannst die GraalVM CE (Community Edition) verwenden.
3. **Maven oder Gradle**: Die Anwendung wird über Maven oder Gradle gebaut. Stelle sicher, dass die korrekten Plugins und Abhängigkeiten in deiner `pom.xml` oder `build.gradle` definiert sind.
4. **GluonFX Plugin**: Installiere das GluonFX Maven- oder Gradle-Plugin für die native Kompilierung deiner JavaFX-Anwendung.

### Schritt-für-Schritt Anleitung:

#### 1. **Starten der Visual Studio Command Prompt**
GluonFX erfordert die Visual Studio 2019-Tools zum Erstellen nativer Windows-Anwendungen. Verwende dafür die spezielle "x64 Native Tools Command Prompt" von Visual Studio 2019.

##### Option 1: Starten über das Startmenü
- Gehe zu **Start** > **Visual Studio 2019** > **x64 Native Tools Command Prompt** und öffne die Konsole.

##### Option 2: Über einen normalen Command-Prompt
Wenn du die Konsole über ein anderes Terminal starten möchtest, kannst du den folgenden Befehl ausführen:

```bash
cmd.exe /k "<path to VS2019>\VC\Auxiliary\Build\vcvars64.bat"

// Aktueller Pfad:
cmd.exe /k vcvars64.bat 
```

- Ersetze `<path to VS2019>` durch den tatsächlichen Pfad deiner Visual Studio 2019 Installation, beispielsweise `C:\Program Files (x86)\Microsoft Visual Studio\2019\Community`.

#### 2. **Vorbereitung der Maven/Gradle-Build-Konfiguration**
Vergewissere dich, dass du das **GluonFX-Maven-Plugin** korrekt in deiner `pom.xml` konfiguriert hast. Hier ist ein Beispiel:

##### Maven Beispiel (`pom.xml`):
```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.gluonhq</groupId>
            <artifactId>gluonfx-maven-plugin</artifactId>
            <version>1.0.10</version>
            <configuration>
                <mainClass>com.sanusbrain.MainApp</mainClass>
                <attachList>
                    <list>display</list>
                    <list>storage</list>
                </attachList>
            </configuration>
        </plugin>
    </plugins>
</build>
```

#### 3. **Erstellen der nativen Anwendung**
Jetzt kannst du die GluonFX-Befehle zum Erstellen der nativen Anwendung verwenden.

##### Erstellen des nativen Images:
Navigiere in deiner Visual Studio Command Prompt zu deinem Projektverzeichnis und führe den folgenden Maven-Befehl aus:

```bash
mvn gluonfx:build
```

Dieser Befehl erstellt ein nativer Windows-Executable-Binary für deine JavaFX-Anwendung. Der Build-Prozess verwendet GraalVM, um deine Anwendung zu kompilieren.

##### Testen der Anwendung:
Um die Anwendung zu starten, ohne sie zu paketieren, kannst du den folgenden Befehl verwenden:

```bash
mvn gluonfx:nativerun
```

Dieser Befehl startet das native Image direkt und zeigt an, ob alles korrekt funktioniert.

#### 4. **Deployment deiner Anwendung**
Sobald du ein nativer Image erstellt hast, kannst du dieses zur Verteilung verwenden. Um ein vollständiges Paket mit allen benötigten Abhängigkeiten (wie JavaFX-Module) zu erstellen, kannst du den `gluonfx:package` Befehl verwenden.

```bash
mvn gluonfx:package
```

Dieser Befehl erstellt ein Distributionspaket, das du auf andere Maschinen übertragen kannst. Das Paket enthält das native Binary und alle erforderlichen Ressourcen, um die Anwendung auszuführen.

#### 5. **Erstellen eines Installers (Optional)**
Um die Anwendung benutzerfreundlich zu verteilen, kannst du auch einen Installer für deine Anwendung erstellen. GluonFX kann MSIs oder EXEs als Installer erstellen:

```bash
mvn gluonfx:package -Pwindows
```

Dieser Befehl erstellt einen Installer, der den nativen Binary und die benötigten Abhängigkeiten für eine einfache Installation enthält.

#### 6. **Fehlersuche**
Falls Fehler auftreten, solltest du die Konsolenausgaben überprüfen. Um Fehlerprotokolle zu sammeln, kannst du den nativen Binary über die Konsole mit Protokollierung starten, um alle Ausgaben in eine Textdatei umzuleiten:

```bash
./app.exe > output.txt 2>&1
```

#### 7. **Überprüfung des Build-Prozesses**
- **Visual Studio Tools**: Stelle sicher, dass Visual Studio korrekt installiert ist und dass die C++-Komponenten enthalten sind.
- **GraalVM**: Vergewissere dich, dass du die GraalVM korrekt als JDK gesetzt hast. Dies kannst du durch die Eingabe von `java -version` prüfen, um sicherzustellen, dass die GraalVM aktiv ist.

```bash
java -version
```

Der Output sollte ähnlich wie folgt aussehen:

```bash
openjdk version "11.0.x" 2021-03-16
OpenJDK Runtime Environment GraalVM CE 21.x.x (build 11.0.x+10-jvmci-21.x.x)
```

### Zusammenfassung
- **1. Command Prompt starten:** Benutze die `x64 Native Tools Command Prompt for VS 2019` oder den Befehl `cmd.exe /k "<path to VS2019>\VC\Auxiliary\Build\vcvars64.bat"`.
- **2. GluonFX Plugin in Maven/Gradle einrichten:** Stelle sicher, dass die Konfiguration in der `pom.xml` korrekt ist.
- **3. Build erstellen:** Führe den Befehl `mvn gluonfx:build` aus, um ein natives Image zu erstellen.
- **4. Anwendung testen:** Starte die Anwendung mit `mvn gluonfx:nativerun`.
- **5. Deployment:** Erstelle ein Distributionspaket mit `mvn gluonfx:package`.

Mit diesen Schritten kannst du deine GluonFX-Anwendung erfolgreich als native Windows-Anwendung deployen und debuggen.