import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utente {
    private String username;
    private String password;  


public Utente(String username, String password) {
    this.username = username;
    this.password = password;
	}
	

	private String hashPassword(String password) {
	    try {
	        MessageDigest digest = MessageDigest.getInstance("SHA-512");
	        byte[] encodedhash = digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
	        return bytesToHex(encodedhash);
	    	} 
	    catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException(e);
	    	}
		}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if (hex.length() == 1) {
	            hexString.append('0');
	        	}
	        hexString.append(hex);
	    	}
	    return hexString.toString();
		}
	
	
	public boolean checkPassword(String hashedPassword,String inPassword) {
		if (hashedPassword.equals(hashPassword(inPassword))) {
			return true;
			}
		else {
			return false;
			}
		}
	
	
	public String getUsername(){
		return username;
	}
	
	
	public String getHashedPassword(){
		return password;
	}
	

}