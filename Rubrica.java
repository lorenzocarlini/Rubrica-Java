import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

public class Rubrica {
    private Vector<Persona> rubrica;
    private Connection connection;
    private boolean initialLoadCompleted = false; // Segnale per tracciare se il caricamento iniziale dal DB è stato completato

    public Rubrica() {
        this.rubrica = new Vector<>();
        this.initializeDBConnection();
        this.loadPersonasFromDB();
        this.initialLoadCompleted = true; // Impostato su true dopo che il caricamento iniziale è completato
    }

    private void initializeDBConnection() {
        Properties props = new Properties();
        try {
            // Carica le proprietà del database
            String propertiesPath = "./credenziali_database.properties";
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

    private void loadPersonasFromDB() {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Persona");
            while (resultSet.next()) {
                Persona persona = new Persona(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("cognome"),
                    resultSet.getString("indirizzo"),
                    resultSet.getString("telefono"),
                    resultSet.getInt("eta")
                );
                this.rubrica.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel caricamento delle persone dal database.");
        }
    }

    public void addPersona(Persona persona) {
        // Assicurati che addPersona effettui il commit solo dopo il caricamento iniziale da loadPersonasFromDB
        if (initialLoadCompleted) {
            int maxId = 0;
            for (Persona p : rubrica) {
                if (p.getId() > maxId) {
                    maxId = p.getId();
                }
            }
            persona.setId(maxId + 1); // Incrementa di 1 il contatore degli id assegnati
            try (PreparedStatement ps = this.connection.prepareStatement(
                    "INSERT INTO Persona (id, nome, cognome, indirizzo, telefono, eta) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, persona.getId());
                ps.setString(2, persona.getNome());
                ps.setString(3, persona.getCognome());
                ps.setString(4, persona.getIndirizzo());
                ps.setString(5, persona.getTelefono());
                ps.setInt(6, persona.getEta());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        persona.setId(generatedKeys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Errore nell'aggiunta della persona al database.");
            }
        }

        rubrica.add(persona);
    }

    public void removePersona(Persona persona) {
        try (PreparedStatement ps = this.connection.prepareStatement(
                "DELETE FROM Persona WHERE id = ?")) {
            ps.setInt(1, persona.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella rimozione della persona dal database.");
        }

        rubrica.remove(persona);
    }

    public Vector<Persona> getAllPersona() {
        return rubrica;
    }

    public void updatePersona(Persona updatedPersona) {
        try (PreparedStatement ps = this.connection.prepareStatement(
                "UPDATE Persona SET nome = ?, cognome = ?, indirizzo = ?, telefono = ?, eta = ? WHERE id = ?")) {
            ps.setString(1, updatedPersona.getNome());
            ps.setString(2, updatedPersona.getCognome());
            ps.setString(3, updatedPersona.getIndirizzo());
            ps.setString(4, updatedPersona.getTelefono());
            ps.setInt(5, updatedPersona.getEta());
            ps.setInt(6, updatedPersona.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento della persona nel database.");
        }

        for (int i = 0; i < rubrica.size(); i++) {
            Persona persona = rubrica.get(i);
            if (persona.getId() == updatedPersona.getId()) {
                rubrica.set(i, updatedPersona);
                break;
            }
        }
    }

    public Object[][] getArray() {
        Object[][] array = new Object[rubrica.size()][];
        for (int i = 0; i < rubrica.size(); i++) {
            Persona p = rubrica.get(i);
            array[i] = new Object[]{p.getNome(), p.getCognome(), p.getIndirizzo(), p.getTelefono(), p.getEta()};
        }
        return array;
    }
}
