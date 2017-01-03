/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import proiect.tp.notepad.Voyager.CustomTextArea;

/**
 *
 * @author Catalin H
 */
public class HighlightManager {
          
          private final ArrayList<Integer> occurrenceIndeces;
          private final CustomTextArea tabEditor;
          private final String candidate ;
          private final Highlighter hl;
          
          public HighlightManager (ArrayList<Integer> occurrenceIndeces, CustomTextArea tabEditor, String candidate) {
                    
                    this.occurrenceIndeces = occurrenceIndeces;
                    this.tabEditor = tabEditor;
                    this.candidate = candidate;
                    this.hl = tabEditor.getHighlighter();
                    
          }
          
          public void highlightCandidate (int indexPointer) {
                    
                    Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
                    int p0 = occurrenceIndeces.get(indexPointer);
                    int p1 = p0 + candidate.length();
                    try {
                              hl.removeAllHighlights();
                              hl.addHighlight(p0 , p1, painter);
                              tabEditor.setCaretPosition(p0); // focus on the highlighted word

                    } catch (BadLocationException ex) {
                              Logger.getLogger(UtillitiesPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
          }
}
