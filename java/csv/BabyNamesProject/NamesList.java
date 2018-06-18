
/**
 * Write a description of NamesList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Iterator;

public class NamesList implements Iterable<Map.Entry<Gender, List<RankedName>>> {
    Map<Gender, List<RankedName>> namesMap;
    // List<RankedName> allNames;
    // List<RankedName> femaleNames;
    // List<RankedName> maleNames;
    
    {
        namesMap = new HashMap<>();
        // allNames = new ArrayList<>();
        // femaleNames = new ArrayList<>();
        // maleNames = new ArrayList<>();
    }
    
    // public List<RankedName> getFemaleNames() {
        // return femaleNames;
    // }
    
    // public List<RankedName> getMaleNames() {
        // return maleNames;
    // }
    
    public void insert(RankedName rankedName) {
        List<RankedName> list = getList(rankedName.getGender());
        
        list.add(rankedName);
        Collections.sort(list);
        
        namesMap.put(rankedName.getGender(), list);
    }
    
    public List<RankedName> getList(Gender gender) {
        List<RankedName> list = namesMap.get(gender);
        
        if (list == null) {
            return new ArrayList<RankedName>();
        }
        
        return list;
    }
    
    public RankedName getNameByRank(int rank, Gender gender) {
        List<RankedName> listToUse = namesMap.get(gender);
        
        RankedName name = null;
        if (listToUse != null) {
            name = listToUse.get(rank - 1);
        }
        
        return name;
    }
    
    public int rankByName(String name, Gender gender) {
        List<RankedName> listToUse = namesMap.get(gender);
                
        return getRank(listToUse, name);
    }
    
    private int getRank(List<RankedName> names, String name) {
        
        if (names == null) {
            return -1;
        }
        
        for (int i = 0; i < names.size(); i++) {
            RankedName rankedName = names.get(i);
            if (rankedName.getName().equals(name)) {
                return i + 1;
            }
        }
        
        return -1;
    }
    
    public int totalFemales() {
        List<RankedName> femaleList = namesMap.get(Gender.FEMALE);
        
        if(femaleList == null) {
            femaleList = new ArrayList<>();
        }
        
        return femaleList.size();
    }
    
    public int totalMales() {
        List<RankedName> maleList = namesMap.get(Gender.MALE);
        
        if (maleList == null) {
            maleList = new ArrayList<>();
        }
        
        return maleList.size();
    }
    
    public int totalBirths() {
        return totalFemales() + totalMales();
    }
    
    @Override
    public Iterator<Map.Entry<Gender, List<RankedName>>> iterator() {
        Iterator<Map.Entry<Gender, List<RankedName>>> it = namesMap.entrySet().iterator();
        return it;
    }
    
    public List<RankedName> getAll(Gender gender) {
        if (namesMap.get(gender) != null) {
            return namesMap.get(gender);
        }
        
        return new ArrayList<RankedName>();
    }
}
