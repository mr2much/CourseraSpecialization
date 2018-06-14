
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester {
    
    public void performTests() {
        testEquality();
        testSameRankedNameSameSexEquality();
        testSameRankedNameDifferentSexEquality();
        testDifferentRankedNameSameSexEquality();
        testCompareToSameSex();
        testCompareToDifferentSex();
        //testRankOfNameToday();
        
        System.out.println("Test finished.");
    }
    
    public void testEquality() {
        RankedName jennifer = new RankedName("Jennifer", "F", 21);
        
        assert jennifer.equals(jennifer);
    }
    
    public void testSameRankedNameSameSexEquality() {
        RankedName jennifer = new RankedName("Jennifer", "F", 21);
        assert jennifer.equals(new RankedName("Grace", "F", 21)) :
            "Jennifer's rank should be equal to Grace's";
        assert jennifer.equals(new RankedName("Erica", "F", 21));
        assert jennifer.equals(new RankedName("Barbara", "F", 21));
        assert jennifer.equals(new RankedName("Crystal", "F", 21));
        assert jennifer.equals(new RankedName("Alexandra", "F", 21));
    }
    
    public void testSameRankedNameDifferentSexEquality() {
        RankedName anubis = new RankedName("Anubis", "M", 21);
        assert !anubis.equals(new RankedName("Jennifer", "F", 21)) :
            "Should return false if sex are different";
    }
    
    public void testDifferentRankedNameSameSexEquality() {
        RankedName jennifer = new RankedName("Jennifer", "F", 21);
        assert !jennifer.equals(new RankedName("Jule", "F", 13)) :
            "Names should not be equal if ranks are different";
    }

    public void testCompareToSameSex() {
       RankedName jennifer = new RankedName("Jennifer", "F", 21);
       RankedName barbara = new RankedName("Barbara", "F", 13);
       RankedName crystal = new RankedName("Crystal", "F", 22);
       RankedName anubis = new RankedName("Anubis", "M", 99);
       RankedName erica = new RankedName("Erica", "F", 21);
       
       assert jennifer.compareTo(barbara) > 0 : 
        "Jennifer ranks higher than Barbara";
       assert jennifer.compareTo(crystal) < 0 :
        "Jennifer ranks lower than Crystal";
       assert jennifer.compareTo(erica) == 0 :
        "Jennifer ranks equal to Erica";
    }
    
    public void testCompareToDifferentSex() {
        RankedName jennifer = new RankedName("Jennifer", "F", 21);
        RankedName anubis = new RankedName("Anubis", "M", Integer.MAX_VALUE);
        
        assert jennifer.compareTo(anubis) > 0 :
            "Female names have to rank higher than Male names";
        assert anubis.compareTo(jennifer) < 0 :
            "Male names have to rank lower than Female names";
    }
    
    // public void testRankOfNameToday() {
        // RankedName sameName = ListOfNames.nameWithSameRankToday("Anubis", 1994, 'M');
        
    // }
}
