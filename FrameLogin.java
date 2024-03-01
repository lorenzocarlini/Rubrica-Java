import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameLogin extends JFrame {
private static final long serialVersionUID = 1L;
public interface LoginListener {
    void onLoginSuccess();
    void onLoginFailure();
}

private FrameLogin.LoginListener listener;
public void setLoginListener(LoginListener loginListener) {
    this.listener = loginListener;
}
private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Keychain keychain;

    public FrameLogin(Keychain keychain) {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        this.keychain = keychain;

        // Create and set up the panel
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        // Display the window
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        // Password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 83, 80, 25);
        panel.add(loginButton);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(FrameLogin.this,
                            "Username and Password cannot be empty",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Call your login function here with username and password
                	boolean loginSuccess = keychain.tryLogin(keychain.findUserByUsername(username), password);
                	if (loginSuccess) {
                        if (listener != null) {
                            listener.onLoginSuccess();
                        }
                        FrameLogin.this.dispose(); // Close the login frame
                    } else {
                        if (listener != null) {
                            listener.onLoginFailure();
                        }
                        FrameLogin.this.dispose(); // Close the login frame
                	};
                    login(username, password);
                }
            }
        });
    }

    // Dummy login function for demonstration
    private void login(String username, String password) {
        // Implement your login logic here
    	//keychain.tryLogin(keychain.findUserByName(username),password);
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameLogin();
            }
        });
    }
}
