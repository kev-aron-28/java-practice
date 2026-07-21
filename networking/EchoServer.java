
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        int port = args.length >= 1 ? Integer.parseInt(args[0]) : 8189;

        try (
            var s = new ServerSocket(port);
            Socket incoming = s.accept()
        ) {
            server(incoming);
        }
    }

    public static void server(Socket incoming) throws IOException {
        try (
            var in = new Scanner(incoming.getInputStream());
            var out = new PrintWriter(incoming.getOutputStream(), true)
        ) {
            out.println("Hello! Enter BYE to exit");

            boolean done = false;

            while (!done && in.hasNextLine()) { 
                String line = in.nextLine();

                out.println("ECHO: " + line);

                if(line.strip().equals("BYE")) done = true;
            }

        }
    }
}
