package tests;
/**
 * Write a description of GenderTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import src.Gender;

public class GenderTester {
    public void performTests() {
        testFemaleGreaterThanMale();
        testMaleLessThanFemale();
        testGenderEquality();
        testGendersAreDifferent();
        
        System.out.println("Gender tests finished.");
    }
    
    public void testFemaleGreaterThanMale() {
        Gender female = Gender.FEMALE;
        Gender male = Gender.MALE;
        
        assert female.compareTo(male) < 0 : "Female should come first than Male";
    }
    
    public void testMaleLessThanFemale() {
        Gender female = Gender.FEMALE;
        Gender male = Gender.MALE;
        
        assert male.compareTo(female) > 0 : "Male should come after Female";
    }
    
    public void testGenderEquality() {
        Gender female = Gender.FEMALE;
        Gender male = Gender.MALE;
        
        assert female.equals(Gender.FEMALE);
        assert male.equals(Gender.MALE);
        assert female.compareTo(Gender.FEMALE) == 0;
        assert male.compareTo(Gender.MALE) == 0;
    }
    
    public void testGendersAreDifferent() {
        Gender female = Gender.FEMALE;
        Gender male = Gender.MALE;
        
        assert !female.equals(male);
        assert male.compareTo(female) != 0;
    }
}
