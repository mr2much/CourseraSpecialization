package tests;


/**
 * Write a description of ListByYearTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import src.ListByYear;
import src.NamesList;
import src.RankedName;
import src.Gender;
import java.util.List;

public class ListByYearTester {
    public void performTests() {
        shouldLoadTestFileFor2012();
        System.out.println("ListByYear - Tests finished.");
    }    
    
    public void shouldLoadTestFileFor2012() {
        ListByYear listYear = new ListByYear();
        
        listYear.loadListForYear(2012);
        NamesList listOf2012 = listYear.getListForYear(2012);
        
        List<RankedName> females = listOf2012.getAll(Gender.FEMALE);
        
        compare(females, new RankedName[]{
            new RankedName("Sophia", "F", 10),
            new RankedName("Emma", "F", 9),
            new RankedName("Isabella", "F", 8),
            new RankedName("Olivia", "F", 7),
            new RankedName("Ava", "F", 6)});
    }
    
    private void compare(List<RankedName> namesList, RankedName[] namesArr) {
        Object[] arr = namesList.toArray();
        
        for (int i = 0; i < namesArr.length; i++) {
            assert arr[i].equals(namesArr[i]) : "Values: " + 
                arr[i] + " != " + namesArr[i];
        }
    }
    
}
