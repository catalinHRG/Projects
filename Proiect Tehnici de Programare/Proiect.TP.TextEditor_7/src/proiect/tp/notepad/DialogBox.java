/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Catalin H
 */
public class DialogBox extends JDialog{
          
          JButton okButton = new JButton("Ok");
          JTextField tabNameField = new JTextField(5);
          JLabel promptLabel = new JLabel("Enter the name for the file ... ");
          
          public void close(){
                    
                    this.dispose();
          }
          
          public DialogBox(ClosableTabbedPane tabContainer){
                    
                    this.setSize(tabContainer.getSize().width / 4, tabContainer.getSize().height / 4);
                    this.setLocation(tabContainer.getLocationOnScreen().x + tabContainer.getWidth() / 3,
                            tabContainer.getLocationOnScreen().y + tabContainer.getHeight() / 3);
                    this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    
                    this.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    
                    okButton.addActionListener(new java.awt.event.ActionListener() {

                              @Override
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        
                                        
                                        Voyager.CustomTextArea tabEditor = new Voyager.CustomTextArea();
                                        JScrollPane scrollContainer = new JScrollPane(tabEditor);
                                        tabContainer.addTab(tabNameField.getText(), scrollContainer);
                                        tabContainer.setSelectedComponent(scrollContainer);
                                        close();

                              }
                    });

                    c.gridx = 0;
                    c.gridy = 0;
                    this.add(promptLabel, c);
                    c.gridx = 0;
                    c.gridy = 1;
                    this.add(tabNameField, c);
                    c.anchor = GridBagConstraints.LINE_START;
                    c.gridx = 2;
                    c.gridy = 1;
                    this.add(okButton, c);
                    
                    
          }
                    
}
