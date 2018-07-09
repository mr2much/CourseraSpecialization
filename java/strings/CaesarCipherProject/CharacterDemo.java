
/**
 * Write a description of CharacterDemo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharacterDemo {
    public void digitTest() {
        String test = "ABCabc0123456789';#!";
        
        for (int i = 0; i < test.length(); i++) {
            char ch = test.charAt(i);
            
            if (Character.isDigit(ch)) {
                System.out.println("Character " + ch + " is a digit.");
            } else if (Character.isLetter(ch)) {
                System.out.println("Character " + ch + " is a letter.");
            } else {
                System.out.println("Character " + ch + " is a symbol.");
            }
        }
    }
}
