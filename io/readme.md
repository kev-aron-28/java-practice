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



## All uses
Java I/O is based on 4 big axes:

Direction → Input / Output
Data type → Byte / Character
Source / Destination → File, Network, Memory, Console
Decorator → Buffering, Data types, Objects, Compression

### Root classes 
| Class          | Type | Purpose                          |
| -------------- | ---- | -------------------------------- |
| `InputStream`  | Byte | Read raw bytes                   |
| `OutputStream` | Byte | Write raw bytes                  |
| `Reader`       | Char | Read characters (encoding-aware) |
| `Writer`       | Char | Write characters                 |


### Streams
| Class              | Use                                  |
| ------------------ | ------------------------------------ |
| `FileInputStream`  | Read binary files (images, pdf, zip) |
| `FileOutputStream` | Write binary files                   |

### Character based 
| Class        | Use              |
| ------------ | ---------------- |
| `FileReader` | Read text files  |
| `FileWriter` | Write text files |


### Buffering streams 
| Class                  | Wraps        | Purpose                |
| ---------------------- | ------------ | ---------------------- |
| `BufferedInputStream`  | InputStream  | Reduce syscalls        |
| `BufferedOutputStream` | OutputStream | Faster writes          |
| `BufferedReader`       | Reader       | Read lines             |
| `BufferedWriter`       | Writer       | Efficient text writing |

### Data streams (primitive types)
| Class              | Purpose          |
| ------------------ | ---------------- |
| `DataInputStream`  | Read primitives  |
| `DataOutputStream` | Write primitives |

### Object streams
| Class                | Purpose            |
| -------------------- | ------------------ |
| `ObjectInputStream`  | Read Java objects  |
| `ObjectOutputStream` | Write Java objects |


### Console streams
| Stream       | Description    |
| ------------ | -------------- |
| `System.in`  | Keyboard input |
| `System.out` | Console output |
| `System.err` | Error output   |


### Pipe streams
| Class               | Use                     |
| ------------------- | ----------------------- |
| `PipedInputStream`  | Thread-to-thread input  |
| `PipedOutputStream` | Thread-to-thread output |
| `PipedReader`       | Char version            |
| `PipedWriter`       | Char version            |


## Common patterns

1. Read from a text file
BufferedReader reader =
    Files.newBufferedReader(Path.of("data.txt"), StandardCharsets.UTF_8);

2. Write to a text file
BufferedWriter writer =
    Files.newBufferedWriter(Path.of("out.txt"), UTF_8);

3. Read a binary file
InputStream in =
    new BufferedInputStream(new FileInputStream("image.png"));

4. Read a small file completely (config, JSON)
String content = Files.readString(Path.of("config.json"));

5. Network: read text from a socket
BufferedReader reader =
    new BufferedReader(
        new InputStreamReader(socket.getInputStream(), UTF_8)
    );

6. Send text to a socket
PrintWriter out =
    new PrintWriter(
        new OutputStreamWriter(socket.getOutputStream(), UTF_8),
        true
    );

7. Network: Binary protocol
DataInputStream in =
    new DataInputStream(
        new BufferedInputStream(socket.getInputStream())
    );

DataOutputStream out =
    new DataOutputStream(
        new BufferedOutputStream(socket.getOutputStream())
    );

8. Console input
Scanner scanner = new Scanner(System.in);

BufferedReader reader =
    new BufferedReader(new InputStreamReader(System.in));

