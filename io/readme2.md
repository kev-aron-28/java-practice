# Input and output

## Input/Output Streams
in the Java API/ an object from which we can read a sequence of bytes is called an input stream. An object to which we can write a sequence of bytes is called an output streams.

These sources and destinations of byte sequences can be and often are files but they can also be network connections
and even blocks of memory. The abstract classes InputStream and OutputStream are the basis for a hierarchy 

## Reading and writing Bytes
The InputStream class has an abstract method:

``` java
abstract int read();
```

This method reads one byte and returns the byte that was read or -1 if it encounters the end of the input source.

The InputStream has a very useful method to read all bytes of a stream:
 
``` java
byte[] bytes = in.readAllBytes();
```

There are also method to read a given number of bytes.

Similarly the OutputStream class defines the abstract method
 
``` java
abstract void write(int b);
```

if you have an array of bytes, you can write them all at once:

``` java
byte[] values = . . .;
out.write(values);
```

The transferTo method transfers all bytes from input stream to an output stream

both the read and write methods block until the byte is actually read or written. This means that if the input stream cannot immediately be accessed
the current thread blocks. This gives other threads the change to do useful work while the method is waiting for the input stream to become available again


the available method lets you check the number of bytes that are currently available for reading. 

``` java
int bytesAvailable = in.available();
if (bytesAvailable > 0)
{
    var data = new byte[bytesAvailable];
    in.read(data);
}
```

When you have finished reading or writing to an input/output stream , close it by calling the close method. This call frees up the operating system resources
that are limited supply. if an application opens too many input/output streams without closing them, system resources can become depleted.
It also flushes the buffer used for the output stream

# The complete Stream Zoo
Java has a whole zoo of more than 60 classes and interfaces for input and output

## Hierarchy

### InputStream
``` txt
InputStream (abstract)
│
├── ByteArrayInputStream
├── FileInputStream
├── PipedInputStream
├── SequenceInputStream
├── ObjectInputStream
│   └── (implements ObjectInput, ObjectStreamConstants)
│
├── FilterInputStream (abstract)
│   │
│   ├── BufferedInputStream
│   ├── DataInputStream (implements DataInput)
│   ├── PushbackInputStream
│   └── LineNumberInputStream (deprecated)
│
└── StringBufferInputStream (deprecated)
```

### OutputStream
```
OutputStream (abstract)
│
├── ByteArrayOutputStream
├── FileOutputStream
├── PipedOutputStream
├── ObjectOutputStream
│   └── (implements ObjectOutput, ObjectStreamConstants)
│
├── FilterOutputStream (abstract)
│   │
│   ├── BufferedOutputStream
│   ├── DataOutputStream (implements DataOutput)
│   └── PrintStream
│
└── (no direct deprecated equivalent like input side, but PrintStream is legacy-ish)s
```

### Character streams

#### Reader hierarchy

``` 
Reader (abstract)
│
├── BufferedReader
│   └── LineNumberReader
│
├── CharArrayReader
├── StringReader
├── PipedReader
│
├── InputStreamReader
│   └── FileReader
│
├── FilterReader (abstract)
│   │
│   └── PushbackReader
```

#### Writer hierarchy
```
Writer (abstract)
│
├── BufferedWriter
│
├── CharArrayWriter
├── StringWriter
├── PipedWriter
│
├── OutputStreamWriter
│   └── FileWriter
│
├── PrintWriter
│
├── FilterWriter (abstract)
```

# Combining Input/Output stream filters
FileInputStream and FileOutputStream give you input and output streams attached to a disk file. You need to pass the file name or full path of the file
to the constructor.

``` java
var fin = new FileInputStream("employee.dat");
```

looks for a file a named employee.data

---
Relative path names start from the working directory, You can get this directory by a call to System.getProperty("user.dir")
It is set when the virtual machine starts and cannot be changed afterwards

For portable porgrams, use the file separtor character for the platform on which your program runs available in

java.io.File.separator

---

