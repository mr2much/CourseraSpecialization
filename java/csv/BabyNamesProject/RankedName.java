
/**
 * Write a description of RankedName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RankedName {
    public String name;
    public String sex;
    public int count;
    
    public String getName() { return name; }
    
    public String getSex() { return sex; }
    
    public int getCount() { return count; }
    
    public RankedName(String name, String sex, int count) {
        this.name = name;
        this.sex = sex;
        this.count = count;
    }
    
    @Override
    public boolean equals(Object o) {
        RankedName other = (RankedName) o;
        
        if (this.count == other.getCount() && this.sex.equals(other.getSex())) {
            return true;
        }
        
        return false;
    }
}
