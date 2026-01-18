# Manuale Utente: Ricerca Punti di Ristoro ETOUR

## Introduzione

Benvenuti nel manuale utente per l'applicazione "Ricerca Punti di Ristoro ETOUR". Questo software è progettato per consentire agli utenti di trovare rapidamente e facilmente punti di ristoro all'interno di un sistema basato su specifici parametri di ricerca, quali nome, località e tipo. L'applicazione simula un'interazione con un sistema esterno ("ETOUR") includendo tempi di risposta variabili e potenziali interruzioni di connessione, per offrire un'esperienza più realistica e testare le prestazioni del sistema.

Il suo scopo principale è quello di dimostrare una robusta funzionalità di ricerca e gestione delle interazioni utente/sistema, affrontando i requisiti di qualità relativi ai tempi di risposta.

## Funzioni Principali

*   **Ricerca Parametrica**: Permette di cercare punti di ristoro utilizzando uno o più criteri: "Nome", "Località" e "Tipo" (es. Ristorante, Bar, Hotel, Stazione di Servizio).
*   **Gestione dei Risultati**: Visualizza in modo chiaro e organizzato l'elenco dei punti di ristoro che soddisfano i criteri di ricerca.
*   **Feedback di Stato**: Fornisce informazioni in tempo reale sullo stato della ricerca tramite una barra di stato dedicata.
*   **Simulazione di Condizioni Reali**: Include ritardi simulati nel recupero dei dati e la possibilità di interruzioni di connessione con il server ETOUR, mimando scenari reali di rete.
*   **Monitoraggio delle Prestazioni**: Verifica che l'operazione di ricerca si completi entro un requisito di qualità specifico (massimo 15 secondi) e avvisa l'utente in caso di superamento.
*   **UI Reattiva**: Utilizza un approccio asincrono per mantenere l'interfaccia utente sempre responsiva, anche durante operazioni di ricerca prolungate.

## Setup dell'Ambiente

Per eseguire l'applicazione "Ricerca Punti di Ristoro ETOUR", è necessario avere un ambiente Java configurato correttamente.

### Prerequisiti

*   **Java Development Kit (JDK) 8 o superiore**: L'applicazione è scritta in Java e richiede un JDK installato per la compilazione e l'esecuzione.

### Passaggi per l'Installazione

