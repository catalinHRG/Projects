/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Catalin H
 */
public class Voyager extends JFrame {

          public static ClosableTabbedPane copyOfTabContainer;
          public ServerConnectivityManager connectivityManager;

          public static class CustomTextArea extends JTextArea implements DropTargetListener {

                    DropTarget dropTarget;

                    public CustomTextArea(String content) {

                              super(content);

                              dropTarget = new DropTarget(this, this);

                              this.addCaretListener(new CaretListener() {
                                        @Override
                                        public void caretUpdate(CaretEvent ce) {

                                                  JTextArea newEditor = (JTextArea) ce.getSource();

                                                  int lineNo;
                                                  int columnNo;

                                                  try {

                                                            int caretpos = newEditor.getCaretPosition();

                                                            lineNo = newEditor.getLineOfOffset(caretpos);

                                                            columnNo = caretpos - newEditor.getLineStartOffset(lineNo);

                                                            lineNo += 1;

                                                            statusDisplayBar.setText("Line : " + lineNo + " Column : " + columnNo);

                                                  } catch (BadLocationException ex) {
                                                            Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
                                                  }
                                        }

                              });

                    }

                    public CustomTextArea() {

                              dropTarget = new DropTarget(this, this);

                              this.addCaretListener(new CaretListener() {
                                        @Override
                                        public void caretUpdate(CaretEvent ce) {

                                                  JTextArea newEditor = (JTextArea) ce.getSource();

                                                  int lineNo;
                                                  int columnNo;

                                                  try {

                                                            int caretpos = newEditor.getCaretPosition();

                                                            lineNo = newEditor.getLineOfOffset(caretpos);

                                                            columnNo = caretpos - newEditor.getLineStartOffset(lineNo);

                                                            lineNo += 1;

                                                            statusDisplayBar.setText("Line : " + lineNo + " Column : " + columnNo);

                                                  } catch (BadLocationException ex) {
                                                            Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
                                                  }
                                        }

                              });

                    }

                    @Override
                    public void dragEnter(DropTargetDragEvent dtde) {
                    }

                    @Override
                    public void dragOver(DropTargetDragEvent dtde) {
                    }

                    @Override
                    public void dropActionChanged(DropTargetDragEvent dtde) {
                    }

                    @Override
                    public void dragExit(DropTargetEvent dte) {
                    }

                    @Override
                    public void drop(DropTargetDropEvent dtde) {

                              DropData data = new DropData();
                              data.dataTransfer(dtde);
                              this.append(data.fileContent);
                    }

          }

