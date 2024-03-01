import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){
        Rubrica rubrica = new Rubrica();
        rubrica.addPersona(new Persona("Mario","Rossi", "Via Roma 1", "3201234567", 30));
        rubrica.addPersona(new Persona("Luca","Bianchi", "Corso Milano 22", "3217654321", 25));
        rubrica.addPersona(new Persona("Giulia","Russo", "Viale Lazio 33", "3229876543", 27));
        rubrica.addPersona(new Persona("Francesca","Ferrari", "Piazza Venezia 44", "3234567890", 35));
        rubrica.addPersona(new Persona("Marco","Esposito", "Strada Statale 5", "3240987654", 22));
        rubrica.addPersona(new Persona("Sofia","Brambilla", "Via Torino 6", "3256789012", 28));
        rubrica.addPersona(new Persona("Giovanni","Romano", "Corso Vittorio Emanuele II 77", "3265432109", 40));
        rubrica.addPersona(new Persona("Anna","Colombo", "Viale Monza 88", "3278901234", 32));
        rubrica.addPersona(new Persona("Roberto","Ricci", "Piazza Duomo 9", "3287654321", 29));
        rubrica.addPersona(new Persona("Elena","Marino", "Via Verdi 10", "3290123456", 24));



        Keychain keychain = new Keychain();
        keychain.addUtente(new Utente("Mario","Rossi"));


        SwingUtilities.invokeLater(() -> {
            FrameLogin frameLogin = new FrameLogin(keychain);
            frameLogin.setLoginListener(new FrameLogin.LoginListener() {
                public void onLoginSuccess() {
                    SwingUtilities.invokeLater(() -> {
                        FrameMain frameMain = new FrameMain(rubrica);
                        frameMain.setVisible(true);
                        frameMain.setLocationRelativeTo(null);
                    });
                }

                public void onLoginFailure() {
                    System.out.println("Login failed");

                }
            });
        });
    }
}
