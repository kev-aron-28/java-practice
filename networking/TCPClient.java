
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 3000);

        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        output.println("Hello server");
        System.out.println("Server: " + input.readLine());

        socket.close();
    }
}