          public Voyager() {

                    initComponents();
                    copyOfTabContainer = (ClosableTabbedPane) tabContainer;
          }

          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
          private void initComponents() {

                    jTextField1 = new javax.swing.JTextField();
                    topContainer = new javax.swing.JInternalFrame();
                    shortcutToolBar = new javax.swing.JToolBar();
                    connectionTriggerButton = new javax.swing.JButton();
                    getTableContentTrigger = new javax.swing.JButton();
                    mainMenuBar = new javax.swing.JMenuBar();
                    fileMenu = new javax.swing.JMenu();
                    newFileMenuItem = new javax.swing.JMenuItem();
                    newProjectMenuItem = new javax.swing.JMenuItem();
                    openFileMenuItem = new javax.swing.JMenuItem();
                    saveAsMenuItem = new javax.swing.JMenuItem();
                    editMenu = new javax.swing.JMenu();
                    undoMenuItem = new javax.swing.JMenuItem();
                    redoMenuItem = new javax.swing.JMenuItem();
                    cutMenuItem = new javax.swing.JMenuItem();
                    copyMenuItem = new javax.swing.JMenuItem();
                    pasteMenuItem = new javax.swing.JMenuItem();
                    mainContainerCenter = new javax.swing.JPanel();
                    southContainer = new javax.swing.JPanel();
                    statusDisplayBar = new javax.swing.JTextField();
                    utillitiesPanel = new UtillitiesPanel();
                    splitContainerCenter = new javax.swing.JSplitPane();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    projectManager = new javax.swing.JTree();
                    tabContainer = new ClosableTabbedPane();

                    jTextField1.setText("jTextField1");

                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                    topContainer.setBackground(new java.awt.Color(0, 0, 255));
                    topContainer.setVisible(true);

                    shortcutToolBar.setRollover(true);
                    shortcutToolBar.setPreferredSize(new java.awt.Dimension(18, 25));

                    connectionTriggerButton.setText("Connect to DB");
                    connectionTriggerButton.setFocusable(false);
                    connectionTriggerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    connectionTriggerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    connectionTriggerButton.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        connectionTriggerButtonActionPerformed(evt);
                              }
                    });
                    shortcutToolBar.add(connectionTriggerButton);

                    getTableContentTrigger.setText("Get Table Content");
                    getTableContentTrigger.setFocusable(false);
                    getTableContentTrigger.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    getTableContentTrigger.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    getTableContentTrigger.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        getTableContentTriggerActionPerformed(evt);
                              }
                    });
                    shortcutToolBar.add(getTableContentTrigger);

                    topContainer.getContentPane().add(shortcutToolBar, java.awt.BorderLayout.CENTER);

                    fileMenu.setText("File");

                    newFileMenuItem.setText("New file ...");
                    newFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        newFileMenuItemActionPerformed(evt);
                              }
                    });
                    fileMenu.add(newFileMenuItem);

                    newProjectMenuItem.setText("New project ...");
                    newProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        newProjectMenuItemActionPerformed(evt);
                              }
                    });
                    fileMenu.add(newProjectMenuItem);

                    openFileMenuItem.setText("Open file ...");
                    openFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        openFileMenuItemActionPerformed(evt);
                              }
                    });
                    fileMenu.add(openFileMenuItem);

                    saveAsMenuItem.setText("Save file as ...");
                    saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        saveAsMenuItemActionPerformed(evt);
                              }
                    });
                    fileMenu.add(saveAsMenuItem);

                    mainMenuBar.add(fileMenu);

                    editMenu.setText("Edit");

                    undoMenuItem.setText("Undo");
                    editMenu.add(undoMenuItem);

                    redoMenuItem.setText("Redo");
                    editMenu.add(redoMenuItem);

                    cutMenuItem.setText("Cut");
                    editMenu.add(cutMenuItem);

                    copyMenuItem.setText("Copy");
                    editMenu.add(copyMenuItem);

                    pasteMenuItem.setText("Paste");
                    editMenu.add(pasteMenuItem);

                    mainMenuBar.add(editMenu);

                    topContainer.setJMenuBar(mainMenuBar);

                    getContentPane().add(topContainer, java.awt.BorderLayout.PAGE_START);

                    mainContainerCenter.setLayout(new java.awt.BorderLayout());

                    southContainer.setPreferredSize(new java.awt.Dimension(100, 30));
                    southContainer.setLayout(new java.awt.BorderLayout());

                    statusDisplayBar.setPreferredSize(new java.awt.Dimension(160, 19));
                    statusDisplayBar.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        statusDisplayBarActionPerformed(evt);
                              }
                    });
                    southContainer.add(statusDisplayBar, java.awt.BorderLayout.LINE_END);
                    southContainer.add(utillitiesPanel, java.awt.BorderLayout.CENTER);

                    mainContainerCenter.add(southContainer, java.awt.BorderLayout.PAGE_END);

                    jScrollPane1.setViewportView(projectManager);

                    splitContainerCenter.setLeftComponent(jScrollPane1);
                    splitContainerCenter.setRightComponent(tabContainer);

                    mainContainerCenter.add(splitContainerCenter, java.awt.BorderLayout.CENTER);

                    getContentPane().add(mainContainerCenter, java.awt.BorderLayout.CENTER);

                    pack();
          }// </editor-fold>//GEN-END:initComponents

          private void newProjectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectMenuItemActionPerformed
                    // TODO add your handling code here:
          }//GEN-LAST:event_newProjectMenuItemActionPerformed

          private void newFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileMenuItemActionPerformed

                    DialogBox tabNameChooser = new DialogBox((ClosableTabbedPane) this.tabContainer);
                    tabNameChooser.setVisible(true);

          }//GEN-LAST:event_newFileMenuItemActionPerformed

          private void openFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileMenuItemActionPerformed

                    final javax.swing.JFileChooser openFile = new javax.swing.JFileChooser();

                    if (evt.getSource() == openFileMenuItem) {

                              int selectedStatus = openFile.showOpenDialog(this);

                              if (selectedStatus == openFile.APPROVE_OPTION) {

                                        File source = openFile.getSelectedFile();
                                        FileProcessor fileProcessor = new FileProcessor();

                                        String content = fileProcessor.readFromFile(source);
                                        String fileName = fileProcessor.returnFileName(source);

                                        JScrollPane scrollContainer = new JScrollPane(new CustomTextArea(content));

                                        tabContainer.addTab(fileName, scrollContainer);
                                        tabContainer.setSelectedComponent(scrollContainer);
                              }
                    }
          }//GEN-LAST:event_openFileMenuItemActionPerformed

          private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed

                    javax.swing.JFileChooser saveFileChooser = new javax.swing.JFileChooser();

                    if (evt.getSource() == saveAsMenuItem) {

                              int selectedStatus = saveFileChooser.showSaveDialog(this);

                              if (selectedStatus == saveFileChooser.APPROVE_OPTION) {

                                        int selectedIndex = tabContainer.getSelectedIndex();
                                        CustomTextArea tabEditor = (CustomTextArea) (((JScrollPane) tabContainer.getComponentAt(selectedIndex)).getViewport()).getComponent(0);

                                        File destination = saveFileChooser.getSelectedFile();
                                        FileProcessor fileProcessor = new FileProcessor();

                                        fileProcessor.writeToFile(destination, tabEditor);

                              }
                    }


          }//GEN-LAST:event_saveAsMenuItemActionPerformed

          private void statusDisplayBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusDisplayBarActionPerformed
                    // TODO add your handling code here:
          }//GEN-LAST:event_statusDisplayBarActionPerformed

          private void connectionTriggerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionTriggerButtonActionPerformed

                    ConectivityDialogBox cdb = new ConectivityDialogBox();
                    this.connectivityManager = cdb.getConnectivityManager(); // now we have the connectivityManager object and we can use the connectivity object stored inside
                    // to execute any query we want , and to even close the connection when we need to

          }//GEN-LAST:event_connectionTriggerButtonActionPerformed

          private void getTableContentTriggerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTableContentTriggerActionPerformed

                    JDialog prompt = new JDialog();

                    prompt.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    prompt.setSize(300, 150);
                    final Toolkit toolkit = Toolkit.getDefaultToolkit();
                    final Dimension screenSize = toolkit.getScreenSize();
                    final int x = (screenSize.width - prompt.getWidth()) / 2;
                    final int y = (screenSize.height - prompt.getHeight()) / 2;
                    prompt.setLocation(x, y);
                    
                    prompt.setLayout(new FlowLayout());

                    JTextField tableName = new JTextField(5);
                    JLabel nameLabel = new JLabel("Table name : ");

                    JButton okButton = new JButton("OK!");
                    okButton.addActionListener(new ActionListener() {

                              @Override
                              public void actionPerformed(ActionEvent ae) {
                                        
                                        String tblName = tableName.getText();
                                        
                                        try {
                                                  
                                                  connectivityManager.establishDBConnection();
                                                  DataBaseManager dbm = new DataBaseManager(connectivityManager.getConnection());
                                                  JScrollPane scrollConainer = new JScrollPane(new CustomTextArea(dbm.getAllTableContent(tblName)));
                                                  tabContainer.addTab(tblName, scrollConainer);

                                                  prompt.dispose();

                                        } catch (SQLException | ClassNotFoundException ex) {
                                                  Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                              }

                    });

                    prompt.add(nameLabel);
                    prompt.add(tableName);
                    prompt.add(okButton);
                    prompt.setVisible(true);

          }//GEN-LAST:event_getTableContentTriggerActionPerformed

          public static void main(String args[]) {
                    /* Set the Nimbus look and feel */
                    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
                    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
                     */
                    try {
                              for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                                        if ("Nimbus".equals(info.getName())) {
                                                  javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                                  break;
                                        }
                              }
                    } catch (ClassNotFoundException ex) {
                              java.util.logging.Logger.getLogger(Voyager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                              java.util.logging.Logger.getLogger(Voyager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                              java.util.logging.Logger.getLogger(Voyager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                              java.util.logging.Logger.getLogger(Voyager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    //</editor-fold>
                    //</editor-fold>

                    /* Create and display the form */
                    java.awt.EventQueue.invokeLater(new Runnable() {
                              @Override
                              public void run() {

                                        Voyager mainFrame = new Voyager();

                                        mainFrame.setExtendedState(Voyager.MAXIMIZED_BOTH);
                                        mainFrame.setVisible(true);
                              }
                    });
          }

          // Variables declaration - do not modify//GEN-BEGIN:variables
          private javax.swing.JButton connectionTriggerButton;
          private javax.swing.JMenuItem copyMenuItem;
          private javax.swing.JMenuItem cutMenuItem;
          private javax.swing.JMenu editMenu;
          private javax.swing.JMenu fileMenu;
          private javax.swing.JButton getTableContentTrigger;
          private javax.swing.JScrollPane jScrollPane1;
          private javax.swing.JTextField jTextField1;
          private javax.swing.JPanel mainContainerCenter;
          private javax.swing.JMenuBar mainMenuBar;
          private javax.swing.JMenuItem newFileMenuItem;
          private javax.swing.JMenuItem newProjectMenuItem;
          private javax.swing.JMenuItem openFileMenuItem;
          private javax.swing.JMenuItem pasteMenuItem;
          private javax.swing.JTree projectManager;
          private javax.swing.JMenuItem redoMenuItem;
          private javax.swing.JMenuItem saveAsMenuItem;
          private javax.swing.JToolBar shortcutToolBar;
          private javax.swing.JPanel southContainer;
          private javax.swing.JSplitPane splitContainerCenter;
          public static javax.swing.JTextField statusDisplayBar;
          public javax.swing.JTabbedPane tabContainer;
          private javax.swing.JInternalFrame topContainer;
          private javax.swing.JMenuItem undoMenuItem;
          private javax.swing.JPanel utillitiesPanel;
          // End of variables declaration//GEN-END:variables
}