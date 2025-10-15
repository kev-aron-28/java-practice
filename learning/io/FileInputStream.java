import java.io.*;

public class FileInputStream {
    public static void main(String[] args) throws IOException {
        try (java.io.FileInputStream in = new java.io.FileInputStream("readme.md")){
            int b;

            while((b = in.read()) != -1) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}