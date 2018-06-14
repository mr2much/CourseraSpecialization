
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester {
    
    public void performTests() {
        testSameRankedNameSameSexEquality();
        testSameRankedNameDifferentSexEquality();
        //testRankOfNameToday();
        
        System.out.println("Test finished.");
    }
    
    public void testSameRankedNameSameSexEquality() {
        RankedName jennifer = new RankedName("Jennifer", 1994, "F", 21);
        assert jennifer.equals(new RankedName("Grace", 2014, "F", 21)) :
            "Jennifer's rank should be equal to Grace's";
    }
    
    public void testSameRankedNameDifferentSexEquality() {
        RankedName anubis = new RankedName("Anubis", 1982, "M", 21);
        assert !anubis.equals(new RankedName("Jennifer", 1994, "F", 21)) :
            "Should return false if sex are different";
    }
    
    // public void testRankOfNameToday() {
        // RankedName sameName = ListOfNames.nameWithSameRankToday("Anubis", 1994, 'M');
        
    // }
}
