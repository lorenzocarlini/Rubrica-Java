import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrameNew extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField nomeField, cognomeField, indirizzoField, telefonoField, etaField;
    private JButton saveButton, cancelButton;
    private Rubrica rubrica; // Reference alla rubrica

    public FrameNew(Rubrica rubrica) {
        this.rubrica = rubrica;
        initUI();

    }

    private void initUI() {
        setTitle("New Persona");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5)); // 6 righe, 2 colonne, 5px padding

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Cognome:"));
        cognomeField = new JTextField();
        add(cognomeField);

        add(new JLabel("Indirizzo:"));
        indirizzoField = new JTextField();
        add(indirizzoField);

        add(new JLabel("Telefono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        add(new JLabel("Età:"));
        etaField = new JTextField();
        add(etaField);

        saveButton = new JButton("Aggiungi");
        saveButton.addActionListener(e -> savePersona());
        add(saveButton);

        cancelButton = new JButton("Annulla");
        cancelButton.addActionListener(e -> dispose()); // Chiudi il frame
        add(cancelButton);
    }

    private void savePersona() {
        try {
            String nome = nomeField.getText();
            String cognome = cognomeField.getText();
            String indirizzo = indirizzoField.getText();
            String telefono = telefonoField.getText();
            int eta = Integer.parseInt(etaField.getText());

            // Valida i campi
            if(nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || telefono.isEmpty() || etaField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nessuno dei campi deve essere vuoto, e l'etá deve essere espressa in numeri.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Persona persona = new Persona(nome, cognome, indirizzo, telefono, eta);
            rubrica.addPersona(persona);
            JOptionPane.showMessageDialog(this, "Persona aggiunta con successo!");
            dispose(); // Chiudi dopo aver salvato
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'etá deve essere espressa in numeri.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
