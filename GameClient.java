package gioco;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        // Indirizzo del server a cui connettersi
        // "localhost" significa che il server gira sulla stessa macchina
        String host = "localhost"; 
        int port = 12345; // Porta su cui il server è in ascolto

        // Creazione del socket e dei canali di comunicazione
        try (Socket socket = new Socket(host, port); // Connessione al server
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Lettura messaggi dal server
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Invio messaggi al server (flush automatico)
             Scanner scanner = new Scanner(System.in)) { // Lettura input da tastiera

            System.out.println("Connesso al server. Digita 'sasso', 'carta', 'forbice' o 'quit' per uscire.");

            // Ciclo principale della partita
            while (true) {
                // Chiede al giocatore di inserire la propria mossa
                System.out.print("La tua mossa: ");
                String move = scanner.nextLine().toLowerCase(); // Converte in minuscolo per uniformità

                // Invia la mossa al server
                out.println(move);

                // Se il giocatore scrive "quit", termina la partita
                if (move.equals("quit")) {
                    System.out.println("Partita terminata.");
                    break;
                }

                // Riceve la risposta dal server (mossa del server + risultato)
                String response = in.readLine();
                System.out.println(response);
            }

        } catch (IOException e) {
            // Gestione errori di input/output (es. server non raggiungibile)
            e.printStackTrace();
        }
    }
}
