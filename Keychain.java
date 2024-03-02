import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

public class Keychain {
    private Vector<Utente> keychain;
    private Connection connection;

    public Keychain() {
        this.keychain = new Vector<>();
        initializeDBConnection();
        loadUtentiFromDB();
    }

    private void initializeDBConnection() {
        Properties props = new Properties();
        try {
            // Carica le proprietà del database
            String propertiesPath = "credenziali_database.properties";
            props.load(new FileInputStream(propertiesPath));

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            // Assicurati che il driver JDBC sia caricato
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC MySQL non trovato.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Errore nella connessione al database.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Errore nel caricamento delle credenziali del database.");
            e.printStackTrace();
        }
    }

    private void loadUtentiFromDB() {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Utente");
            while (resultSet.next()) {
                Utente utente = new Utente(resultSet.getString("username"),resultSet.getString("password"));
                // Popola gli altri campi di Utente come necessario

                this.keychain.add(utente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel caricamento degli utenti dal database.");
        }
    }

    public void addUtente(Utente utente){
        keychain.add(utente);
    }

    public void removeUtente(Utente utente){
        keychain.remove(utente);
    }

    public Vector<Utente> getAllUtente(){
        return keychain;
    }

    public Utente findUserByUsername(String username) {
        for (Utente utente : keychain) {
            if (utente.getUsername().equals(username)) {
                return utente; // Restituisci l'utente corrispondente
            }
        }
        return null; // Restituisci null se non è stato trovato nessun utente corrispondente
    }

    public boolean tryLogin(Utente utente, String inPassword) {
    	if(utente == null) {
    		return false;
    	}
    	else {
    	return utente.checkPassword(utente.getHashedPassword(), inPassword);
    	}
    }
}