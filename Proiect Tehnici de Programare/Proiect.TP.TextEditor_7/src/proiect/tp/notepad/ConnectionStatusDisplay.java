/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JPanel;

/**
 *
 * @author Catalin H
 */
public class ConnectionStatusDisplay extends JPanel {

          private Connection dataBaseConnection;

          public ConnectionStatusDisplay(Connection con) throws SQLException {

                    if (con.isValid(5)) {

                              this.dataBaseConnection = con;
                              this.setBackground(Color.GREEN);
                              
                              
                    } else {
                              
                              this.setBackground(Color.RED);
                    }
                    
                    this.repaint();

          }

          public ConnectionStatusDisplay() {

                    this.setBackground(Color.RED);
          }

          public Connection getConnection() {

                    return dataBaseConnection;
          }
}
