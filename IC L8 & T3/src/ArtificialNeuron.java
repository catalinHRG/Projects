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
public class ArtificialNeuron {
          
          ArrayList< Double > weights ;
          ArrayList<CustomPattern> patterns = new ArrayList();

          void updateWeights (ArrayList<Double> currentWeights) {
                    
                    this.weights = currentWeights;
          }
          
          void loadPattern (String row) {
                    
                    String[] rowElements = row.split(",");
                    
                    CustomPattern temp = new CustomPattern() ;
                    
                    for (int i = 0 ; i < rowElements.length - 1 ; i ++) {
                              
                              temp.addPatternElement(Integer.parseInt( rowElements[i] ));
                              
                    }
                    
                    temp.desiredOutput = Integer.parseInt(rowElements[rowElements.length-1]);
                    patterns.add(temp);
          }
          
          void triggerPerceptronLearning() {
                    
                    double _NET;
                    
                    for (int i = 0 ; i < patterns.size() ; i ++) {
                              
                              System.out.print("Pentru pattern ul : " + patterns.get(i).coordinates + " avem : ") ;
                              
                              _NET = dotProduct(weights , patterns.get(i).coordinates);
                              CustomPattern temp = patterns.get(i);
                              temp.actualOutput = sigmoid(_NET);
                              patterns.set(i, temp);
                              
                              System.out.println(" Actual output =  " + patterns.get(i).actualOutput + " --- Desired output = " + patterns.get(i).desiredOutput);
                    }
                    
                    System.out.println("--------------------------------------");
          }
          
          double sigmoid (double current_NET) {
                    
                    return ( ( 2  /  ( 1 + Math.pow(Math.E,(-1*current_NET) ) ) ) - 1);
          }
          
          double dotProduct(ArrayList<Double> weights, ArrayList<Integer> pattern) {

                    double temp = 0.0;

                    for (int i = 0; i < weights.size(); i++) {

                              temp += weights.get(i) * pattern.get(i);
                    }
                    return temp;
          }
}
