
/**
 * Write a description of NameListTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NameListTester {
    public void performTests() {
        testInsertName();
        testRankByName();
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
        
        assert namesList.totalBirths() == 3 : 
            "Expected 3, got " + namesList.totalBirths();
        assert namesList.totalMales() == 1;
    }
    
    public void testRankByName() {
        RankedName jennifer = new RankedName("Jennifer", "F", 1);
        RankedName grace = new RankedName("Grace", "F", 5);
        RankedName carol = new RankedName("Carol", "F", 3);
        RankedName erika = new RankedName("Erika", "F", 2);
        RankedName camila = new RankedName("Camila", "F", 4);
        RankedName anubis = new RankedName("Anubis", "M", 1);
        
        NamesList namesList = new NamesList();
        namesList.insert(jennifer);
        namesList.insert(grace);
        namesList.insert(carol);
        namesList.insert(erika);
        namesList.insert(camila);
        namesList.insert(anubis);
        
        int rank = namesList.rankByName("Erika", Gender.FEMALE);
        
        assert rank == 2 : "Expected rank == 2, got " + rank;
        
        rank = namesList.rankByName("Grace", Gender.FEMALE);
        
        assert rank == 5;
        
        rank = namesList.rankByName("Clara", Gender.FEMALE);
        
        assert rank == -1;
        
        rank = namesList.rankByName("Anubis", Gender.MALE);
        
        assert rank == 1;
    }
}
