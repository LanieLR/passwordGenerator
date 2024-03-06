package passwordGenerator;

import java.security.SecureRandom;


public class PasswordGenerator {

	 private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+=-";
	    private static final int DEFAULT_LENGTH = 12;

	    public static void main(String[] args) {
	        int length = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_LENGTH;
	        System.out.println(generatePassword(length));
	    }

	    public static String generatePassword(int length) {
	        if (length < 1) {
	            throw new IllegalArgumentException("Password length must be at least 1");
	        }

	        SecureRandom random = new SecureRandom();
	        StringBuilder password = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            password.append(CHARS.charAt(random.nextInt(CHARS.length())));
	        }

	        // Ensure at least one character from each group
	        if (!password.toString().matches(".*[!@#$%^&*()_+=-].*")) {
	            password.setCharAt(random.nextInt(length), CHARS.charAt(random.nextInt(6)));
	        }
	        if (!password.toString().matches(".*[A-Z].*")) {
	            password.setCharAt(random.nextInt(length), (char) ('A' + random.nextInt(26)));
	        }
	        if (!password.toString().matches(".*[a-z].*")) {
	            password.setCharAt(random.nextInt(length), (char) ('a' + random.nextInt(26)));
	        }
	        if (!password.toString().matches(".*[0-9].*")) {
	            password.setCharAt(random.nextInt(length), (char) ('0' + random.nextInt(10)));
	        }

	        return password.toString();
	    }
	}


	

