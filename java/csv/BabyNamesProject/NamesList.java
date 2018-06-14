
/**
 * Write a description of NamesList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
import java.util.ArrayList;

public class NamesList {
    List<RankedName> femaleNames;
    List<RankedName> maleNames;
    
    {
        femaleNames = new ArrayList<>();
        maleNames = new ArrayList<>();
    }
    
    public List<RankedName> getFemaleNames() {
        return femaleNames;
    }
    
    public List<RankedName> getMaleNames() {
        return maleNames;
    }
    
    public void insert(RankedName rankedName) {
        if (rankedName.getGender() == Gender.FEMALE) {
            femaleNames.add(rankedName);
        } else {
            maleNames.add(rankedName);
        }
    }
    
    public int totalFemales() {
        return femaleNames.size();
    }
    
    public int totalMales() {
        return maleNames.size();
    }
    
    public int totalBirths() {
        return totalFemales() + totalMales();
    }
}
