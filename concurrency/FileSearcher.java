
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

class FileEnumerator implements Runnable {
    
    Path toSearch;
    BlockingQueue<Path> files;

    public FileEnumerator(Path toSearch, BlockingQueue<Path> files) {
        this.toSearch = toSearch;
        this.files = files;
    }

    @Override
    public void run() {
        try {
            enumerate(toSearch);
            files.put(FileSearcher.DUMMY);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }


    private void enumerate(Path toSearch) throws IOException, InterruptedException {
        try (Stream<Path> children = Files.list(toSearch)) {
            for(Path child : children.toList()) {
                if(Files.isDirectory(child)) {
                    enumerate(child);
                } else {
                    files.put(child);
                }
            }
        } 
    } 
}


class TextSearcher implements  Runnable {
    BlockingQueue<Path> files;

    public TextSearcher(BlockingQueue<Path> files) {
        this.files = files;
    }

    @Override
    public void run() {
        try () {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

public class FileSearcher {
    public static final Path DUMMY = Path.of("");
    
}
