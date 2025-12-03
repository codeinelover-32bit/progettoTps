package gioco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SassoCartaForbiceSwing extends JFrame {
    private JLabel lblRisultato, lblScelte;
    private String[] mosse = {"Sasso", "Carta", "Forbice"};
    private Random rand = new Random();

    public SassoCartaForbiceSwing() {
        setTitle("Sasso Carta Forbice");
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Label titolo
        lblScelte = new JLabel("Fai la tua scelta!", SwingConstants.CENTER);
        lblScelte.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblScelte, BorderLayout.NORTH);

        // Pulsanti con immagini
        JButton btnSasso = new JButton(new ImageIcon("src/sasso.png"));
        JButton btnCarta = new JButton(new ImageIcon("src/carta.png"));
        JButton btnForbice = new JButton(new ImageIcon("src/forbice.png"));

        JPanel panelBottoni = new JPanel(new FlowLayout());
        panelBottoni.add(btnSasso);
        panelBottoni.add(btnCarta);
        panelBottoni.add(btnForbice);
        add(panelBottoni, BorderLayout.CENTER);
        
        btnSasso.setPreferredSize(new Dimension(200, 200));
        btnCarta.setPreferredSize(new Dimension(200, 200));
        btnForbice.setPreferredSize(new Dimension(200, 200));

        // Risultato
        lblRisultato = new JLabel("", SwingConstants.CENTER);
        lblRisultato.setFont(new Font("Arial", Font.BOLD, 18));
        lblRisultato.setForeground(Color.BLUE);
        add(lblRisultato, BorderLayout.SOUTH);

        // Eventi pulsanti
        ActionListener listener = e -> {
            String sceltaUtente = "";
            if (e.getSource() == btnSasso) sceltaUtente = "Sasso";
            else if (e.getSource() == btnCarta) sceltaUtente = "Carta";
            else if (e.getSource() == btnForbice) sceltaUtente = "Forbice";

            String sceltaPC = mosse[rand.nextInt(3)];
            lblScelte.setText("Tu: " + sceltaUtente + " | PC: " + sceltaPC);

            if (sceltaUtente.equals(sceltaPC)) {
                lblRisultato.setText("Pareggio!");
            } else if ((sceltaUtente.equals("Sasso") && sceltaPC.equals("Forbice")) ||
                       (sceltaUtente.equals("Carta") && sceltaPC.equals("Sasso")) ||
                       (sceltaUtente.equals("Forbice") && sceltaPC.equals("Carta"))) {
                lblRisultato.setText("Hai vinto!");
            } else {
                lblRisultato.setText("Hai perso!");
            }
        };

        btnSasso.addActionListener(listener);
        btnCarta.addActionListener(listener);
        btnForbice.addActionListener(listener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SassoCartaForbiceSwing().setVisible(true));
    }
}
