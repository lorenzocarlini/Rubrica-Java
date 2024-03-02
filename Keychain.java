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
            // Load database properties
            String propertiesPath = "credenziali_database.properties";
            props.load(new FileInputStream(propertiesPath));

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            // Ensure the JDBC driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error loading database credentials.");
            e.printStackTrace();
        }
    }

    private void loadUtentiFromDB() {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Utente");
            while (resultSet.next()) {
                Utente utente = new Utente(resultSet.getString("username"),resultSet.getString("password"));
                // Populate other Utente fields as necessary

                this.keychain.add(utente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading utenti from database.");
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
        return null; // Restituisci null se non é stato trovato nessun utente corrispondente
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
