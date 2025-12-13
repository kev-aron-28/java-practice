class ContadorCompartido {
    public int contador = 0;

    public synchronized  void increment() {
        contador++;
    }
}

class MiHilo implements Runnable {
    private ContadorCompartido c;

    public MiHilo(ContadorCompartido c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            c.increment();
        }
    }
}

public class Ex3 {
    public static void main(String[] args) {
        ContadorCompartido c = new ContadorCompartido();

        Thread h1 = new Thread(new MiHilo(c));
        Thread h2 = new Thread(new MiHilo(c));

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + c.contador);
    }
}
