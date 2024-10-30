package org.example;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class cliTest {

 private cli cliex;

 @BeforeEach
 void setUp() {
  cliex = new cli();
 }


 @Test
 void testRedirectToFile() {
  String testFilename = "testOutput.txt";
  String content = "Hello, World!";
  cliex.redirectToFile(content, testFilename);

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
