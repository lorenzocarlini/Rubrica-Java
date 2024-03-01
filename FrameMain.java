import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JScrollPane;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
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
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setContentAreaFilled(false);
		btnModifica.setBorderPainted(false);
		btnModifica.setMargin(new Insets(1, 7, 1, 7));
		menuBar.add(btnModifica);
		
		btnModifica.addActionListener(e -> {
		    // Seleziona l'indice della riga selezionata
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow >= 0) { // Controlla se la riga é selezionata
		  
		        Object[] rowData = new Object[table.getColumnCount()];
		        for (int i = 0; i < table.getColumnCount(); i++) {
		            rowData[i] = table.getModel().getValueAt(selectedRow, i);
		        }

		    } else {
		        JOptionPane.showMessageDialog(this, "Seleziona una persona da modificare.", "Nessuna persona selezionata", JOptionPane.WARNING_MESSAGE);
		    }
		});
		
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setContentAreaFilled(false);
		btnElimina.setBorderPainted(false);
		btnElimina.setMargin(new Insets(1, 7, 1, 7));
		menuBar.add(btnElimina);
	}
}
