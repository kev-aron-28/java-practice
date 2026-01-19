
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String args[]) throws IOException {
        if(args.length == 0) {
            throw new Error("You must provide the file");
        }
        
        String input = args[0];
        String output = "copy.txt";
        
        copyFile(input, output);
        analyzeFile(input);

    }

    public static void copyFile(String input, String output) {
        try (
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(input));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(output));
        ) {
            byte [] buffer = new byte[1024];
            int bytesRead;
            
            while((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("File copied");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void analyzeFile(String input) {
        int lines = 0, words = 0, chars = 0;
        try (
            BufferedReader reader = new BufferedReader(new FileReader(input))
        ){
            String line;
            while((line = reader.readLine()) != null) {
                lines++;
                chars += line.length();
                words += line.split("\\s+").length;
            }

            System.out.println(lines + " " + words + " " + chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


