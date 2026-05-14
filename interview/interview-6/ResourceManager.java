package interview.interview-6;

public class ResourceManager {
    public static void useResource(ManagedResource resource) throws Exception {
        Exception main = null;
        
        try {
            resource.use();
        } catch(Exception ex) {
            main = ex;
        } finally {
            try {
                resource.close();
            } catch (Exception closeEx) {
                if(main != null) {
                    main.addSuppressed(closeEx);
                } else {
                    throw closeEx;
                }
            }
        }

        if(main != null) {
            throw main;
        }
    }


}
