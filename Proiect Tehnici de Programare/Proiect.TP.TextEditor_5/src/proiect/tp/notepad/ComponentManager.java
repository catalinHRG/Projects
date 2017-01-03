/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import javax.swing.JScrollPane;
import javax.swing.text.Highlighter;
import proiect.tp.notepad.Voyager.CustomTextArea;

/**
 *
 * @author Catalin H
 */
public class ComponentManager {

          private CustomTextArea tabEditor;
          
          private final ClosableTabbedPane tabContainer;
          private final int selectedIndex;
          
         
          public ComponentManager(ClosableTabbedPane tabContainer) {
                    
                    this.tabContainer = tabContainer;
                    this.selectedIndex = tabContainer.getSelectedIndex();
                    
          }
          
          public void fetchTextComponent(){
                    
                    this.tabEditor = ((CustomTextArea) (((JScrollPane) tabContainer.getComponentAt(selectedIndex)).getViewport()).getComponent(0));
          }
          
          public Highlighter fetchHighlighter(){
                    
                    return tabEditor.getHighlighter();
          }
          
          public CustomTextArea getTextComponent(){
                    
                    return tabEditor;
          }
          
}
