/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Catalin H
 */
public class ServerConnectivityManager {
          
          private final String username;
          private final String password;
          private final String address;
          
          private Connection dataBaseConnection;
          
          public ServerConnectivityManager(String username , String password , String address) {

                    this.username = username;
                    this.password = password;
                    this.address = address;
          }
          
          public Connection getConnection(){
                    
                    return dataBaseConnection;
          }
          
          public void establishDBConnection() throws ClassNotFoundException, SQLException{
                    
                    String dataBaseURL = "jdbc:mysql://localhost/" + address;
                    
                    Class.forName("com.mysql.jdbc.Driver");
                    dataBaseConnection = DriverManager.getConnection(dataBaseURL , username,  password);
          }
          
          public void closeDBConnection() throws SQLException{
                    
                    dataBaseConnection.close();
          }
          // this should have methods to create the connection , close the connection -- ideally , we need the connection object to be returned,  in order to execute querries with another object
}
