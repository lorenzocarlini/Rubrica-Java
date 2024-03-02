import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrameEdit extends JFrame {
    private static final long serialVersionUID = 1L;
    private Persona persona;
    private JTextField nomeField, cognomeField, indirizzoField, telefonoField, etaField;
    private JButton saveButton, cancelButton;

    public interface FrameEditListener {
        void onSave(Persona persona);
        void onCancel();
    }

    private FrameEditListener listener;

    public void setFrameEditListener(FrameEditListener listener) {
        this.listener = listener;
    }

    public FrameEdit(Persona persona) {
        this.persona = persona;
        initUI();
    }

    private void initUI() {
        setTitle("Edit Persona - ID: " + persona.getId());
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Nome:"));
        nomeField = new JTextField(persona.getNome());
        add(nomeField);

        add(new JLabel("Cognome:"));
        cognomeField = new JTextField(persona.getCognome());
        add(cognomeField);

        add(new JLabel("Indirizzo:"));
        indirizzoField = new JTextField(persona.getIndirizzo());
        add(indirizzoField);

        add(new JLabel("Telefono:"));
        telefonoField = new JTextField(persona.getTelefono());
        add(telefonoField);

        add(new JLabel("Età:"));
        etaField = new JTextField(String.valueOf(persona.getEta()));
        add(etaField);

        saveButton = new JButton("Salva");
        saveButton.addActionListener(e -> {
            // Valida l'input prima di aggiornare la persona
            if (nomeField.getText().trim().isEmpty() ||
                cognomeField.getText().trim().isEmpty() ||
                indirizzoField.getText().trim().isEmpty() ||
                telefonoField.getText().trim().isEmpty() ||
                etaField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Nessun campo deve essere vuoto.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
                return; // Ferma l'operazione di aggiornamento
            }

            try {
                int eta = Integer.parseInt(etaField.getText().trim());
                // Crea una nuova persona con i cambi aggiornati
                Persona updatedPersona = new Persona(persona.getId(), nomeField.getText().trim(), cognomeField.getText().trim(), indirizzoField.getText().trim(), telefonoField.getText().trim(), eta);

                if (listener != null) {
                    listener.onSave(updatedPersona);
                }
                dispose(); // Se l'aggiornamento é avvenuto con successo, chiudi il frame
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "L'etá deve essere espressa in numeri.", "Errore di validazione", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(saveButton);

        cancelButton = new JButton("Annulla");
        cancelButton.addActionListener(e -> {
            if (listener != null) {
                listener.onCancel();
            }
            dispose();
        });
        add(cancelButton);
    }
}
