/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proiect.tp.notepad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Catalin H
 */

public class ContentParser {

          public class CustomThread extends Thread {

                    private final String chunk;
                    private final int previousChunkSize;
                    //private final int threadID;

                    public CustomThread(String chunk, int previousChunkSize) {

                              this.chunk = chunk;
                              this.previousChunkSize = previousChunkSize;
                              //this.threadID = threadID;

                    }

                    @Override
                    public void run() {

                              int occurrenceIndex = 0;
                              
                              while (true) {

                                        occurrenceIndex = chunk.toLowerCase().indexOf(candidate.toLowerCase(), occurrenceIndex);

                                        if (occurrenceIndex == -1) {
                                                  break;
                                        }
                                        occurrenceIndeces.add(occurrenceIndex + previousChunkSize);
                                        occurrenceIndex++; // e ceva in neregula aici , nu am mai tinut cont de partajarea string ului initial // thread ul 2 spre exemplu trebuie sa adauge occurrenceIndex + cat de mare e string ul pe care lucreaza threadul 1
                              }
                              
                    }
          }

          public ContentParser(ArrayList<String> splitContent, String candidate) {

                    this.candidate = candidate;

                    occurrenceIndeces = new ArrayList();
                    threads = new ArrayList();

                    threads.add(new CustomThread(splitContent.get(0), 0)); // first threads will not need to add the lenght of the previous chunk size to the indeces found for occurrence index relevance in the main content string
                    int a = 0;
                    for (int i = 0 ; i < splitContent.size() ; i++){
                              a += splitContent.get(i).length();
                    }
                    
                    int cumulativeSize = 0 ;
                    
                    for (int i = 1; i < splitContent.size(); i++) {
                              
                              cumulativeSize += splitContent.get(i - 1).length();
                              threads.add(new CustomThread(splitContent.get(i), cumulativeSize)); // each thread will have a chunk associated with him
                    }
                    
                    // now that the threads have been instantiated , they can be started by calling the searchForCandidate method on this ContentParser object
          }

          private final String candidate;
          private final ArrayList<Integer> occurrenceIndeces;

          private final ArrayList<CustomThread> threads;

          public void searchForCandidate() {

                    try {

                              threadStart();

                    } catch (InterruptedException ex) {
                              
                              Logger.getLogger(ContentParser.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Collections.sort(occurrenceIndeces);

          }

          public void threadStart() throws InterruptedException {

                    for (int i = 0; i < threads.size(); i++) {

                              threads.get(i).start();
                              threads.get(i).join();
                    }
          }

          public ArrayList<Integer> getOccurrenceIndeces() {

                    return this.occurrenceIndeces;
          }
}
