
import java.io.BufferedReader;
import java.io.FileReader;

public class WordCounter {
    public static void main(String args[]) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                count += line.split(" ").length;
            }

            System.out.println("Word counter: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }
}