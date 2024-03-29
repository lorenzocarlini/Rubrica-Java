import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){
        Rubrica rubrica = new Rubrica();

        Keychain keychain = new Keychain();
        keychain.addUtente(new Utente("Admin","Password"));


        SwingUtilities.invokeLater(() -> {
            FrameLogin frameLogin = new FrameLogin(keychain);
            frameLogin.setLoginListener(new FrameLogin.LoginListener() {
                @Override
				public void onLoginSuccess() {
                    SwingUtilities.invokeLater(() -> {
                        FrameMain frameMain = new FrameMain(rubrica);
                        frameMain.setVisible(true);
                        frameMain.setLocationRelativeTo(null);
                    });
                }

                @Override
				public void onLoginFailure() {
                    System.out.println("Login failed");

                }
            });
        });
    }
}
