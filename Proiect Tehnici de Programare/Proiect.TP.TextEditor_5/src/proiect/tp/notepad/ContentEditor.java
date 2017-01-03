/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.util.ArrayList;
import proiect.tp.notepad.Voyager.CustomTextArea;

/**
 *
 * @author Catalin H
 */
public class ContentEditor {

          private final String content;
          
          private final int NR_OF_THREADS = 3;

          private ArrayList<Integer> newLineIndeces;
          private ArrayList<String> splitContent;

          public ContentEditor(CustomTextArea tabEditor) {

                    this.content = tabEditor.getText();
                    
          }

          public void splitContent() {

                    findNewLineIndeces();
                    loadSplittedContent();

          }

          public void findNewLineIndeces() { // avoiding the split of one word , into two different halves , in which case the two threads operating on each half would never find that word if it would be one of the candidates

                    newLineIndeces = new ArrayList();
                    
                    for (int i = 0; i < content.length(); i++) {

                              if (content.charAt(i) == '\n') {

                                        newLineIndeces.add(i);
                              }
                    }
                    
          }
          
          public void loadSplittedContent(){
                    
                    splitContent = new ArrayList();
                    int counter = 0; // just need it to make it so that at the first iterration we start from the 0 indeces ,  the split , after we just compute the left boundarie by the formula
                    
                    for (int i = 0 ; i < NR_OF_THREADS ; i++){
                              
                              int leftBoundarie = newLineIndeces.size() * i / NR_OF_THREADS;
                              int rightBoundarie = newLineIndeces.size() * (i + 1) / NR_OF_THREADS ;
                              
                              if ( i == NR_OF_THREADS - 1 ) { splitContent.add(content.substring(newLineIndeces.get(leftBoundarie) * counter , content.length())); }
                              
                              else { splitContent.add(content.substring(newLineIndeces.get(leftBoundarie) * counter, newLineIndeces.get(rightBoundarie))); }
                              
                              counter = 1;
                              
                    }
                    
          }
          
          public ArrayList<String> getSplittedContent(){
                    
                    return splitContent;
          }
}
