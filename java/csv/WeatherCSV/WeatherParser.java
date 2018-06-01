
/**
 * Write a description of WeatherParser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.List;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;

public class WeatherParser {
    public FileResource getEmptyFileResource() {
        return new FileResource();
    }
    
    public FileResource getFileResourceFor(String filename) {
        return new FileResource(filename);
    }
    
    public CSVParser getCSVParser(FileResource resource) {
        if (resource != null) {
            return resource.getCSVParser();
        }
        
        return null;
    }
    
    public void test() throws IOException {
        FileResource fr = new FileResource();
        CSVParser parser = getCSVParser(fr);
        
        nullTest(fr, parser);
        
        // TODO: read all files in path using DirectoryResource()
        // TODO: get average records of all files
        // TODO: test if record count is lower than average for a given file
        
        testRecordCount(parser.getRecords());
        testAverages();
        
        testMaxTemp();
        testMaxTempInManyDays();
        
        System.out.println("Tests finished.");
    }
    
    private void nullTest(FileResource fr, CSVParser parser) {
        testNotNull(fr);
        testNotNull(parser);
        
        System.out.println("Finished null testing.");
    }
    
    public void testNotNull(Object obj) {
        if(obj == null) {
            System.out.println(obj.getClass().getSimpleName() + " is null.");
        }
    }
    
    public void testRecordCount(List<CSVRecord> records) {
        if(records == null) {
            System.out.println("No list.");
        }
        
        if (records.size() == 0) {
            System.out.println("No data to read.");
        }
        
        if (records.size() < 23) {
            System.out.println("Possible error, number of records is " +
                records.size());
        }
        
        System.out.println("Finished counting records.");
    }
    
    public void testAverages() {
        FileResource fr = getFileResourceFor(
            Paths.get("nc_weather/2012/weather-2012-01-01.csv").toString());
        float value = getAverageTemp(fr.getCSVParser());
        
        if (value < 52.61) {
            System.out.println("Expected 52.61 but got " + value);
        }
        
        value = getAverageTemp(null);
        
        if (value != 0.0f) {
            System.out.println("Value should be 0.0, got " + value);
        }
        
        System.out.println("Finished testing average temperature.");
    }
    
    public float getAverageTemp(CSVParser parser) {
        
        if (parser == null) {
            return 0.0f;
        }
        
        int recordCount = 0;
        float tempTotal = 0.0f;
        
        for (CSVRecord record : parser) {
            String temp = record.get("TemperatureF");
            
            float t = Float.parseFloat(temp);
            
            tempTotal += t;
            
            recordCount++;
        }
        
        return tempTotal / recordCount;
    }
    
    public void testMaxTemp() {
        FileResource fr = getFileResourceFor(
            Paths.get("nc_weather/2012/weather-2012-01-01.csv").toString());
        CSVRecord max = getMaxTemp(fr.getCSVParser());
        float value = Float.parseFloat(max.get("TemperatureF"));
        
        if (value < 66.9) {
            System.out.println("Expected 66.9 but got " + value);
        } else {
            System.out.println("The hottest temp was: " + value + " at " +
                max.get("TimeEST"));
        }
        
        max = getMaxTemp(null);
        
        if (max != null) {
            System.out.println("Expected 0.0f got " + value);
        }
        
        System.out.println("Finished testing max temperature");
    }
    
    public CSVRecord getMaxTemp(CSVParser parser) {
        
        if (parser == null) {
            return null;
        }
        
        CSVRecord maxRecord = null;
        
        float max = 0.0f;
        
        for (CSVRecord record : parser) {
            String tempStr = record.get("TemperatureF");
            float currentTemp = getTempFrom(record);
            
            if (currentTemp > max) {
                max = currentTemp;
                maxRecord = record;
            }
        }
        
        return maxRecord;
    }
    
    private float getTempFrom(CSVRecord record) {
        float result = 0.0f;
        
        String tempStr = record.get("TemperatureF");
        result = Float.parseFloat(tempStr);
        
        return result;
    }
    
    public void testMaxTempInManyDays() {
        CSVRecord max = getMaxTempInManyDays();
        
        System.out.println("Max temperature was " + max.get("TemperatureF") +
            " at " + max.get("DateUTC"));
    }
    
    public CSVRecord getMaxTempInManyDays() {
        CSVRecord maxRecord = null;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRecord = getMaxTemp(fr.getCSVParser());
            
            if(maxRecord == null) {
                maxRecord = currentRecord;
            } else {
                float currentMax = getTempFrom(maxRecord);
                float currentTemp = getTempFrom(currentRecord);
                
                if(currentTemp > currentMax) {
                    maxRecord = currentRecord;
                }
            }
        }
        
        return maxRecord;
    }
}
