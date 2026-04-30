package interview.interview-6;

public class ManagedResource implements AutoCloseable {
    private final String name;

    public ManagedResource(String name) {
        this.name = name;
    }

    public void use() throws Exception {
        System.out.println("Using " + name);

        int min = 1;
        int max = 10;
        int range = max - min + 1;
        
        int random = (int)(Math.random() * range) + min;

        if(random > 5) {
            throw new Exception("EXCEPTION");
        }
    
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing " + name);

        int min = 1;
        int max = 10;
        int range = max - min + 1;
        
        int random = (int)(Math.random() * range) + min;

        if(random < 5) {
            throw new Exception("EXECEPTION CLOSING...");
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

}
