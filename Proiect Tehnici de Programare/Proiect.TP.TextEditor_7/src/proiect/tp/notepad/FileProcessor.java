/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import proiect.tp.notepad.Voyager.CustomTextArea;

/**
 *
 * @author Catalin H
 */
public class FileProcessor {
          
          
          public String returnFileName(File file) {

                    return file.getName();
          }

          public String readFromFile(File source) {

                    String temp = "" ;
                    

                    try {
                              
                              FileReader fr = new FileReader(source);
                              BufferedReader br = new BufferedReader(fr);

                              String buffer;

                              while ((buffer = br.readLine()) != null) {

                                        temp += (buffer + "\n");
                              }

                              br.close();
                              
                    } catch (FileNotFoundException ex) {
                              Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                              Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    return temp;
          }

          public void writeToFile(File destination, CustomTextArea content) {

                    try {

                              FileWriter fr = new FileWriter(destination);
                              PrintWriter pw = new PrintWriter(fr);
                              
                              String[] lines;
                              lines = content.getText().split("\\n");

                              for (int i = 0; i < lines.length; i++) {

                                        pw.println(lines[i]);
                              }
                              
                              pw.close();

                    } catch (IOException ex) {

                              Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
                    }
          }
}