1.  **Installa il JDK**:
    *   Se non hai già un JDK, puoi scaricarlo da:
        *   [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)
        *   [Adoptium Temurin (Open-source JDK)](https://adoptium.net/temurin/releases/)
    *   Segui le istruzioni di installazione fornite dal tuo provider JDK. Assicurati che le variabili d'ambiente `JAVA_HOME` e `PATH` siano configurate correttamente. Puoi verificarlo aprendo un terminale o prompt dei comandi e digitando `java -version` e `javac -version`.

2.  **Scarica il Codice Sorgente**:
    *   Assicurati di avere tutti i file `.java` forniti (etourconnectionexception.java, restpoint.java, restpointservice.java, searchformpanel.java, restpointlistpanel.java, ricercapuntidiristoroapp.java).
    *   Organizza il codice sorgente in una struttura di directory che rifletta i package Java. Ad esempio, sotto una directory `src` (o il nome che preferisci per la radice del tuo progetto):
        ```
        /your_project_root
        └── src
            └── com
                └── chatdev
                    └── ricercapuntidiristoro
                        ├── ETOURConnectionException.java
                        ├── RicercaPuntiDiRistoroApp.java
                        ├── model
                        │     └── RestPoint.java
                        ├── service
                        │     └── RestPointService.java
                        └── ui
                              ├── RestPointListPanel.java
                              └── SearchFormPanel.java
        ```

## Come Usare (Avviare e Giocare)

Segui questi passaggi per compilare ed eseguire l'applicazione.

### 1. Compilare il Codice Sorgente

Apri un terminale o prompt dei comandi e naviga alla directory `your_project_root` (quella che contiene la cartella `src`).

Esegui il seguente comando per compilare tutti i file Java:

```bash
javac -d bin src/com/chatdev/ricercapuntidiristoro/*.java src/com/chatdev/ricercapuntidiristoro/model/*.java src/com/chatdev/ricercapuntidiristoro/service/*.java src/com/chatdev/ricercapuntidiristoro/ui/*.java
```

Questo comando compilerà i file `.java` e posizionerà i file `.class` risultanti nella cartella `bin/`. Potrebbe essere necessario modificare `bin` e `src` in base alla tua struttura di directory, o semplicemente navigare nella cartella `src` ed eseguire `javac com/chatdev/.../*.java`.

*   **Nota**: `-d bin` indica alla compilazione di mettere i file `.class` all'interno di una cartella `bin` (che verrà creata se non esiste).

### 2. Eseguire l'Applicazione

Dopo aver compilato con successo, puoi eseguire l'applicazione dal tuo terminale:

Assicurati di essere nella directory `your_project_root`.

```bash
java -cp bin com.chatdev.ricercapuntidiristoro.RicercaPuntiDiRistoroApp
```

*   **Nota**: `-cp bin` aggiunge la directory `bin` al classpath, in modo che la Java Virtual Machine (JVM) possa trovare i file `.class` compilati.

Questo avvierà l'interfaccia grafica dell'applicazione "Ricerca Punti di Ristoro ETOUR".

### 3. Utilizzo dell'Interfaccia di Ricerca

Una volta avviata, l'applicazione presenterà una finestra con le seguenti sezioni:

*   **Pannello di Ricerca (in alto)**: Contiene i campi di input e il pulsante "Cerca".
*   **Pannello Risultati (al centro)**: Mostrerà l'elenco dei punti di ristoro trovati.
*   **Barra di Stato (in basso)**: Fornisce feedback testuale sull'operazione corrente.

Ecco come interagire:

1.  **Attivare la Funzionalità di Ricerca**: L'applicazione si apre con il form di ricerca già visibile e pronto all'uso.
2.  **Visualizzazione del Form per la Ricerca**: Il pannello "Cerca Punti di Ristoro" contiene tre campi di testo:
    *   **Nome**: Inserisci parte o l'intero nome del punto di ristoro (es. "Trattoria", "Caffè").
    *   **Località**: Inserisci parte o l'intera località/città (es. "Milano", "Roma").
    *   **Tipo**: Inserisci il tipo di punto di ristoro desiderato (es. "Restaurant", "Cafe", "Hotel", "Service Station").
    *   **Suggerimento**: Puoi lasciare vuoti uno o più campi per effettuare una ricerca più ampia. Ad esempio, se lasci tutti i campi vuoti e premi "Cerca", verranno visualizzati tutti i punti di ristoro disponibili nel sistema simulato.
3.  **Compilare il Form di Ricerca e Inviare**:
    *   Inserisci i tuoi criteri di ricerca desiderati nei rispettivi campi.
    *   Clicca sul pulsante **"Cerca"**.
4.  **Elaborazione della Richiesta**:
    *   Immediatamente dopo aver cliccato "Cerca", la **barra di stato** in basso mostrerà "Ricerca in corso... attendere prego."
    *   Il form di ricerca verrà temporaneamente disabilitato per prevenire ulteriori input mentre la ricerca è in corso.
    *   L'applicazione simulerà un'attesa (per mimare la latenza di rete) che può durare fino a 14 secondi.
    *   In modo casuale, potrebbe verificarsi un'**interruzione della connessione al server ETOUR**, che verrà visualizzata tramite un messaggio di errore pop-up e nella barra di stato.
5.  **Condizioni di Uscita**:
    *   **Lista di Punti di Ristoro Trovati**: Se la ricerca ha successo, il pannello "Risultati Ricerca" si popolerà con l'elenco dei punti di ristoro che soddisfano i tuoi criteri. La barra di stato indicherà il numero di risultati trovati e il tempo impiegato per la ricerca.
    *   **Nessun Risultato**: Se nessun punto di ristoro corrisponde ai criteri, la lista rimarrà vuota e la barra di stato indicherà "Nessun punto di ristoro trovato...".
    *   **Interruzione della Connessione al server ETOUR**: In caso di errore simulato, apparirà una finestra di dialogo "Errore di Connessione" con un messaggio dettagliato, e la barra di stato rifletterà l'errore.
    *   **Avviso Prestazioni**: Se l'operazione di ricerca supera i 15 secondi (requisito di qualità), apparirà un messaggio di avviso pop-up ("Avviso Prestazioni") che segnala il superamento del limite di tempo, anche se i risultati sono stati poi restituiti.

### 4. Interpretazione dei Risultati e del Feedback

*   **Pannello "Risultati Ricerca"**: Ogni elemento della lista rappresenterà un punto di ristoro, formattato come "Nome (Tipo) - Località".
*   **Barra di Stato**: Tienila d'occhio per il feedback in tempo reale su operazioni, errori e tempi di esecuzione.
*   **Pop-up di Errore**: In caso di problemi di connessione o altri errori interni, verranno visualizzate finestre di dialogo informative per guidare l'utente.
*   **Qualità e Prestazioni**: L'applicazione monitora la durata della ricerca. Sebbene la simulazione possa intenzionalmente superare i 15 secondi per dimostrare la gestione del requisito, in un ambiente reale questo indicherebbe una necessità di ottimizzazione.

Ora sei pronto per utilizzare l'applicazione "Ricerca Punti di Ristoro ETOUR" e testare le sue funzionalità!