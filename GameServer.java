package gioco;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameServer {
    public static void main(String[] args) {
        int port = 12345; // Porta su cui il server rimane in ascolto
        Scanner scanner = new Scanner(System.in); // Scanner per leggere input da tastiera (mossa del server-player)

        // Creazione del server socket che rimane in ascolto sulla porta specificata
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server avviato. In attesa di connessione...");

            // Attende la connessione di un client (bloccante finché non arriva)
            Socket socket = serverSocket.accept();
            System.out.println("Client connesso: " + socket.getInetAddress());

            // Canali di comunicazione con il client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // per ricevere messaggi
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // per inviare messaggi (flush automatico)

            // Ciclo principale della partita
            while (true) {
                // Riceve la mossa inviata dal client
                String clientMove = in.readLine();
                if (clientMove == null || clientMove.equalsIgnoreCase("quit")) {
                    // Se il client chiude la connessione o scrive "quit", termina la partita
                    System.out.println("Client ha terminato la partita.");
                    break;
                }

                System.out.println("Client ha scelto: " + clientMove);

                // Chiede al giocatore lato server di inserire la propria mossa
                System.out.print("La tua mossa (sasso/carta/forbice/quit): ");
                String serverMove = scanner.nextLine().toLowerCase();

                // Se il server-player decide di uscire
                if (serverMove.equals("quit")) {
                    System.out.println("Hai terminato la partita.");
                    out.println("Server ha abbandonato la partita."); // Notifica al client
                    break;
                }

                System.out.println("Server ha scelto: " + serverMove);

                // Determina il risultato della partita confrontando le mosse
                String result;
                if (clientMove.equals(serverMove)) {
                    result = "Pareggio!";
                } else if ((clientMove.equals("sasso") && serverMove.equals("forbice")) ||
                           (clientMove.equals("carta") && serverMove.equals("sasso")) ||
                           (clientMove.equals("forbice") && serverMove.equals("carta"))) {
                    result = "Client vince!";
                } else {
                    result = "Server vince!";
                }

                // Invia al client la mossa del server e il risultato
                out.println("Server: " + serverMove + " | Risultato: " + result);
            }

            // Chiude la connessione con il client
            socket.close();
        } catch (IOException e) {
            // Gestione di eventuali errori di input/output
            e.printStackTrace();
        }
    }
}
