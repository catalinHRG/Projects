/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Catalin H
 */
public class _InsertEntryStrategy implements _InsertDataStrategy{
          
          final String tableName , firstNameEntry , lastNameEntry ;
          final int entryID , ageEntry;
          
          public _InsertEntryStrategy (String tableName, int entryID , String firstNameEntry , String lastNameEntry , int ageEntry) {
                    
                    this.tableName = tableName ;
                    this.entryID = entryID ;
                    this.firstNameEntry = firstNameEntry ;
                    this.lastNameEntry = lastNameEntry ;
                    this.ageEntry = ageEntry ;
          }

          
          
          @Override
          public void insertData(Connection dbConnection) throws SQLException {
                    
                    String query = "Insert INTO " + tableName + 
                   " VALUES ( " + entryID + " , " + firstNameEntry + "  ,  " + lastNameEntry + "  , " + ageEntry + " )";
                    
                    
                    Statement statement = dbConnection.createStatement();
                    statement.executeUpdate(query);
          }
          
}
