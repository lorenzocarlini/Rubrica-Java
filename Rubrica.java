import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;
import java.io.FileInputStream;

public class Rubrica {
    private Vector<Persona> rubrica;
    private Connection connection;
    private boolean initialLoadCompleted = false; // Flag to track if initial load from DB has been completed

    public Rubrica() {
        this.rubrica = new Vector<>();
        this.initializeDBConnection();
        this.loadPersonasFromDB();
        this.initialLoadCompleted = true; // Set true after initial load is completed
    }

    private void initializeDBConnection() {
        Properties props = new Properties();
        try {
            // Load database properties
            String propertiesPath = "./credenziali_database.properties";
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

    // Include the rest of the Rubrica methods here...



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
            System.out.println("Error loading personas from database.");
        }
    }
    
    public void addPersona(Persona persona) {
    	   int maxId = 0;
        // Ensure addPersona commits only after the initial loadPersonasFromDB
        if (initialLoadCompleted) {
            for (Persona p : rubrica) {
                if (p.getId() > maxId) {
                    maxId = p.getId();
                }
            }
            if(persona.id == -1) {
            	if (persona.id>maxId) {
            		maxId = persona.id;
            	}
                persona.setId(maxId + 1); // Aumenta di 1 il contatore degli id assegnati
            }
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
                System.out.println("Error adding persona to database.");
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
            System.out.println("Error removing persona from database.");
        }
        
        rubrica.remove(persona);
    }
    
    public Vector<Persona> getAllPersona(){
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
            System.out.println("Error updating persona in database.");
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
