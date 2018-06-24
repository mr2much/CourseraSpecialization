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
        masonShouldBeRankTwoIn2012();
        nameShouldBeOliviaIn2012ForRank4();
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
        
        List<RankedName> males = listOf2012.getAll(Gender.MALE);
        
        compare(males, new RankedName[]{
            new RankedName("Jacob", "M", 8),
            new RankedName("Mason", "M", 7),
            new RankedName("Ethan", "M", 7),
            new RankedName("Noah", "M", 6),
            new RankedName("William", "M", 5)});
    }
    
    private void compare(List<RankedName> namesList, RankedName[] namesArr) {
        Object[] arr = namesList.toArray();
        
        for (int i = 0; i < namesArr.length; i++) {
            assert arr[i].equals(namesArr[i]) : "Values: " + 
                arr[i] + " != " + namesArr[i];
        }
    }
    
    public void masonShouldBeRankTwoIn2012() {
        ListByYear listYear = new ListByYear();
        
        listYear.loadListForYear(2012);
        int rank = listYear.getRank(2012, "Mason", Gender.MALE);
        
        assert rank == 2;
        
        rank = listYear.getRank(2012, "Mason", Gender.FEMALE);
        
        assert rank == -1;
    }
    
    public void nameShouldBeOliviaIn2012ForRank4() {
        ListByYear listYear = new ListByYear();
        
        listYear.loadListForYear(2012);
        
        RankedName name = listYear.getName(2012, 4, Gender.FEMALE);
        
        assert name.equals(new RankedName("Olivia", "F", 7));
    }
}
