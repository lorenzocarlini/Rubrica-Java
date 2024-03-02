import java.util.Vector;
public class Rubrica {
    private Vector<Persona> rubrica;
    
    

    public Rubrica() {
        this.rubrica = new Vector<Persona>();
    }

    public void addPersona(Persona persona) {
        int maxId = 0;
        for (Persona p : rubrica) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        persona.setId(maxId + 1); // Aumenta di 1 il contatore degli id assegnati
        rubrica.add(persona);
    }
    
    public void removePersona(Persona persona){
        rubrica.remove(persona);
    }

    public Vector<Persona> getAllPersona(){
        return rubrica;
    }
    
    public void updatePersona(Persona updatedPersona) {
        for (int i = 0; i < rubrica.size(); i++) {
            Persona persona = rubrica.get(i);
            if (persona.getId() == updatedPersona.getId()) { //Cerca la persona da aggiornare tramite l'id univoco
                persona.setNome(updatedPersona.getNome());
                persona.setCognome(updatedPersona.getCognome());
                persona.setIndirizzo(updatedPersona.getIndirizzo());
                persona.setTelefono(updatedPersona.getTelefono());
                persona.setEta(updatedPersona.getEta());
                break;
            }
        }
    }



    
    public Object[][] getArray(){
    	Object[][] array = new Object[rubrica.size()][];
    	for (int i = 0; i < rubrica.size(); i++) {
    		Persona p = rubrica.get(i);
    		array[i] = new Object[] {p.getNome(),p.getCognome(),p.getIndirizzo(),p.getTelefono(),p.getEta()
    		};    	
    	}
    	return array;
    }
    

}
