/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;

/**
 *
 * @author Catalin H
 */
public class _Prompt extends JDialog {

          public _Prompt(JTabbedPane tabContainer , int widthAdjust , int heightAdjust) {

                    this.setSize(tabContainer.getSize().width / widthAdjust, tabContainer.getSize().height / widthAdjust);
                    this.setLocation(tabContainer.getLocationOnScreen().x + tabContainer.getWidth() / heightAdjust,
                            tabContainer.getLocationOnScreen().y + tabContainer.getHeight() / heightAdjust);
                    this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

          }
}
