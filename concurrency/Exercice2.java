class MyThread extends Thread {
    private String id;

    public MyThread(String id) {
        this.id = id;
    }
 
    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Soy el hilo " + id);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}


public class Exercice2 {

    public static void main(String[] args) {
        MyThread thread1 = new MyThread("1");
        MyThread thread2 = new MyThread("2");
        
        thread1.start();
        thread2.start();
    }
}
