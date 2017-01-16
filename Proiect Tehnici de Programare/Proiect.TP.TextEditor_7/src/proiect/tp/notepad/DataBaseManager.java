/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Catalin H
 */

public class DataBaseManager {

          private _FetchDataStrategy fetchDataStrategy ;
          private _InsertDataStrategy insertDataStrategy ;
          
          
          public DataBaseManager (_FetchDataStrategy fetchDataStrategy , _InsertDataStrategy insertDataStrategy) {
                    
                    this.fetchDataStrategy = fetchDataStrategy ;
                    this.insertDataStrategy = insertDataStrategy;
          }
          
          public DataBaseManager (_FetchDataStrategy fetchDataStrategy ) { this.fetchDataStrategy = fetchDataStrategy ; }
          
          public DataBaseManager (_InsertDataStrategy insertDataStrategy ) {this.insertDataStrategy = insertDataStrategy ;}

          
          
          
          public String execute_FetchQuery (Connection dbConnection) throws SQLException {
                    
                    return fetchDataStrategy.fetchData(dbConnection);
          }
          
          public void execute_InsertQuery (Connection dbConnection) throws SQLException {
                    
                    insertDataStrategy.insertData(dbConnection);
          }
          
          
          
          // setter methods for the two behavioral models ;
          
          public void setFetchDataStrategy (_FetchDataStrategy fetchDataStrategy) {
                    
                    this.fetchDataStrategy = fetchDataStrategy ;
          }
          
          public void setInsertDataStrategy (_InsertDataStrategy insertDataStrategy) {
                    this.insertDataStrategy = insertDataStrategy ;
          }
          
}
