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
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Catalin H
 */
public class DropData { // used to fetch the data ; it is making use of the drop event that triggered the procedure in the first place ; fetces and stores the data , for later use
          
          public String fileName , fileContent;
          
          public void dataTransfer(DropTargetDropEvent evt) {

                    try {

                              Transferable transferableData = evt.getTransferable();
                              DataFlavor[] flavors = transferableData.getTransferDataFlavors();

                              for (int i = 0; i < flavors.length; i++) {

                                        if (flavors[i].isFlavorJavaFileListType()) {

                                                  evt.acceptDrop(DnDConstants.ACTION_COPY);
                                                  java.util.List list = (java.util.List) transferableData.getTransferData(flavors[i]);

                                                  File source = (File) list.get(i);
                                                  
                                                  FileProcessor fileProcessor = new FileProcessor();
                                                  fileContent = fileProcessor.readFromFile(source);
                                                  fileName = fileProcessor.returnFileName(source);

                                                  evt.dropComplete(true);
                                                  return;
                                        }
                              }

                              evt.rejectDrop();

                    } catch (UnsupportedFlavorException | IOException e) {
                              
                              System.out.println(e);
                              evt.rejectDrop();
                    }
          }
}
