/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author Catalin H
 */
public class CustomTextArea extends JTextArea implements  DropTargetListener{
          
          DropTarget dropTarget ;
          
          public CustomTextArea(){
                    
                    dropTarget = new DropTarget(this , this);
                    
          }
          
          @Override
          public void dragEnter(DropTargetDragEvent dtde) {}

          @Override
          public void dragOver(DropTargetDragEvent dtde) {}

          @Override
          public void dropActionChanged(DropTargetDragEvent dtde) {}

          @Override
          public void dragExit(DropTargetEvent dte) {}

          @Override
          public void drop(DropTargetDropEvent dtde) {
                    
                    try {

                                                  Transferable transferableData = dtde.getTransferable();
                                                  DataFlavor[] flavors = transferableData.getTransferDataFlavors();
                                                  
                                                  for (int i = 0; i < flavors.length; i++) {

                                                            if (flavors[i].isFlavorJavaFileListType()) {

                                                                      dtde.acceptDrop(DnDConstants.ACTION_COPY);

                                                                      java.util.List list = (java.util.List) transferableData.getTransferData(flavors[i]);

                                                                      FileReader fr = new FileReader((File) list.get(i));
                                                                      BufferedReader br = new BufferedReader(fr);
                                                                      String buffer;

                                                                      while ((buffer = br.readLine()) != null) {

                                                                                this.append(buffer + "\n");
                                                                      }
                                                                      
                                                                      dtde.dropComplete(true);
                                                                      return;
                                                            }
                                                  }

                                                  dtde.rejectDrop();

                                        } catch (UnsupportedFlavorException | IOException e) {
                                                  e.printStackTrace();
                                                  dtde.rejectDrop();
                                        }
          }
          
}
