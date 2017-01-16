/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiect.tp.notepad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Catalin H
 */
public class _FetchTableStrategy implements _FetchDataStrategy{
          
          final String tableName ;
          
          
          public _FetchTableStrategy (String tableName) {
                    
                    this.tableName = tableName ;
          }

          
          @Override
          public String fetchData(Connection dbConnection) throws SQLException{
                    
                    StringBuilder temp = new StringBuilder();
                    
                    Statement statement = dbConnection.createStatement(); 
                    ResultSet resultSet = statement.executeQuery("Select * From " + tableName);
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int columnsCount = rsmd.getColumnCount();
                    
                    
                    while (resultSet.next()) {

                              for (int i = 1; i <= columnsCount; i++) {
                                        
                                        temp.append(resultSet.getObject(i));
                                        temp.append("   ");

                              }

                              temp.append("\n");
                    }

                    return temp.toString();
          }

}
