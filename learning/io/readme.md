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

# The most common I/O patterns in java

1. standard I/O (System.in, System.out, System.err)

System.in InputStream: Reads bytes from standard input
System.out PrintStream WritesText to standar output
System.err PrintStream

2. File I/O (reading/wrting files)
3. Buffered I/O (fast reading/writing)
4. Data/Object I/O (Binary data and serialization)
5. Console I/O with Scanner or BufferReader


--- review

Streams has two main categories:
1. Byte streams
For images,pdfs,audio
- InputStream
- OutputStream

FileInputStream
FileOutputStream
BufferedInputStream
BufferedOutputStream

2. Char streams
- Reader
- Writer

FileReader
FileWriter
BufferedReader
BufferedWriter

# Read files

- Line by line
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}

- Read all as bytes
try (FileInputStream fis = new FileInputStream("image.png")) {
    byte[] data = fis.readAllBytes();
}

# Write files

- write
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    bw.write("Hello!");
    bw.newLine();
    bw.write("World!");
}

- write bytes
try (FileOutputStream fos = new FileOutputStream("photo.png")) {
    fos.write(data);
}

# Buffered streams
BufferedInputStream bis = new BufferedInputStream(new FileInputStream("a.bin"));
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("b.bin"));

# Data streams
try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.bin"))) {
    dos.writeInt(42);
    dos.writeDouble(3.14);
    dos.writeUTF("Hello");
}

try (DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"))) {
    int n = dis.readInt();
    double d = dis.readDouble();
    String t = dis.readUTF();
}

# Object streams serialization

class Person implements Serializable {
    private String name;
}
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("p.dat"));
oos.writeObject(person);

ObjectInputStream ois = new ObjectInputStream(new FileInputStream("p.dat"));
Person p = (Person) ois.readObject();


# java.nio new i/o - modern java

Path → representa rutas
Files → utilidades rápidas
Channels → alternativa a streams
Buffers → lectura/escritura eficiente
NIO.2 (Java 7) → caminar directorios, copiar, borrar, etc.

## Path and files

- create path
Path path = Paths.get("data.txt");

- read file complete
String content = Files.readString(path);

- write 
Files.writeString(path, "Hello!");

- list files on dir
Files.list(Paths.get("folder"))
    .forEach(System.out::println);

- copy,move,delete
Files.copy(src, dest);
Files.move(src, dest);
Files.delete(path);

- Walk and recursion
Files.walk(Paths.get("root"))
     .filter(Files::isRegularFile)
     .forEach(System.out::println);

