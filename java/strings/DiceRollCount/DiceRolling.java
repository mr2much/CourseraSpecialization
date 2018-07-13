
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
        int[] diceRolls = new int[13];
        
        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            
            System.out.println("Rolled " + (d1+d2));
            diceRolls[d1 + d2]++;
        }
        
        printResult(diceRolls, rolls);
    }
    
    private void printResult(int[] totals, int rolls) {
        for(int i = 2; i < totals.length; i++) {
            System.out.println(i + "'s=\t" + totals[i] + "\t" + 100.0 * totals[i]/rolls);
        }       
    }
}
