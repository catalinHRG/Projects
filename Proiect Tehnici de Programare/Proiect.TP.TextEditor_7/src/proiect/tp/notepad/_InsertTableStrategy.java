/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//      Cam asa arata tabelul ce va fi creat , userul trebuie doar sa dea numele celor 4 coloane

//                   "CREATE TABLE REGISTRATION " +
//                   "(id INTEGER not NULL, " +
//                   " first VARCHAR(255), " + 
//                   " last VARCHAR(255), " + 
//                   " age INTEGER, " + 
//                   " PRIMARY KEY ( id ))"



/**
 *
 * @author Catalin H
 */
public class _InsertTableStrategy implements _InsertDataStrategy{
           
          final String tableName , firstColumnName /* Primary key*/ , secondColumnName , thirdColumnName , fourthColumnName;
          
          public _InsertTableStrategy (String tableName , String firstColumnName , String secondColumnName , String thirdColumnName , String fourthColumnName) {
                    
                    this.tableName = tableName ;
                    this.firstColumnName = firstColumnName;
                    this.secondColumnName = secondColumnName ;
                    this.thirdColumnName = thirdColumnName ;
                    this.fourthColumnName = fourthColumnName ;
          }
          
          
          @Override
          public void insertData(Connection dbConnection) throws SQLException {
                    
                    String query = "Create Table " + tableName + " ( " + 
                            firstColumnName + " Integer not Null , " + 
                            secondColumnName + " VARCHAR(255), " + 
                            thirdColumnName + " VARCHAR(255), " + 
                            fourthColumnName + " INTEGER , "
                            + " Primary key ( " + firstColumnName + " ) )";
                    
                    Statement statement = dbConnection.createStatement();
                    statement.executeUpdate(query);
          }
          
}
