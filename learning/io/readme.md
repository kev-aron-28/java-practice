# Input/Output

Referes to how your program communicates with the outside world:
- Input: reading data
- Output: writing data

There are two main models
1. java.io
This is the trad model and works with stream and each operation blocks until it completes

2. java.nio
this is modern and faster uses buffers instead of streams.
allows nonblocking operations
includes channels selectors and buffers

# HIerarchy

Byte streams
It handles raw binary data
- InputStream
- OutputStream

InputStream family:
- FileInputStream: Reads bytes from a files
- BufferedInputStream: Adds buffering to make reading faster
- DataInputStream: Reads java primitives in binary form
- ByteArrayInputStream: Reads bytes from a byte array in memory
- ObjectInputStream: Reads java objects 

OutputStream family:
- FileOutputStream: wrytes bytes to a file
- BufferedOutputStrea: Buffers output for efficiency
- DataOutputStrea: Writes java primitives
- ByteArrayOutputStream: Writes bytes to an in memory array
- ObjectOutputStream: Writes java objects

Tip: Use Buffered Streams for Performance
BufferedInputStream and BufferedOutputStream wrap around other streams.

try (
    BufferedInputStream in = new BufferedInputStream(new FileInputStream("source.bin"));
    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("dest.bin"))
) {
    byte[] buffer = new byte[1024];
    int len;
    while ((len = in.read(buffer)) != -1) {
        out.write(buffer, 0, len);
    }
}

# Char streams
Handle text (characters) rather than raw bytes.
Automatically handle character encoding (like UTF-8 or UTF-16).
Base classes:

Reader → for reading characters.
Writer → for writing characters.

- FileReader: Reads text from a file
- BufferedReader: Adds buffering and allows reading lines
- CharArrayReader: Reads froma a character array in memory
- InputStreamReader: Converts byte stream
- String reader: Reads from a string

