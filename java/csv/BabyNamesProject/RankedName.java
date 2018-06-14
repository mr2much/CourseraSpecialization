
/**
 * Write a description of RankedName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RankedName implements Comparable<RankedName> {
    private String name;
    private Gender gender;
    private Integer count;
    
    public String getName() { return name; }
    
    public void setGender(String sex) {
        if (sex.startsWith("F")) {
            this.gender = Gender.FEMALE;
        } else if (sex.startsWith("M")) {
            this.gender = Gender.MALE;
        }
    }
    
    public Gender getGender() { return gender; }
    
    public Integer getCount() { return count; }
    
    public RankedName(String name, String sex, Integer count) {
        this.name = name;
        // TODO: setSex(Strgin sex);
        this.setGender(sex);
        this.count = count;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RankedName)) { return false; }
        
        RankedName other = (RankedName) o;
        
        if (this.count == other.getCount() && 
            this.gender.equals(other.getGender())) {
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
        Gender otherGender = otherName.getGender();
        
        if(!sexAreEqual(otherGender)) {
            return this.gender.compareTo(otherGender);
        }
        
        return this.count.compareTo(otherName.getCount());
    }
    
    private boolean sexAreEqual(Gender other) {
        return this.gender == other;
    }
}
