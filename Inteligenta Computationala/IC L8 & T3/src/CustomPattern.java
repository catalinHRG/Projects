/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Catalin H
 */
public class CustomPattern {
          
          ArrayList<Integer> coordinates ;
          int desiredOutput ;
          double actualOutput ;
          
          public CustomPattern () {
                    
                    coordinates = new ArrayList();
                    desiredOutput = 0;
                    actualOutput = 0D;
          }
          
          void addPatternElement (int element) {
                    
                    coordinates.add(element);
          } 
          
          void setDesiredOutput (int desiredOutput) {
                    
                    this.desiredOutput = desiredOutput;
          }
}
