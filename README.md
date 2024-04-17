# Java Assembly Library (JASM)
JASM is a library meant to read class files from their byte code to memory, and into human readable text, as well as a few other low-level features. JASM was completely written in Java 8 with only using Java 8 features, and as such, has backwards compatibility to be used with projects dating back to Java 8, but can be used to analyze anything from Java 1.0 to Java 22.
### Interpreter *(RELEASE-1.0.0)*
Supports all Java class file features up to JVM version 22.
### Assembler *(ALPHA-0.1.0)*
The assembler project currently supports disassembling most instructions, methods, fields, and a few attributes. The next versions of the assembler will complete the remaining opcodes and attributes. The release version of the assembler will support assembling this IR back into byte code.
### LLVM *(planned)*
Java code, up to Java 5 was supported to be compiled to machine code with the GNU GCC compiler. This project is planned to bring back support up to the current Java version (22 as of now).
### Decompiler *(planned)*
Similar to CFR, Fernflower, etc., this project is planned to support decompiling Java code into compilable Java code.

