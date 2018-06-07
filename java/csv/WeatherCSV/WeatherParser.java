
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
import java.util.Iterator;
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
        //FileResource fr = new FileResource();
        //CSVParser parser = getCSVParser(fr);
        
        //nullTest(fr, parser);
        
        // TODO: read all files in path using DirectoryResource()
        // TODO: get average records of all files
        // TODO: test if record count is lower than average for a given file
        
        //testRecordCount(parser.getRecords());
        testAverages();
        
        testMaxTemp();
        //testMaxTempInManyDays();
        testColdestHourInFile();
        testFileWithColdestTemperature();
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
            maxRecord = getLargestOfTwo(record, maxRecord, max);
            max = getFloatValueFrom(maxRecord, "TemperatureF");
        }
        
        return maxRecord;
    }
    
    private CSVRecord getLargestOfTwo(CSVRecord currentRecord, 
        CSVRecord maxRecord, float currentMax) {
        if(maxRecord == null) {
            maxRecord = currentRecord;
        } else {
            float currentTemp = getFloatValueFrom(currentRecord, "TemperatureF");
            
            if(currentTemp > currentMax) {
                maxRecord = currentRecord;
            }
        }
            
        return maxRecord;
    }
    
    public float getFloatValueFrom(CSVRecord record, String searchValue) {
        float result = 0.0f;
        
        String tempStr = record.get(searchValue);
        try {
            result = Float.parseFloat(tempStr);
        } catch (NumberFormatException e) {
            result = Float.NaN;
        }
        
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
        float max = 0.0f;
        for(File f : dr.selectedFiles()) {
            FileResource fr = getFileResourceFor(f.toString());
            
            CSVRecord currentRecord = getMaxTemp(fr.getCSVParser());
            maxRecord = getLargestOfTwo(currentRecord, maxRecord, max);
            max = getFloatValueFrom(maxRecord, "TemperatureF");
        }
        
        return maxRecord;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = getFileResourceFor(
            Paths.get("nc_weather/2014/weather-2014-01-03.csv").toString());
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        float value = getFloatValueFrom(coldestRecord, "TemperatureF");
        
        if (value > 21.9) {
            System.out.println("Coldest day should be 35.1, got: " + value);
        }
        
        System.out.println("Coldest temperature on that day was " + coldestRecord.get("TemperatureF") + " on " +
            coldestRecord.get("DateUTC"));
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        float lowestTemp = 9999.0f;
        
        for(CSVRecord currentRecord : parser) {
            coldest = getLowestOfTwo(currentRecord, coldest, lowestTemp, "TemperatureF");
            lowestTemp = getFloatValueFrom(coldest, "TemperatureF");
            
            if (lowestTemp == -9999) {
                lowestTemp = 9999.0f;
            }
        }
        
        return coldest;
    }
    
    public CSVRecord getLowestOfTwo(CSVRecord currentRecord, 
        CSVRecord lowestRecord, float currentLowest, String searchValue) {
        if(lowestRecord == null) {
            lowestRecord = currentRecord;
        } else {
            float currentTemp = getFloatValueFrom(currentRecord, searchValue);
            
            if(currentTemp < currentLowest) {
                lowestRecord = currentRecord;
            }
        }
            
        return lowestRecord;
    }
    
    public void testFileWithColdestTemperature() {
        String filename = fileWithColdestTemperature();
        
        if (!filename.equals("weather-2014-01-03.csv")) {
            System.out.println("Filename should be 'weather-2014-01-03.csv' but got " + filename);
        } else {
            System.out.println("Coldest day was in file " + filename);
            FileResource fr = getFileResourceFor(
                Paths.get("nc_weather/2014", filename).toString());
            CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
            System.out.println("Coldest temperature on that day was " + lowest.get("TemperatureF"));
            
            System.out.println("All the temperatures on the coldest day were: ");
            for(CSVRecord record : fr.getCSVParser()) {
                System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
            }
        }
    }
    
    public String fileWithColdestTemperature() {
        String filename = "";
        CSVRecord minRecord = null;
        DirectoryResource dr = new DirectoryResource();
        float minTemp = 9999.0f;
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = getFileResourceFor(f.toString());
            CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());
            minRecord = getLowestOfTwo(currentRecord, minRecord, minTemp, "TemperatureF");
            
            if (getFloatValueFrom(minRecord, "TemperatureF") < minTemp) {
                filename = f.getName();
                minTemp = getFloatValueFrom(minRecord, "TemperatureF");
            }
        }
        
        return filename;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        float lowestHumidity = 9999.0f;
        
        for (CSVRecord currentRecord : parser) {
            lowest = getLowestOfTwo(currentRecord, lowest, lowestHumidity, "Humidity");
            lowestHumidity = getFloatValueFrom(lowest, "Humidity");
        }
        
        return lowest;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestRecord = null;
        
        float minHumidity = 9999.0f;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f.toString());
            CSVRecord currentRecord = lowestHumidityInFile(fr.getCSVParser());
            lowestRecord = getLowestOfTwo(currentRecord, lowestRecord, minHumidity, "Humidity");
            minHumidity = getFloatValueFrom(lowestRecord, "Humidity");
        }
        
        return lowestRecord;
    }
}
