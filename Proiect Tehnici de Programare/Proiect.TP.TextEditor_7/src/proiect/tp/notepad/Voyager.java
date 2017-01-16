/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
                                                  caretUpdateMethod(ce);
                                        }

                              }
                              );

                    }

                    public CustomTextArea() {

                              dropTarget = new DropTarget(this, this);

                              this.addCaretListener(new CaretListener() {
                                        @Override
                                        public void caretUpdate(CaretEvent ce) {
                                                  caretUpdateMethod(ce);
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

                    private void caretUpdateMethod(CaretEvent ce) {

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
                    getEntryTrigger = new javax.swing.JButton();
                    insertTableTrigger = new javax.swing.JButton();
                    insertEntryTrigger = new javax.swing.JButton();
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

                    getEntryTrigger.setText("Get Entry");
                    getEntryTrigger.setFocusable(false);
                    getEntryTrigger.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    getEntryTrigger.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    getEntryTrigger.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        getEntryTriggerActionPerformed(evt);
                              }
                    });
                    shortcutToolBar.add(getEntryTrigger);

                    insertTableTrigger.setText("Insert Table");
                    insertTableTrigger.setFocusable(false);
                    insertTableTrigger.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    insertTableTrigger.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    insertTableTrigger.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        insertTableTriggerActionPerformed(evt);
                              }
                    });
                    shortcutToolBar.add(insertTableTrigger);

                    insertEntryTrigger.setText("Insert Entry");
                    insertEntryTrigger.setFocusable(false);
                    insertEntryTrigger.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    insertEntryTrigger.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    insertEntryTrigger.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        insertEntryTriggerActionPerformed(evt);
                              }
                    });
                    shortcutToolBar.add(insertEntryTrigger);

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

          // triggerele pentru butoane

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
//
//                    try {
//                              connectivityManager.establishDBConnection();
//
//                    } catch (ClassNotFoundException | SQLException ex) {
//                              Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    ArrayList<String> tableNames = new ArrayList();
//
//                    String query = "SELECT * FROM sys.Tables ; SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES "
//                            + "WHERE TABLE_TYPE = `BASE TABLE` AND TABLE_SCHEMA=`sakila` ; " ;           // e ceva in neregula cu query ul !?!!!
//
//                    
//
//                    try {
//
//                              Statement statement = connectivityManager.getConnection().createStatement();
//
//                              ResultSet resultSet = statement.executeQuery(query);
//                              ResultSetMetaData rsmd = resultSet.getMetaData();
//                              int columnsCount = rsmd.getColumnCount();
//
//                              while (resultSet.next()) {
//
//                                        tableNames.add(resultSet.getString("TABLE_NAME"));
//
//                              }
//
//                    } catch (SQLException ex) {
//                              Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    System.out.println(tableNames); // de listat in JTree cand se stabileste conexiunea

          }//GEN-LAST:event_connectionTriggerButtonActionPerformed

          // Strategy Pattern implementation 

          private void getTableContentTriggerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTableContentTriggerActionPerformed

                    final int textFieldSize = 15;

                    _Prompt prompt = new _Prompt(tabContainer, 4, 3);
                    prompt.setLayout(new FlowLayout());

                    JLabel promptLabel = new JLabel("Enter the name of the table : ");
                    JTextField tableName = new JTextField(textFieldSize);
                    JButton okButton = new JButton("OK!");

                    prompt.add(promptLabel);
                    prompt.add(tableName);

                    okButton.addActionListener(new java.awt.event.ActionListener() {

                              @Override
                              public void actionPerformed(ActionEvent ae) {

                                        DataBaseManager dbm = new DataBaseManager(new _FetchTableStrategy(tableName.getText()));
                                        try {

                                                  connectivityManager.establishDBConnection();

                                                  JScrollPane scrollContainer = new JScrollPane(new CustomTextArea(dbm.execute_FetchQuery(connectivityManager.getConnection())));
                                                  tabContainer.addTab(tableName.getText(), scrollContainer);
                                                  tabContainer.setSelectedComponent(scrollContainer);

                                                  connectivityManager.closeDBConnection();
                                                  prompt.dispose();

                                        } catch (SQLException | ClassNotFoundException ex) {
                                                  Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                              }

                    });

                    prompt.add(okButton);
                    prompt.setVisible(true);


          }//GEN-LAST:event_getTableContentTriggerActionPerformed

          private void getEntryTriggerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getEntryTriggerActionPerformed

                    final int tableNameFieldSize = 7;
                    final int entryIDFieldSize = 7;

                    _Prompt prompt = new _Prompt(tabContainer, 4, 3);
                    prompt.setLayout(new FlowLayout());

                    JLabel promptLabel = new JLabel("Enter the name of the table : ");
                    JTextField tableName = new JTextField(tableNameFieldSize);
                    JTextField entryID = new JTextField(entryIDFieldSize);
                    JButton okButton = new JButton("OK!");

                    prompt.add(promptLabel);
                    prompt.add(tableName);
                    prompt.add(entryID);

                    okButton.addActionListener(new java.awt.event.ActionListener() {

                              @Override
                              public void actionPerformed(ActionEvent ae) {

                                        DataBaseManager dbm = new DataBaseManager(new _FetchEntryStrategy(tableName.getText(), Integer.parseInt(entryID.getText())));

                                        try {

                                                  connectivityManager.establishDBConnection();

                                                  CustomTextArea prop = new CustomTextArea(dbm.execute_FetchQuery(connectivityManager.getConnection()));
                                                  _Prompt popUp = new _Prompt(tabContainer, 4, 3);
                                                  popUp.setLayout(new FlowLayout());

                                                  popUp.add(prop);
                                                  popUp.setVisible(true);
                                                  prompt.dispose();

                                                  connectivityManager.closeDBConnection();

                                        } catch (ClassNotFoundException | SQLException ex) {
                                                  Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                              }

                    });

                    prompt.add(okButton);
                    prompt.setVisible(true);

          }//GEN-LAST:event_getEntryTriggerActionPerformed

          private void insertEntryTriggerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertEntryTriggerActionPerformed

                    // Nu gasesc un query cu care sa pot insera cum trebuie .........................
                    DataBaseManager dbm = new DataBaseManager(new _InsertEntryStrategy("actor", 1, "Catalin", "Herghelegiu", 25));

                    try {

                              connectivityManager.establishDBConnection();
                              dbm.execute_InsertQuery(connectivityManager.getConnection());

                    } catch (ClassNotFoundException | SQLException ex) {
                              Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
                    }

          }//GEN-LAST:event_insertEntryTriggerActionPerformed

          private void insertTableTriggerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertTableTriggerActionPerformed

                    final int fieldSize = 15;

                    _Prompt prompt = new _Prompt(tabContainer, 2, 2);
                    prompt.setLayout(new GridBagLayout());

                    JLabel tableNameLabel = new JLabel("Table name : ");
                    JLabel firstColumnLabel = new JLabel("Column 1 : ");
                    JLabel secondColumnLabel = new JLabel("Column 2 : ");
                    JLabel thirdColumnLabel = new JLabel("Column 3 : ");
                    JLabel fourthColumnLabel = new JLabel("Column 4 : ");

                    JTextField tableNameField = new JTextField(fieldSize);
                    JTextField firstColumnName = new JTextField(fieldSize);
                    JTextField secondColumnName = new JTextField(fieldSize);
                    JTextField thirdColumnName = new JTextField(fieldSize);
                    JTextField fourthColumnName = new JTextField(fieldSize);

                    JButton okButton = new JButton("OK!");

                    okButton.addActionListener(new java.awt.event.ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent ae) {

                                        DataBaseManager dbm = new DataBaseManager(new _InsertTableStrategy(tableNameField.getText(), firstColumnName.getText(), secondColumnName.getText(), thirdColumnName.getText(), fourthColumnName.getText()));

                                        try {

                                                  connectivityManager.establishDBConnection();
                                                  dbm.execute_InsertQuery(connectivityManager.getConnection());
                                                  prompt.dispose();

                                        } catch (ClassNotFoundException | SQLException ex) {
                                                  Logger.getLogger(Voyager.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                              }
                    });

                    GridBagConstraints gc = new GridBagConstraints();

                    gc.gridx = 0;
                    gc.gridy = 0;
                    gc.anchor = GridBagConstraints.LINE_START;
                    prompt.add(tableNameLabel, gc);

                    gc.gridy = 1;
                    prompt.add(firstColumnLabel, gc);

                    gc.gridy = 2;
                    prompt.add(secondColumnLabel, gc);

                    gc.gridy = 3;
                    prompt.add(thirdColumnLabel, gc);

                    gc.gridy = 4;
                    prompt.add(fourthColumnLabel, gc);

                    gc.gridx = 1;
                    gc.gridy = 0;
                    //gc.anchor = GridBagConstraints.LINE_END;
                    prompt.add(tableNameField, gc);

                    gc.gridy = 1;
                    prompt.add(firstColumnName, gc);

                    gc.gridy = 2;
                    prompt.add(secondColumnName, gc);

                    gc.gridy = 3;
                    prompt.add(thirdColumnName, gc);

                    gc.gridy = 4;
                    prompt.add(fourthColumnName, gc);

                    gc.gridy = 5;
                    prompt.add(okButton, gc);

                    prompt.setVisible(true);
          }//GEN-LAST:event_insertTableTriggerActionPerformed

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
          private javax.swing.JButton getEntryTrigger;
          private javax.swing.JButton getTableContentTrigger;
          private javax.swing.JButton insertEntryTrigger;
          private javax.swing.JButton insertTableTrigger;
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
