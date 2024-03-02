import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JScrollPane;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Rubrica rubrica;


	public FrameMain(Rubrica rubrica) {
		setMinimumSize(new Dimension(20, 20));
		setTitle("Rubrica_lc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 303);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{474, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		this.rubrica = rubrica;
		
		String[] nomiColonne = {
				"Nome",
				"Cognome",
				"Indirizzo",
				"Num.Telefono",
				"Etá"
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
        
		table = new JTable(rubrica.getArray(),nomiColonne){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMaximumSize(new Dimension(2, 1));
		menuBar.setMinimumSize(new Dimension(2, 1));
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		setJMenuBar(menuBar);
		
		JButton btnNuovo = new JButton("Nuovo");
		btnNuovo.setContentAreaFilled(false);
		btnNuovo.setBorderPainted(false);
		btnNuovo.setBorder(UIManager.getBorder("Button.border"));
		btnNuovo.setMargin(new Insets(1, 7, 1, 7));
		menuBar.add(btnNuovo);
		
		btnNuovo.addActionListener(e -> {
		    FrameNew frameNew = new FrameNew(rubrica);
		    frameNew.setVisible(true);
		    frameNew.addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
		            refreshTable(); // Aggiorna il contenuto della tabella quando viene chiusa la finestra
		        }
		    });
		});


		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setContentAreaFilled(false);
		btnModifica.setBorderPainted(false);
		btnModifica.setMargin(new Insets(1, 7, 1, 7));
		menuBar.add(btnModifica);
		
		btnModifica.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            // Recupera la persona usando il suo id
		            Persona persona = rubrica.getAllPersona().get(selectedRow); 
		            openFrameEdit(persona);
		        } else {
		            JOptionPane.showMessageDialog(FrameMain.this, "Seleziona una persona da modificare.", "Nessuna persona selezionata", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setContentAreaFilled(false);
		btnElimina.setBorderPainted(false);
		btnElimina.setMargin(new Insets(1, 7, 1, 7));
		menuBar.add(btnElimina);
		
		btnElimina.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            // Usa il nome e cognome della selezione per la finestra di conferma
		            String nome = table.getModel().getValueAt(selectedRow, 0).toString();
		            String cognome = table.getModel().getValueAt(selectedRow, 1).toString();
		            
		            // Finestra di conferma
		            int response = JOptionPane.showConfirmDialog(
		                FrameMain.this, 
		                "Eliminare la persona " + nome + " " + cognome + "?", 
		                "Conferma eliminazione", 
		                JOptionPane.YES_NO_OPTION, 
		                JOptionPane.QUESTION_MESSAGE
		            );
		            
		            if (response == JOptionPane.YES_OPTION) {
		                // Elimina la persona e aggiorna la tabella
		                rubrica.removePersona(rubrica.getAllPersona().get(selectedRow));
		                refreshTable();
		            }
		            // Se si sceglie no, chiudi senza far nulla
		        } else {
		            JOptionPane.showMessageDialog(
		                FrameMain.this, 
		                "É necessario selezionare una persona per poterla eliminare.", 
		                "Errore", 
		                JOptionPane.ERROR_MESSAGE
		            );
		        }
		    }
		});
		menuBar.add(btnElimina);
	}
    private void openFrameEdit(Persona persona) {
        FrameEdit frameEdit = new FrameEdit(persona);
        frameEdit.setFrameEditListener(new FrameEdit.FrameEditListener() {
            @Override
            public void onSave(Persona updatedPersona) {
                // Modifica la persona e aggiorna la tabella
                rubrica.updatePersona(updatedPersona); 
                refreshTable(); // 
            }

            @Override
            public void onCancel() {
            }
        });
        frameEdit.setVisible(true);
    }
    private void refreshTable() {
        // Converti rubrica in un Object[][] per essere usata con JTable
        Object[][] updatedData = rubrica.getArray();
        
        // Column Names
        String[] columnNames = {
            "Nome",
            "Cognome",
            "Indirizzo",
            "Num.Telefono",
            "Etá"
        };

        // Usa il nuovo modello per la tabella
        table.setModel(new DefaultTableModel(updatedData, columnNames) {
            private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                // Make table cells non-editable
                return false;
            }
        });

    }



	
}
