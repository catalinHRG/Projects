/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.Highlighter;

/**
 *
 * @author Catalin H
 */
public class UtillitiesPanel extends JPanel {

          JLabel searchLabel = new JLabel("Seach for : ");
          JTextField searchBox = new JTextField(15);
          JButton goButton = new JButton("Go! ");
          
          JTextArea tempTabEditor  = new CustomTextArea();

          Thread t1;
          Thread t2;
          Thread t3;

          ArrayList<Integer> indeces = new ArrayList();
          ArrayList<Integer> occurrenceIndeces = new ArrayList();

          String previousCandidate = null;
          int counter = 0;
          int selectedIndex = 0;
          boolean flag = false;

          public UtillitiesPanel() {

                    setLayout(new FlowLayout());
                    
                    add(searchLabel);
                    
                    searchBox.setFocusable(true);
                    searchBox.addKeyListener(new KeyListener(){
                              @Override
                              public void keyTyped(KeyEvent ke) {}

                              @Override
                              public void keyPressed(KeyEvent ke) {
                                        
                                        if (ke.getKeyCode() == KeyEvent.VK_ENTER){
                                                  
                                                  searchForCandidate(searchBox.getText().toLowerCase());
                                        }
                              }

                              @Override
                              public void keyReleased(KeyEvent ke) {}
                              
                    });
                   
                    add(searchBox);

                    goButton.addActionListener(new java.awt.event.ActionListener() {

                              @Override
                              public void actionPerformed(java.awt.event.ActionEvent evt) {

                                        searchForCandidate(searchBox.getText().toLowerCase());

                              }
                    }
                    );
                    
                    add(goButton);

          }

          void highlightCandidate(int index, String cand, Highlighter hl) {

                    HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
                    int p0 = index;
                    int p1 = p0 + cand.length();
                    try {
                              hl.addHighlight(p0, p1, painter);

                    } catch (BadLocationException ex) {
                              Logger.getLogger(UtillitiesPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
          }

          void searchForCandidate(String candid) {

                    if (!candid.equals(previousCandidate)) {

                              previousCandidate = candid;
                              indeces = new ArrayList();
                              occurrenceIndeces = new ArrayList();

                              selectedIndex = Voyager.copyOfTabContainer.getSelectedIndex(); // poate merge de facut getter pentru componenta respectiva -- de inercat
                              tempTabEditor = ( (CustomTextArea) (((JScrollPane) Voyager.copyOfTabContainer.getComponentAt(selectedIndex)).getViewport()).getComponent(0));
                              String content = tempTabEditor.getText();
                              
                              System.out.println("Tab nr : " + selectedIndex + " Tab content " + content);

                              for (int i = 0; i < content.length(); i++) {

                                        if (content.charAt(i) == '\n') {

                                                  indeces.add(i);
                                        }
                              }

                              String firstChunk = content.substring(0, indeces.get(indeces.size() / 3));
                              String secondChunk = content.substring(indeces.get(indeces.size() / 3), indeces.get((indeces.size() / 3) * 2));
                              String thirdChunk = content.substring(indeces.get((indeces.size() / 3) * 2), content.length());
//                              System.out.println("---------------------------");
//                              System.out.println(firstChunk);
//                              System.out.println("---------------------------");
//                              System.out.println(secondChunk);
//                              System.out.println("---------------------------");
//                              System.out.println(thirdChunk);
//                              System.out.println("---------------------------");

                              t1 = new Thread(new Runnable() {

                                        @Override
                                        public void run() {

                                                  int occurrenceIndex = 0;

                                                  while (true) {

                                                            occurrenceIndex = firstChunk.toLowerCase().indexOf(candid, occurrenceIndex);
                                                            if (occurrenceIndex == -1) {
                                                                      break;
                                                            }
                                                            occurrenceIndeces.add(occurrenceIndex);
                                                            occurrenceIndex++;
                                                  }
                                        }

                              });

                              t2 = new Thread(new Runnable() {

                                        @Override
                                        public void run() {

                                                  int occurrenceIndex = 0;

                                                  while (occurrenceIndex >= 0) {

                                                            occurrenceIndex = secondChunk.toLowerCase().indexOf(candid, occurrenceIndex);
                                                            if (occurrenceIndex == -1) {
                                                                      break;
                                                            }
                                                            occurrenceIndeces.add(firstChunk.length() + occurrenceIndex);
                                                            occurrenceIndex++;

                                                  }
                                        }

                              });

                              t3 = new Thread(new Runnable() {

                                        @Override
                                        public void run() {

                                                  int occurrenceIndex = 0;

                                                  while (occurrenceIndex >= 0) {

                                                            occurrenceIndex = thirdChunk.toLowerCase().indexOf(candid, occurrenceIndex);
                                                            if (occurrenceIndex == -1) {
                                                                      break;
                                                            }
                                                            occurrenceIndeces.add(occurrenceIndex + firstChunk.length() + secondChunk.length());
                                                            occurrenceIndex++;
                                                  }
                                        }
                              });

                              t1.start();
                              t2.start();
                              t3.start();

                              try {
                                        t1.join();
                                        t2.join();
                                        t3.join();

                              } catch (InterruptedException ex) {
                                        Logger.getLogger(UtillitiesPanel.class.getName()).log(Level.SEVERE, null, ex);
                              }

                              counter = 0;
                              flag = false;

                              Collections.sort(occurrenceIndeces);
                              System.out.println(occurrenceIndeces);
                              Highlighter highlighter = tempTabEditor.getHighlighter();
                              highlighter.removeAllHighlights();
                              highlightCandidate(occurrenceIndeces.get(counter), candid, highlighter);

                              tempTabEditor.setCaretPosition(occurrenceIndeces.get(counter) + candid.length());

                    }

                    if (counter < occurrenceIndeces.size() & flag) {

                              Highlighter highlighter = tempTabEditor.getHighlighter();
                              highlighter.removeAllHighlights(); // scoate toate highlight urile din toate taburile
                              highlightCandidate(occurrenceIndeces.get(counter), candid, highlighter);

                              tempTabEditor.setCaretPosition(occurrenceIndeces.get(counter) + candid.length());

                    }

                    counter++;
                    flag = true;

                    if (counter == occurrenceIndeces.size()) {

                              counter = 0;
                    }
          }

}
