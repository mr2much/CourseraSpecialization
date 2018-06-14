
/**
 * Write a description of NameListTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NameListTester {
    public void performTests() {
        testInsertName();
        System.out.println("Finished testing NamesList");
    }
    
    public void testInsertName() {
        NamesList namesList = new NamesList();
        RankedName jennifer = new RankedName("Jennifer", "F", 5);
        
        namesList.insert(jennifer);
        
        assert namesList.totalBirths() == 1;
        
        RankedName grace = new RankedName("Grace", "F", 5);
        
        namesList.insert(grace);
        
        assert namesList.totalBirths() == 2;
        assert namesList.totalFemales() == 2;
        assert namesList.totalMales() == 0;
        
        RankedName anubis = new RankedName("Anubis", "M", 1);
        
        namesList.insert(anubis);
        
        assert namesList.totalBirths() == 3;
        assert namesList.totalMales() == 1;
    }
}
