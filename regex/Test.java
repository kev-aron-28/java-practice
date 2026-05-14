package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        // Exact match
        Pattern p1 = Pattern.compile("dog");
        Matcher m1 = p1.matcher("cat dog bird");

        while (m1.find()) { 
            System.out.println(m1.group());
        }

        // Find all numbers

        Pattern p2 = Pattern.compile("\\d+");
        Matcher m2 = p2.matcher("21 2025 14");

        while (m2.find()) { 
           System.out.println(m2.group()); 
        }

        // Match only full numbers
        Pattern p3 = Pattern.compile("\\b\\d+\\b");
        Matcher m3 = p3.matcher("abc123 12355234 dk334");
        
        
        while (m3.find()) { 
            System.out.println(m3.group());
        }
    }
}
