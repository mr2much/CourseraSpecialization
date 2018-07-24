package src;

import edu.duke.*;
import java.util.*;

public class GladLib {
    Map<String, List<String>> myMap;

    private ArrayList<String> usedWords;
    private int replacedWords;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    private static final String[] categories = {"adjective", "noun", "color",
        "country", "name", "animal", "timeframe", "verb", "fruit"};
    
    public GladLib(){
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        for (String category : categories) {
            myMap.put(category, readIt(String.format("%s/%s.txt",
                source, category)));
        }
        
        usedWords = new ArrayList<>();
    }
    
    private String randomFrom(List<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        replacedWords++;
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
        
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        while (usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first+1, last));
        }
        
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory() {
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        System.out.println(replacedWords + " words were harmed when creating this story.\n");
        printOut(story, 60);
        
        System.out.println("\n\nThe total words that were available to pick" +
            " from to create this story was " + totalWordsInMap());
    }
    
    public int totalWordsInMap() {
        int totalWords = 0;
        
        for (String key : myMap.keySet()) {
            for (String word : myMap.get(key)) {
                totalWords++;
            }
        }
        
        return totalWords;
    }
    
}
