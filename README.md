Gioco: Sasso-Carta-Forbice

Descrizione:

Il gioco implementa la classica partita di Sasso-Carta-Forbice tra un SERVER e un CLIENT.
Il server rappresenta un giocatore umano con GUI, il client rappresenta l'altro giocatore.
La partita è gestita tramite socket TCP.

Istruzioni di compilazione:

1. Aprire il terminale nella cartella del progetto.
2. Compilare tutti i file Java con:
   -Eclipse

Istruzioni di avvio:

1. Avviare prima il server:
   GiocoServer
   - Comparirà una finestra GUI del server.
2. Avviare poi il client:
   GiocoClient
   - Inserire l'IP del server quando richiesto.
3. Seguire le istruzioni per scegliere le mosse.
4. Ogni turno ha un timer di 10 secondi;

Note sulla logica di gioco:

- Le mosse valide sono: SASSO, CARTA, FORBICE.
- La funzione GameLogic restituisce:
  0 => pareggio
  1 => vittoria del server
  2 => vittoria del client
- Timeout del client comporta un punto automatico per il server.

Note sull'implementazione dei socket:

- La comunicazione tra server e client avviene tramite socket TCP.
- Il server invia messaggi di tipo testo per richiedere la mossa, inviare il risultato e aggiornare il punteggio.
- Il client invia la mossa selezionata o "timeout" se scaduto il tempo.
- Il protocollo è sincrono: una richiesta per turno, con attesa della risposta.

Autori:

[Reci Ledio, Lamera Andrea]


