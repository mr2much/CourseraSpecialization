
/**
 * Write a description of RankedName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RankedName implements Comparable<RankedName> {
    public String name;
    public String sex;
    public Integer count;
    
    public String getName() { return name; }
    
    public String getSex() { return sex; }
    
    public Integer getCount() { return count; }
    
    public RankedName(String name, String sex, Integer count) {
        this.name = name;
        this.sex = sex;
        this.count = count;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RankedName)) { return false; }
        
        RankedName other = (RankedName) o;
        
        if (this.count == other.getCount() && this.sex.equals(other.getSex())) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    @Override
    public int compareTo(RankedName otherName) {
        return this.count.compareTo(otherName.getCount());
    }
}
