
/**
 * Write a description of DiceRolling here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
public class DiceRolling {
    
    public void simpleSimulate(int rolls) {
        Random rand = new Random();
        int twos = 0;
        int twelves = 0;
        
        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            
            if ((d1 + d2) == 2) {
                twos++;
            } else if ((d1 + d2) == 12) {
                twelves++;
            }
        }
        
        printResult(twos, twelves, rolls);
    }
    
    private void printResult(int twos, int twelves, int rolls) {
        System.out.println("2's =\t" + twos + "\t" + 100.0 * twos/rolls);
        System.out.println("12's =\t" + twelves + "\t" + 100.0 * twelves/rolls);        
    }
}
