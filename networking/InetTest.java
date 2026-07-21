
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetTest {
    public static void main(String[] args) throws UnknownHostException {
        if(args.length > 0) {
            InetAddress[] address = InetAddress.getAllByName(args[0]);
            for(InetAddress addr : address) {
                System.out.println(addr);
            }
        } else {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println(localhost);
        }
    }
}
