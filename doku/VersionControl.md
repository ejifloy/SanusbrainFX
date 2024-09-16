# Branch Management for SanusbrainFX

## 1. **Erstellen eines neuen Feature-Branches**
Bevor du ein neues Feature entwickelst, erstelle einen eigenen Branch. Dadurch bleibt dein `main`-Branch stabil und unberührt, während du an der neuen Funktion arbeitest.

### Schritte:
1. **Wechsel in dein Projektverzeichnis:**
   ```bash
   cd /path/to/SanusbrainFX
   ```

2. **Wechsle zum `main`-Branch und hole die neuesten Änderungen:**
   ```bash
   git checkout main
   git pull origin main
   ```

3. **Erstelle einen neuen Branch für dein Feature:**
   ```bash
   git checkout -b feature-name
   ```
   Ersetze `feature-name` durch einen beschreibenden Namen für dein Feature, z.B. `user-authentication` oder `report-export`.

4. **Entwickle dein Feature im neuen Branch.**
    - Füge deine Änderungen und neuen Features hinzu.
    - Führe deine Änderungen regelmäßig durch:
      ```bash
      git add .
      git commit -m "Implement feature-name"
      ```

5. **Push den neuen Branch zu GitHub:**
   ```bash
   git push origin feature-name
   ```

---

## 2. **Branch aktualisieren (mit dem `main`-Branch synchronisieren)**

Um deinen Feature-Branch aktuell zu halten, ist es ratsam, regelmäßig Änderungen aus dem `main`-Branch zu integrieren.

### Schritte:
1. **Wechsle zu deinem Feature-Branch:**
   ```bash
   git checkout feature-name
   ```

2. **Hole die neuesten Änderungen vom `main`-Branch:**
   ```bash
   git fetch origin
   ```

3. **Mische die Änderungen in deinen Branch:**
   ```bash
   git merge origin/main
   ```

4. **Löse Konflikte (falls vorhanden):**
    - Bearbeite die betroffenen Dateien, um Merge-Konflikte zu beheben.
    - Führe den Merge durch:
      ```bash
      git add <konflikt-datei>
      git commit -m "Resolved merge conflict between main and feature-name"
      ```

5. **Push den aktualisierten Feature-Branch:**
   ```bash
   git push origin feature-name
   ```

---

## 3. **Feature-Branch in den `main`-Branch mergen**

Sobald dein Feature vollständig ist und getestet wurde, kannst du es in den `main`-Branch mergen.

### Schritte:
1. **Wechsle zum `main`-Branch:**
   ```bash
   git checkout main
   ```

2. **Hole die neuesten Änderungen des `main`-Branches:**
   ```bash
   git pull origin main
   ```

3. **Mische deinen Feature-Branch in den `main`-Branch:**
   ```bash
   git merge feature-name
   ```

4. **Push die Änderungen in den `main`-Branch:**
   ```bash
   git push origin main
   ```

---

## 4. **Branch löschen**

Nachdem du das Feature erfolgreich in den `main`-Branch gemergt hast, kannst du den Feature-Branch löschen.

### Schritte:
1. **Lösche den lokalen Feature-Branch:**
   ```bash
   git branch -d feature-name
   ```

2. **Lösche den Remote-Feature-Branch:**
   ```bash
   git push origin --delete feature-name
   ```

---

## Zusammenfassung der wichtigsten Befehle

- **Erstellen eines neuen Branches**:
  ```bash
  git checkout -b feature-name
  ```

- **Aktualisieren des Branches mit `main`**:
  ```bash
  git merge origin/main
  ```

- **Mergen des Features in `main`**:
  ```bash
  git merge feature-name
  ```

- **Branch löschen**:
  ```bash
  git branch -d feature-name
  git push origin --delete feature-name
  ```