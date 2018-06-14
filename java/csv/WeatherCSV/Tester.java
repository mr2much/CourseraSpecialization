
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.nio.file.Paths;
import java.io.File;

public class Tester {
    WeatherParser weatherParser;
    
    {
        weatherParser = new WeatherParser();
    }
    
    public void performTests() {
        testLowestHumidityInFile();
        testLowestHumidityInManyFiles();
        testAverageTemperatureInFile();
        testAverageTemperatureWithHighHumidityInFile();
        System.out.println("Tests finished.");
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource(Paths.get("nc_weather", "2014", "weather-2014-07-22.csv").toString());
        CSVRecord lowestHumidityRecord = weatherParser.lowestHumidityInFile(fr.getCSVParser());
        
        float value = weatherParser.getFloatValueFrom(lowestHumidityRecord, "Humidity");
        
        System.out.println("Lowest Humidity was " + value + " at " + lowestHumidityRecord.get("DateUTC"));
        assert value == 24 : "Expected value 24, got " + value;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = weatherParser.lowestHumidityInManyFiles();
        
        float value = weatherParser.getFloatValueFrom(lowest, "Humidity");
        System.out.println("Lowest Humidity was " + value + " at " + lowest.get("DateUTC"));
        
        assert value == 24 : "Expected value of many files to be 24, got " + value;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource(Paths.get("nc_weather", "2013", "weather-2013-08-10.csv").toString());
        double avrgTemp = weatherParser.getAverageTemp(fr.getCSVParser());

        System.out.println("Average temperature in file is " + avrgTemp);
        assert avrgTemp == 44.93333333333334 : "Expected 44.93333333333334, got " + avrgTemp;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource(Paths.get("nc_weather", "2013", "weather-2013-09-02.csv").toString());
        double value = weatherParser.averageTemperatureWithinHighHumidityFile(fr.getCSVParser(), 80);
        
        System.out.println("Average Temp when high Humidity is " + value);
        
        assert value == 0.0;
        
        if (value == 0.0) {
            System.out.println("No temperatures with that humidity");
        }
        
        fr = new FileResource(Paths.get("nc_weather", "2014", "weather-2014-03-20.csv").toString());
        value = weatherParser.averageTemperatureWithinHighHumidityFile(fr.getCSVParser(), 80);
        
        assert value == 41.78666666666667 : "Expected 41.78666666666667, got " + value;
        
        System.out.println("Average Temp when high Humidity is " + value);
    }
}
