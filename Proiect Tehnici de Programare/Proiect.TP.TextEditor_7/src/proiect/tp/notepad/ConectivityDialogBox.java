/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Catalin H
 */
public class ConectivityDialogBox extends JDialog {

          private final JTextField usernameField;
          private final JPasswordField passwordField;
          private final JTextField dataBaseName;
          private final JButton connectButton;

          private final JLabel usernameLabel;
          private final JLabel passwordLabel;
          private final JLabel tableNameLabel;

          private final JPanel container;
          
          private ServerConnectivityManager connectivityManager;

          public ConectivityDialogBox() {

                    this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    
                    this.setSize(300, 150);
                    final Toolkit toolkit = Toolkit.getDefaultToolkit();
                    final Dimension screenSize = toolkit.getScreenSize();
                    final int x = (screenSize.width - this.getWidth()) / 2;
                    final int y = (screenSize.height - this.getHeight()) / 2;
                    this.setLocation(x, y);

                    usernameLabel = new JLabel(" username : ");
                    passwordLabel = new JLabel(" password : ");
                    tableNameLabel = new JLabel(" database : ");

                    usernameField = new JTextField(15);
                    passwordField = new JPasswordField(15);
                    dataBaseName = new JTextField(15);

                    connectButton = new JButton("Connect");
                    
                    container = new JPanel();

                    container.setLayout(new GridBagLayout());

                    GridBagConstraints gc = new GridBagConstraints();

                    gc.weightx = 0.5;
                    gc.weighty = 0.5;
                    gc.anchor = GridBagConstraints.LINE_START;

                    gc.gridx = 0;
                    gc.gridy = 0;
                    container.add(tableNameLabel, gc);

                    gc.gridx = 0;
                    gc.gridy = 1;
                    container.add(usernameLabel, gc);

                    gc.gridx = 0;
                    gc.gridy = 2;
                    container.add(passwordLabel, gc);

                    gc.weightx = 1;

                    gc.gridx = 1;
                    gc.gridy = 0;
                    container.add(dataBaseName, gc);

                    gc.gridx = 1;
                    gc.gridy = 1;
                    container.add(usernameField, gc);

                    gc.gridx = 1;
                    gc.gridy = 2;
                    container.add(passwordField, gc);

                    gc.gridx = 1;
                    gc.gridy = 3;
                    gc.weighty = 1.5;

                    connectButton.addActionListener(new java.awt.event.ActionListener() {

                              @Override
                              public void actionPerformed(java.awt.event.ActionEvent evt) {

                                        String username = usernameField.getText();
                                        String password = new String(passwordField.getPassword());
                                        String address = dataBaseName.getText();

                                        connectivityManager = new ServerConnectivityManager(username, password, address);
                                        closeDialogBox();
                              }
                    });

                    container.add(connectButton, gc);

                    this.add(container);
                    this.setVisible(true);

          }
          
          public void closeDialogBox(){
                    
                    this.dispose();
          }
          
          public ServerConnectivityManager getConnectivityManager(){
                    
                    return connectivityManager;
                    
          }
}
