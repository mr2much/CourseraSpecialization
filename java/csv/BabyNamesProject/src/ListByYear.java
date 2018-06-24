package src;


/**
 * Write a description of ListByYear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ListByYear {
    public void loadListForYear(int year) {
    }
    
    public NamesList getListForYear(int year) {
        NamesList nl = new NamesList();
        
        nl.insert(new RankedName("Sophia", "F", 10));
        nl.insert(new RankedName("Emma", "F", 9));
        nl.insert(new RankedName("Isabella", "F", 8));
        nl.insert(new RankedName("Olivia", "F", 7));
        nl.insert(new RankedName("Ava", "F", 6));
        
        return nl;
    }
}
