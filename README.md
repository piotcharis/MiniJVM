**MiniJVM Simulator**

Wir wollen einen Simulator für ein Subset des MiniJVM-Codes entwickeln. Folgende Befehle sollen umgesetzt werden:

- arithmetische Operationen: ADD, SUB
- Laden von Konstanten: CONST i
- Allozieren von Speicherplatz ALLOC i
- Load/Store: LOAD i, STORE i
- Vergleichsoperatoren: LESS
- Logische Operatorn: AND, NOT
- Sprunganweisungen: JUMP i, FJUMP i
- Funktionsaufrufe: CALL i, RET
- Programmende: HALT

Jede Operation soll dabei in einer eigene Klasse umgesetzt werden, die von der abstrakten Klasse Instruction erbt. Ein Simulator besitzt ein Array von Instructions code und einen Stapel stack. Der Stack speichert dabei int-Werte. Der Einfachheit halber wird in dieser Aufgabe als interne Darstellung von Wahrheitswerte ints verwendet. Der Stack der Simulators besitzt eine feste Kapazität, die bei der Instanziierung festgelegt wird. Der stackPointer des Stacks zeigt dabei auf die oberste belegte Zelle. Zudem verfügt eine Instanz von Simulator über einen Programmzähler programCounter, der den Index des am nächsten auszuführenden Befehls speichert und ein Attribut halted, das speichert, ob ein HALT ausgeführt wurde. Der Simulator nimmt bei Aufruf der Funktion executeInstructions dei bei der Instanziierung übergebenen Instruktionen und führt diese aus. Zudem gibt es einen Parser, welcher eine Textdatei in ein Instruction Array umwandelt.

**Stackframes**

Unsere MiniJVM soll auch in der Lage sein Funktionen aufzurufen.
Unsere Funktionen benötigen unter Umständen Variablen, und sollen auf diese zugreifen können. Wenn die CALL instruktion wie ein JUMP funktionieren würde, würde LOAD 0 auf die gleiche Variable zugreifen wie vor dem CALL. Das wäre an sich nicht sonderlich schlimm. Da aber der Wert des Stackpointers unbekannt ist, kann, selbst wenn man mit ALLOC Platz dafür erstellt, nicht auf eigene Variablen zugegriffen werden. Aus diesem Grund, gibt es das Konzept des Stackframes.
Stackpointer ermöglichen 2 Dinge:

- Das Rückspringen aus einer Funktion ohne explizit die Rücksprungadresse zu Programmieren.

- Das nutzen von Lokalen Variablen.

Stackframes sind wie folgt aufgebaut:

Um wieder an die Stelle zurück zu springen, von der aus die Funktion aufgerufen wurde, wird zuerst der aktuelle programCounter auf den Stack gepushed. Anschließend wird, um ihn ebenso zu sichern, der stackOffsetdes alten Frames gepushed. Nun muss der stackOffset noch aktualisiert werden. Der stackOffset soll so gewählt werden, dass man mit LOAD 0 auf das erste Element am Stack, nach dem gesicherten stackOffset zugreifen kann.

Die Dateien sind im Template bereits vorhanden. Die abstrakte Klasse Instruction ist wie die Klasse Stack bereits definiert.

**Aufgaben**

**Der Simulator**

Implementiert einen Simulator, welcher miniJVM Instruktionenen Ausführen kann. Im Konstruktor soll eine stackSize und eine Instruction Array übergeben werden. Die Instructions werden gespeichert, und ein Stack entsprechender größe wird erstellt.

Die executeInstructions Methode soll kontinuierlich die Instruktionen aus dem im Konstruktor übergebenen Array ausführen. Begonnen wird an Index 0. Die Instruktionen werden, sollange nicht gesprungen wird, nach einander ausgeführt. Ist das halted Attribut gesetzt, wird das Ausführen beendet.
Es kann davon ausgegangen werden, dass alle übergebenen Programme dafür sorgen, dass der programCounter innherhalb des Programms bleibt. Es soll dementsprechend nichts abgefangen werden.

**Die Instruktionen**

