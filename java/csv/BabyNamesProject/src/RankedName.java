package src;
/**
 * Write a description of RankedName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import src.Gender;

public class RankedName implements Comparable<RankedName> {
    private String name;
    private Gender gender;
    private int count;
    
    public String getName() { return name; }
    
    public void setGender(String sex) {
        if (sex.startsWith("F")) {
            this.gender = Gender.FEMALE;
        } else if (sex.startsWith("M")) {
            this.gender = Gender.MALE;
        }
    }
    
    public Gender getGender() { return gender; }
    
    public int getCount() { return count; }
    
    public RankedName(String name, String sex, int count) {
        this.name = name;
        this.setGender(sex);
        this.count = count;
    }
    
    @Override
    public String toString() {
        return name + " " + gender + " " + count;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        
        RankedName other = (RankedName) o;
        
        if (this.name.equals(other.getName()) && 
            this.count == other.getCount() && 
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
        
        return (this.count > otherName.getCount()) ? -1 :
            this.count == otherName.getCount() ? 0 : 1;
    }
    
    private boolean sexAreEqual(Gender other) {
        return this.gender == other;
    }
}
