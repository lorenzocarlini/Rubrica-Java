import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrameLogin extends JFrame {
    private static final long serialVersionUID = 1L;

    public interface LoginListener {
        void onLoginSuccess();
        void onLoginFailure();
    }

    private FrameLogin.LoginListener listener;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Keychain keychain;

    public FrameLogin(Keychain keychain) {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        this.keychain = keychain;

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 83, 80, 25);
        panel.add(loginButton);

        // Shared login action
        ActionListener loginAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        };

        // Attach the action listener to the login button
        loginButton.addActionListener(loginAction);

        // Attach the action to the text fields to trigger on 'Enter' key press
        usernameField.addActionListener(loginAction);
        passwordField.addActionListener(loginAction);
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(FrameLogin.this,
                    "Username and Password cannot be empty",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
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
                JOptionPane.showMessageDialog(FrameLogin.this,
                        "Login failed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setLoginListener(LoginListener loginListener) {
        this.listener = loginListener;
    }
}
