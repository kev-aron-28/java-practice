public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> 0);
        
        Runnable test = () -> {
            threadId.set(threadId.get() + 1);
            System.out.println(
                Thread.currentThread().getName() + " " + threadId.get()
            );
        };
    }
}
