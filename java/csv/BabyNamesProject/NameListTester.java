
/**
 * Write a description of NameListTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.Map;
import java.util.Iterator;
public class NameListTester {
    public void performTests() {
        testInsertName();
        getRankByNameShouldReturnNegativeOneOnEmptyListOrNameNotFound();
        testRankByName();
        shouldThrowExceptionIfAllGenderAndNamesAreNotPresent();
        shouldThrowExceptionIfNamesAreNotAllOfTheSameSex();
        getNameByRankShouldReturnNullOnEmptyListOrNameNotFound();
        getNameByRankShouldReturnNameRepresentedByGivenRank();
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
    
    public void getRankByNameShouldReturnNegativeOneOnEmptyListOrNameNotFound() {
        NamesList namesList = new NamesList();
        
        int rank = namesList.rankByName("Anubis", Gender.MALE);
        
        assert rank == -1;
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
        
        assert rank == 4 : "Expected rank == 4, got " + rank;
        
        rank = namesList.rankByName("Grace", Gender.FEMALE);
        
        assert rank == 1;
        
        rank = namesList.rankByName("Clara", Gender.FEMALE);
        
        assert rank == -1;
        
        rank = namesList.rankByName("Anubis", Gender.MALE);
        
        assert rank == 1;
        
        RankedName bob = new RankedName("Bob", "M", 10);
        
        namesList.insert(bob);
        rank = namesList.rankByName("Bob", Gender.MALE);
        
        assert rank == 1 : "Expected 1, got: " + rank;
    }
    
    public void shouldThrowExceptionIfAllGenderAndNamesAreNotPresent() {
        NamesList namesList = new NamesList();
        
        RankedName jennifer = new RankedName("Jennifer", "F", 10);
        RankedName carla = new RankedName("Carla", "F", 5);
        RankedName anubis = new RankedName("Anubis", "M", 10);
        RankedName bob = new RankedName("Bob", "M", 5);
        
        namesList.insert(jennifer);
        namesList.insert(carla);
        namesList.insert(anubis);
        namesList.insert(bob);
        
        Iterator<Map.Entry<Gender, List<RankedName>>> entries = 
            namesList.iterator();
        Map.Entry<Gender, List<RankedName>> entry = entries.next();
        
        assert entry.getKey() == Gender.FEMALE;
        
        compare(entry.getValue(), new RankedName[]{jennifer, carla});
        
        entry = entries.next();
        
        assert entry.getKey() == Gender.MALE;
        
        compare(entry.getValue(), new RankedName[] {anubis, bob});
    }
    
    public void shouldThrowExceptionIfNamesAreNotAllOfTheSameSex() {
        NamesList namesList = new NamesList();
        
        RankedName jennifer = new RankedName("Jennifer", "F", 10);
        RankedName grace = new RankedName("Grace", "F", 9);
        RankedName anubis = new RankedName("Anubis", "M", 10);
        
        namesList.insert(jennifer);
        namesList.insert(grace);
        namesList.insert(anubis);
        
        List<RankedName> names = namesList.getAll(Gender.FEMALE);
        
        compare(names, new RankedName[] {new RankedName("Jennifer", "F", 10),
            new RankedName("Grace", "F", 9)});
    }
    
    private void compare(List<RankedName> namesList, RankedName[] namesArr) {
        Object[] arr = namesList.toArray();
        
        for (int i = 0; i < arr.length; i++) {
            assert arr[i].equals(namesArr[i]);
        }
    }
    
    public void getNameByRankShouldReturnNullOnEmptyListOrNameNotFound() {
        NamesList namesList = new NamesList();
        RankedName anubis = namesList.getNameByRank(1, Gender.MALE);
        
        assert anubis == null;
    }
    
    public void getNameByRankShouldReturnNameRepresentedByGivenRank() {
        NamesList namesList = new NamesList();
        
        RankedName anubis = new RankedName("Anubis", "M", 10);
        RankedName bob = new RankedName("Bob", "M", 5);
        RankedName richard = new RankedName("Richard", "M", 1);
        
        namesList.insert(richard);
        namesList.insert(anubis);
        namesList.insert(bob);
        
        RankedName name = namesList.getNameByRank(3, Gender.MALE);
        
        assert name.equals(richard);
        
        name = namesList.getNameByRank(1, Gender.MALE);
        
        assert name.equals(anubis);
    }
}
