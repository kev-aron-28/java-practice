import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryManager {
    private Map<String, Product> inventory = new HashMap<>();
    private static final String FILE = "inv.csv";

    public InventoryManager() {
        loadFromFile();
    }

    private void saveToFile() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE))) {
            for(Product p : inventory.values()) {
                bw.write(p.toCSV());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void loadFromFile() {
        Path path = Paths.get(FILE);
        if(!Files.exists(path)) return;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            inventory = br.lines()
                .map(Product::fromCSV)
                .collect(Collectors.toMap(Product::getCode, p -> p));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
