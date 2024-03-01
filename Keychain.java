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
                return utente; // Return the matching user
            }
        }
        return null; // Return null if no matching user is found
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
