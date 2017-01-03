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
public class DataBaseManager {

          private final Connection connectionToDB;

          public DataBaseManager(Connection connectionToDB) {

                    this.connectionToDB = connectionToDB;
          }

          public String getAllTableContent(String tableName) throws SQLException {

                    StringBuilder temp = new StringBuilder();
                    Statement statement = connectionToDB.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from " + tableName);
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int columnsCount = rsmd.getColumnCount();

                    while (resultSet.next()) {
                              
                              for (int i = 1; i <= columnsCount; i++) { 
                                        
                                        temp.append(resultSet.getObject(i));
                                        temp.append(" ");
                                        
                              }
                              
                              temp.append ("\n");
                    }

                    return temp.toString();
          }

}
