import java.net.*;

public class IPExample {

	public static void main(String args[]) throws Exception {
		InetAddress address = InetAddress.getByName("www.google.com");
		System.out.println("Hostname: " + address.getHostName());

		System.out.println("IP address" + address.getHostAddress());
	}
}
