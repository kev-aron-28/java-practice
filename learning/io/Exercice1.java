
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Exercice1 {
    public static void main(String[] args) {
        
    }

    public static String readAll(String filename) throws IOException {
        Path path = Paths.get(filename);

        String content = Files.readString(path);

        return content;
    }

    public static void writeText(String filename, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(content);
        }
    }

    public static void copyFile(String src, String dest) {
        try (BufferedInputStream bi = new BufferedInputStream(new FileInputStream(src));
            BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(dest))
        ) {
            byte buffer[] = new byte[4096];
            int bytesRead;

            while((bytesRead = bi.read(buffer)) != -1 ) {
                bo.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