Die Objekte der Klassen, die die Instruktionen für CONST i, ALLOC i, LOAD i, STORE i, JUMP i, FJUMP i, CALL i darstellen, sollen jeweils einen int-Attribut besitzen, welches den Parameter i des Befehls abspeichert. Erstellt in den Klassen für diese Befehle jeweils einen öffentlichen Konstruktor, der einen int-Parameter erwartet, und das jeweilige Attribut damit initialisiert.

Implementiert für jede konkrete Klasse von Instruktionen die Methode public void execute(Simulator simulator), welche den Stack je nach Befehl passend verändert. Das Verhalten von execute(...) bei den unterschiedlichen Instruktionen ist dabei wie folgt:

- ADD: Nimmt die beiden obersten Elemente vom Stack und setzt das Ergebnis der Addition auf dem Stack.

- SUB: Nimmt die beiden obersten Elemente a, b vom Stack und setzt das Ergebnis von b - a auf den Stack.

- CONST i: Lädt die Konstante i auf den Stack

- ALLOC i: Erhöht den Stackpointer um i. Ihr könnt dazu die Methode alloc von Stack verwenden.

- LOAD i: Liest den Wert am Index i des Stackframes ein und legt ihn auf den Stack.

- STORE i: Nimmt den obersten Wert vom Stack und speichert ihn am Index i des Stackframes.

- LESS: Nimmt die beiden obersten Elemente a, b vom Stack und legt das Ergebnis des Vergleichs b < a als int codiert auf den Stack. dabei soll der Wahrheitswert true durch eine 1 codiert werden, der Wahrheitswert false durch eine 0.

- JUMP i soll einen Sprung zum Befehl am Index i umsetzen.

- AND nimmt die beiden oberen Elemente vom Stack und setzt das Ergebnis eines logischen Undes wieder auf den Stack. Als wahr zählen alle Werte, welche nicht 0 sind.

- NOT Invertiert den Wahrheitswert des obersten Elementes auf dem Stack.

- FJUMP i Nimmt das oberste Element vom Stack. Falls der Wert dieses Elements 0 ist, soll ein Sprung zum Befehl am Index i durchgeführt werden. Andernfalls wird kein Sprung durchgeführt und der Programmfluss geht normal weiter.

- CALL i soll den aktuellen Stackframe wie beschrieben sichern, den stackOffset anpassen und die Ausführung an der übergebenen Adresse fortsetzen.

- RET verlässt den aktuellen Stackframe und setzt den Simulator auf den zuvor gespeicherten Zustand zurück.

- HALT soll das Programm beenden. Dazu soll das halted-Attribut des Simulators auf true gesetzt werden.

Alle Instruktionen mit Parameter sollen zudem eine getParameter Methode haben, mit welcher man diesen abrufen kann.

**Der Parser**

Damit das schreiben von Programmen möglichst einfach wird, soll ein Parser implementiert werden, welcher einen String in ein MiniJVM Programm übersetzt.
Implementiert die statische Methode instructionsFromString welche diese Aufgabe übernimmt.

Die der instructionsFromString Methode übergebenen Strings sind wie folgt aufgebaut:

; Am Anfang einer Zeile definiert einen Kommentar welcher ignoriert wird.

: Am Ende einer Zeile definiert ein Label (Label beinhalten keine weiteren Doppelpunkte)

Sämtliche anderen zeilen sind valide Instruktionen

Instruktionen welche den programCounter verändern können als Parameter sowohl ein Label als auch eine Adresse haben.

Label können sowohl bereits über der Instruktion definiert worden sein, als auch erst nach der Instruktion folgen.

Direkte Sprungadressen beziehen sich auf den n-ten Befehl im Programm und nicht auf die n-te Zeile.

Auf jeder Zeile des Eingabe Strings befindet sich nur ein Befehl.

Man kann davon Ausgehen, dass nur Valide Zeilen enthalten sind.

Es gibt keinen Whitespace am Anfang der Zeile.

Testen
Schreibt für 5 Beliebige Instruktionen je mindestens 2 Tests, welche deren jeweilige execute()-Methode testen. Hierfür gibt es 2 Punkte welche erst mit der manuellen Bewertung vergeben werden. Wie gewohnt sollt ihr auch eure Tests wieder mit Kommentaren erklären.
