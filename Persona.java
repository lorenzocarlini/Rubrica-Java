import java.util.Objects;

public class Persona {
	int id;
    private String nome;
    private String cognome;  
    private String indirizzo; 
    private String telefono; 
    private int eta;          

public Persona(String nome, String cognome, String indirizzo, String telefono, int eta) {
    this.id = -1;
	this.nome = nome;
    this.cognome = cognome;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.eta = eta;
}

public Persona(int id,String nome, String cognome, String indirizzo, String telefono, int eta) {
    this.id = id;
	this.nome = nome;
    this.cognome = cognome;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.eta = eta;
}

	public int getId() {
	    return id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}
	

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id == persona.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
