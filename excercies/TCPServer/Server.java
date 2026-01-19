package excercies.TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(3000);

        while(true) {
            Socket client = server.accept();

            System.out.println("Client connected");

            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                client.getOutputStream(),
                true
            );

            String line;

            while((line = in.readLine()) != null) {
                System.out.println(line);
            }
            
            client.close();
        }
        
    }
}
