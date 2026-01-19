# Networking

Java has built-in networking capabilities through the **java.net** package
It allows comm between programs running on different machines using sockets, urls, and protocols like tpc or udp


# Important classes

InetAddress, represents an ip address

Socket, used for tcp client-side

ServerSocker, used for tcp server-side

DatagramPacket, used for udp data packets

DatagramSocket, used for udp communication

URL, URLConnection: Used for http and general protocol-based communications


# TCP what it guarantees and what it does not?

TCP guarantees:
- Bytes arrive in order
- Bytes arrive without corruption
- Bytes arrive exactly once

TCP does NOT guarantee:
- Message boundaries
- That read() returns a full message

# How the OS sees a socket
The OS gives you:

A file descriptor
A send buffer
A receive buffer

So a Socket is basically a file

# How java supports this
with 

DataInputStream
readInt();
readFully(byte[]);

and

DataOutputStream 
writeInt(length);
write(byte[]);

## Concurrency

When you do this:

```
while (true) {
    Socket s = server.accept();
    handleClient(s); // blocks
}
```
Only ONE client at a time.
Correct mental models:
- Thread-per-connection
- Thread pool
- Event loop (NIO)