Like the abstract InputStream and OutputStream classes, these classes only support reading and writing at the byte level

If we just had a DataInputStream, we could read numeric types

``` java
DataInputStream din = ...;
double x = din.readDouble();
```

But just as the FileInputStream has no methods to read numeric types, the DataInputStream has no method to get data from a file


Java uses a clever mechanism to separate two kinds of responsabilities. Some input streams (Such as FileInputStream) can retrieve bytes from files and other more exotic locations
other input streams can assemble bytes into more useful data types. The java programmer has to combine the two

For example, to be able to read numbers from a file, first create a FileInputStream and the pass it to thw constructor of a DataInputStream

``` java
var fin = new FileInputStream("employee.dat");
var din = new DataInputStream(fin);
double x = din.readDouble();
```

You can see the classes FilterInputStream and FilterOutputStream are used to add capabilities to input/output streams that process bytes

You can add multiple capabilities by nesting the filters, For instance: by default, the input streams are nto buffered. That is,
every call to read asks the operating system to dole out yet another byte

``` java
var din = new DataInputStream(
    new BufferedInputStream(
    new FileInputStream("employee.dat")
    )
);
```

# Text input and output
When saving text strings you need to consider the character encoding. In the UTF-16 encodind that Java uses internally, however
many programs expect that text files use a different encoding. In UTF-8 the encoding commonly used on the internet

The OutputStreamWriter class turns an output stream of Unicode code units into a stream of bytes. The InputStreamReader class turns input
stream that contains bytes (specifying characters in some character encoding) into a reader that emits Unicode code units.

Since Java 18 these classes use the UTF-8 encoding by default, Before Java 18 or if you need to support an archaic encoding
specify the encoding in the constructor

``` java
var in = new InputStreamReader(new
FileInputStream("data.txt"), StandardCharsets.UTF_8);
```

## How to read text input

You can read a short text file into a string

``` java
String content = Files.readString(path, charset);
```

But if you want the file as a sequence of filnes 

``` java
List<String> lines = Files.readAllLines(path, charset);
```

If the file is large, process the lines lazily as a Stream<String>:

``` java
try (Stream<String> lines = Files.lines(path, charset)) 
{
. . .
}
```

---
If an IOException occurs as the stream fetches the lines, that exception is wraped into an UnchedIOException
which is thrown out of the stream operation. This subterfuge is necesarry because stream operations are not declared to throw any
check exceptions
---

To read lines from an arbitrary input stream:

``` java
InputStream inputStream = . . .;
try (var reader = new BufferedReader(new
InputStreamReader(inputStream, charset)))
{
Stream<String> lines = reader.lines();
. . .
}
```

You can also use a scanner to read tokens, the default delimiter is whitespace


## How to write text output

For text output, use a PrintWriter, That class has methods to print strings and numbers in text format.

``` java
var out = new PrintWriter("employee.txt", StandardCharsets.UTF_8);
```



# Read and Writing binary data
Text format is convenient for testing and debuggin because it is humany readable, but is not as efficient as transmitting
data in binary format.


## The DataInput and Dataoutput interfaces
The DataOutput interface defines the following methods for writing a number, a character a boolean value or a string
in binary format:

``` java
writeChars          
writeByte           
writeInt            
writeShort          
writeLong           
writeFloat
writeDouble
writeChar
writeBoolean
writeUTF
```

For example, writeInt always writes an integer as 4-byte binary quantity and writeDouble always writes a double as an 8-byte binary
quantity


To read the data back in:

```
readInt            
readShort          
readLong           
readFloat          
readDouble
readChar
readBoolean
readUTF
```

The DataInputStream class implements the DataInput interface. To read binary data from a file, combine with FileInputStream

``` java

var in = new DataInputStream(new
FileInputStream("employee.dat"));

var out = new DataOutputStream(new
FileOutputStream("employee.dat"));
```

# Random-Access files

The RandomAccessFile class lets you read or write data anywhere in a file. Disk files are random-access but input/output streams that
communicate with a network socket are not.