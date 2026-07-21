
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServerThread {
    public static void main(String[] args) throws IOException {
        try (var s = new ServerSocket(8189)) {
            ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
            
            while (true) { 
                Socket incoming = s.accept();

                System.out.println("NEW CONNECTION");

                service.submit(() -> serve(incoming));
            }

        }
    }

    public static void serve(Socket incoming) {
        try(
            var in = new Scanner(incoming.getInputStream());
            var out = new PrintWriter(incoming.getOutputStream())
        ) {
            out.println("Hello! Enter BYE to exit");

            boolean done = false;

            while(!done && in.hasNextLine()) {
                String line = in.nextLine();

                System.out.println("ECHO: " + line);

                if(line.strip().equals("BYE")) done = true;
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
