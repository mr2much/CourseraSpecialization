
/**
 * Write a description of ExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ExportData {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            
            if(exports.contains(exportOfInterest)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public String countryInfo(CSVParser parser, String country) {
        String result = country + ": NOT FOUND!";
        
        for (CSVRecord record : parser) {
            String cntry = record.get("Country");
            
            if (cntry.contains(country)) {
                return new StringBuilder().append(country).append(": ")
                    .append(record.get("Exports")).append(": ")
                    .append(record.get("Value (dollars)")).toString(); 
            }
        }
        
        return result;
    }
    
    public void test() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // listExporters(fr.getCSVParser(), "coffee");
        // System.out.println(countryInfo(fr.getCSVParser(), "China"));
        // System.out.println(countryInfo(fr.getCSVParser(), "Germany"));
        // System.out.println(countryInfo(fr.getCSVParser(), "United States"));
        // System.out.println(countryInfo(fr.getCSVParser(), "Nauru"));
        // listExportersTwoProducts(fr.getCSVParser(), "cotton", "flowers");
        // listExportersTwoProducts(fr.getCSVParser(), "fish", "nuts");
        // System.out.println(numberOfExporters(fr.getCSVParser(), "cocoa"));
        // System.out.println(numberOfExporters(fr.getCSVParser(), "sugar"));
        // bigExporters(fr.getCSVParser(), "$999,999,999");
        bigExporters(fr.getCSVParser(), "$999,999,999,999");
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,
        String exportItem2) {
            for(CSVRecord record : parser) {
                String export = record.get("Exports");
                
                if(export.contains(exportItem1) && 
                    export.contains(exportItem2)) {
                        System.out.println(record.get("Country"));
                }
            }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int result = 0;
        
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            
            if (export.contains(exportItem)) {
                result++;
            }
        }
        
        return result;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            
            if(value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " +
                    value);
            }
        }
    }
}
