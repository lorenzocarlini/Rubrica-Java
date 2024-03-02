import java.util.Vector;
public class Keychain {
    private Vector<Utente> keychain;

    public Keychain() {
        this.keychain = new Vector<Utente>();
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
    	return utente.checkPassword(utente.getHashedPassword(), inPassword) == true;
    	}
    }
}
