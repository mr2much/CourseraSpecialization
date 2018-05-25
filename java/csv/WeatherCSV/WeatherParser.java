
/**
 * Write a description of WeatherParser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.List;
import java.io.IOException;

public class WeatherParser {
    public FileResource getEmptyFileResource() {
        return new FileResource();
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
    }
}
