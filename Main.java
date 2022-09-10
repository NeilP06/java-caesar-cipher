import java.util.*;

/**
* @author NeilP06 
*/
public class Main {
    public static void main (String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nWelcome to the Caesar Cipher encryptor/decryptor! You have two options: encode or decode.\n> ");
            String option = scanner.nextLine();    
            if (option.equals("encode")) {
                System.out.print("\nChoose a message: ");
                String message = scanner.nextLine();
                System.out.print("Choose your key: ");
                int key = scanner.nextInt();
                String encrypted = encode(message, key);
                System.out.println("\nHere is your new encrypted message: \"" + encrypted + "\"\n");
            } else if (option.equals("decode")) {
                System.out.print("\nChoose a message: ");
                String message = scanner.nextLine();
                System.out.print("Choose your key: ");
                int key = scanner.nextInt();
                String decrypted = decode(message, key);
                System.out.println("\nHere is your new decrypted message: \"" + decrypted + "\"\n");
            } else {
                System.out.println("\nYou didn't choose one of the two commands; try again.\n");
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("\nYou have an error with your key input; try again.\n");
        }
    }

    /**
    * @param message
    * @param key
    * @return
    * @summary: uses indexOf() methods (not ASCII index-shifting methods) to encrypt a certain message.
    */
    public static String encode(String message, int key) {
        String indicator = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseIndicator = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encodedString = "";
        for (int i = 0; i < message.length(); i++) {
            char replacement = message.charAt(i);
            int index = -1;
            if (Character.isLetter(replacement)) {
                if (Character.isUpperCase(replacement)) {
                    index = uppercaseIndicator.indexOf(replacement);
                } else if (Character.isLowerCase(replacement)) {
                    index = indicator.indexOf(replacement);
                }
            }
            if (Character.isLetter(replacement)) {
                if (key + index >= 0) {
                    if (Character.isUpperCase(replacement)) {
                        replacement = uppercaseIndicator.charAt((key + index) % 26);
                    } else if (Character.isLowerCase(replacement)) {
                        replacement = indicator.charAt((key + index) % 26);
                    }
                } else {
                    replacement = indicator.charAt(((key + ((Math.abs(key) + 26 / 26) * 26)) + Math.abs(index)) % 26);
                }
            }
            encodedString += "" + replacement;
        }
        return encodedString;        
    }

    /**
    * @param message
    * @param key
    * @return
    * @summary: uses ASCII index-shifting methods to decrypt a certain message.
    */
    public static String decode(String message, int key) {
        String decodedString = "";
        for (int i = 0; i < message.length(); i++) {
            char current = message.charAt(i);
            char replacement = message.charAt(i);
            int index = (int)replacement;
            if (Character.isLetter(current) && key >= 0) {
                if (Character.isUpperCase(current)) {
                    if (index - (key % 26) >= 65) {
                        replacement = (char)(index - (key % 26));
                    } else {
                        replacement = (char)(index - (key % 26) + 26);
                    }
                } else if (Character.isLowerCase(current)) {
                    if (index - (key % 26) >= 97) {
                        replacement = (char)(index - (key % 26));
                    } else {
                        replacement = (char)(index - (key % 26) + 26);
                    }
                }
                decodedString += "" + replacement;
            } else if (Character.isLetter(current) && key < 0) {
                decodedString = encode(message, key * -1);
            } else {
                decodedString += "" + replacement;
            }
        }
        return decodedString;        
    }
}
