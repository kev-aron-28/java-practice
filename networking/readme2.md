# Networking

In network parlance, a port is not a physical device, but an abstraction facilitating communication between a server and a client

## Connecting to a server with Java
The next program will do the same you could do with telnet

``` java
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) {
        try (
            var s = new Socket("time-a.nist.gov", 13);
            var in = new Scanner(s.getInputStream())
        ) {
            while(in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

The first line opens a socket, which is a network software abstraction that enables commucation out of and into this program
The getInputStream method in Java returns an InputStream object that you can use just like any other stream and thrwos an IOException if it is not possible
to read from the socket

``` java
Socket(String host, int port): constructs a socket to connect to the given host and port.
InputStream getInputStream()
OutputStream getOutputStream(): get the stream to read data from the socket or write data to the sockt
```

## Socket timeouts
Reading from a socket blocks until data is available. IF the host is unreachable, your application waits for long time and you rre
at the mercy of the underlying OS to eventually timeout

You can decide what timeout value is reasonable for your particula application. Then call the setSoTimeout method 
to set a timeout value in millis

``` java
var s = new Socket();
s.setSoTimeout(1000)
``` 

if the timeout value has been set for a socket, all subsequent read operatiosn throw SocketTimeoutException

There is no timeout for write operations

There is one additional timeout issue that you need to address, The constructor

```
Socket(String host, int port)
```

This can block indefinitely until an initial connection to the host is established, you can overcome this problem by first
constructing an unconnected socket and then connecting it with a timeout

``` java
var s = new Socket();
s.connect(new InetSocketAddress(host, port), timeout);
```

## Internet addresses
You can use the InetAddress class if you need to convert between host names and Internet Addresses. 

``` java
InetAddress address = InetAddress.getByName("timea.nist.gov");
```

YOu can get all hosts with the getAllByName method

``` java
InetAddress[] addresses = InetAddress.getAllByName(host);
```

Finally you sometimes need the address of the localhost, if you simply ask for the address of localhost, you always get the local loopback address 127.0.0.1

``` java
InetAddress address = InetAddress.getLocalHost();
```

# Implementing servers

## Server sockets

A server program, when started waits for a client to attach to its port, the ServerSocket class establishes a socket

``` java
var s = new ServerSocket(8189)
```

now, the command: 

``` java
Socket incoming = s.accept();
```

tells the program to wait indefenitely until a client connects to that port. Once someone connects to this port by
sending the correct request over the network, this method returns a Socket object that represents the connection
that was made

``` java
InputStream inStream = incoming.getInputStream();
OutputStream outStream = incoming.getOutputStream();
```

Everything that the server sends to the server output stream becomes the input of the client program, and all the
output from the client program ends up in the server input stream

# Half-close
A half-close allows one direction of a TCP connection to be closed while keeping the other direction open.

Normally, when you close a socket, both sending and receiving stop.

With a half-close:

- You can stop sending data.
- You can continue receiving data.

Indicate "Im done sending, but i am waiting for your response"