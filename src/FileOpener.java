import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOpener {
    public static void main(String[] args) {
        // TODO -> SECOND REGEX USING regex101.com -> ^\d{1,}\s{3}(.*?)(\d+)\s(ks|m)s?\s\d{1,}.\d{3}\s\d+.\d{1,}\s\d{1,}\%\s\d{1,}.\d{1,}$
        // TODO -> FIRST REGEX USING regex101.com -> ^\d{1,}\s{3}(.*?)$
        String filePath = "src/data.txt";
        Pattern firstPattern = Pattern.compile("^(\\d+)\\s{3}((.*?))$", Pattern.CASE_INSENSITIVE);
        Pattern secondPattern = Pattern.compile("^(\\d+)\\s{3}(.*?)\\s(\\d+\\s(?:ks|m))\\s(\\d+\\.\\d{3})\\s(\\d+\\.\\d{2})\\s(\\d+%)\\s(\\d+\\.\\d{2})$", Pattern.CASE_INSENSITIVE);
        List<String> extractedParts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher secondMatcher = secondPattern.matcher(line);
                Matcher firstMatcher = firstPattern.matcher(line);
                if(secondMatcher.find()){
                    extractedParts.add("Riadok: " + secondMatcher.group(1));
                    extractedParts.add("Nazov: " +secondMatcher.group(2));
                    extractedParts.add("Mnozstvo: "+ secondMatcher.group(3));
                    extractedParts.add("Jednotkova cena: " + secondMatcher.group(4));
                    extractedParts.add("Cena spolu: " + secondMatcher.group(5));
                    extractedParts.add("DPH: " +secondMatcher.group(6));
                    extractedParts.add("Spolu s DPH: " + secondMatcher.group(7));
                    extractedParts.add("\n");
                } else if(firstMatcher.find()){
                    extractedParts.add("Riadok: " + firstMatcher.group(1));
                    extractedParts.add("Text: " + firstMatcher.group(2));
                    extractedParts.add("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String part : extractedParts) {
            System.out.println(part);
        }
    }
}