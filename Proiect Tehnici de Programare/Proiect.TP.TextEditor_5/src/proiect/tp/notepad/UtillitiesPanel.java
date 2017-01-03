/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Catalin H
 */
public class UtillitiesPanel extends JPanel {

          private final JLabel searchLabel = new JLabel("Seach for : ");
          private final JTextField searchBox = new JTextField(15);
          private final JButton goButton = new JButton("Go! ");

          private ArrayList<Integer> occurrenceIndeces = new ArrayList();
          private HighlightManager highlighter;

          private String previousCandidate = null;
          private int previousTabReference = 0;
          private int n = 0;
          private boolean flag = false;
         
          //public ConnectionStatusDisplay dataBaseConnectionStatus = new ConnectionStatusDisplay();

          public UtillitiesPanel() {

                    setLayout(new FlowLayout());
                    
                    //add(dataBaseConnectionStatus);
                    
                    add(searchLabel);

                    searchBox.setFocusable(true);
                    searchBox.addKeyListener(new KeyListener() {
                              @Override
                              public void keyTyped(KeyEvent ke) {
                              }

                              @Override
                              public void keyPressed(KeyEvent ke) {

                                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {

                                                  triggerSearch();
                                        }
                              }

                              @Override
                              public void keyReleased(KeyEvent ke) {
                              }

                    });

                    add(searchBox);

                    goButton.addActionListener(new java.awt.event.ActionListener() {

                              @Override
                              public void actionPerformed(java.awt.event.ActionEvent evt) {

                                        triggerSearch();

                              }
                    }
                    );

                    add(goButton);

          }

          public void triggerSearch() {

                    String candidate = searchBox.getText();
                    int tabReference = Voyager.copyOfTabContainer.getSelectedIndex();

                    if (!candidate.equals(previousCandidate) | tabReference != previousTabReference) {

                              n = 0;

                              previousCandidate = candidate;
                              previousTabReference = tabReference;

                              ComponentManager componentManager = new ComponentManager(Voyager.copyOfTabContainer);
                              
                              componentManager.fetchTextComponent(); // fetches and stores the text component for later use
                              ContentEditor contentEditor = new ContentEditor(componentManager.getTextComponent());
                              contentEditor.splitContent(); // splits and stores the split content for later use
                              
                              
                              ContentParser parser = new ContentParser(contentEditor.getSplittedContent(), candidate);
                              parser.searchForCandidate(); // after the search has finished , stores the array of indeces of each occurrence , for later use
                              occurrenceIndeces = parser.getOccurrenceIndeces(); // retrieve the occurrence indeces to instantiate the HighlightManager object with

                              highlighter = new HighlightManager(occurrenceIndeces, componentManager.getTextComponent(), candidate);
                              highlighter.highlightCandidate(n);

                              flag = false; // skip the next block of code since we only need to highlight the first occurence ; the search bottun has been pressed th first time for this candidate

                    }

                    if (n < occurrenceIndeces.size() & flag) {
                              
                              highlighter.highlightCandidate(n); // only highlights when the second press of the search button has occurred
                    }

                    n++;
                    flag = true; // flag indicating that the first press of the search button has occurred and now we can move on with simply highlighting from where we left at the next search

                    if (n == occurrenceIndeces.size()) {

                              n = 0; // in case we have reached the end of the occurrenceIndex array , we have to start highlighting from the begining
                    }
          }

}
