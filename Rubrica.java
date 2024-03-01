import java.util.Vector;
public class Rubrica {
    private Vector<Persona> rubrica;

    public Rubrica() {
        this.rubrica = new Vector<Persona>();
    }

    public void addPersona(Persona persona){
        rubrica.add(persona);
    }

    public void removePersona(Persona persona){
        rubrica.remove(persona);
    }

    public Vector<Persona> getAllPersona(){
        return rubrica;
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
