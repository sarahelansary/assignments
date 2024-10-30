package os.example;
import org.junit.jupiter.api.*;
import os.example.redirectToFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RedirectToFileTest {

 private redirectToFile cliex;

 @BeforeEach
 void setUp() {
  cliex = new redirectToFile();
 }


 @Test
 void testredirectToFile() {
  String testFilename = "testOutput.txt";
  String content = "Hello";
  cliex.redirectToFile(testFilename,new String[]{content});

  try {
   String fileContent = new String(Files.readAllBytes(Paths.get(testFilename)));
   assertEquals(content, fileContent.trim());
  } catch (IOException e) {
   fail("Exception while reading the file: " + e.getMessage());
  } finally {
   new File(testFilename).delete();
  }
 }


}
