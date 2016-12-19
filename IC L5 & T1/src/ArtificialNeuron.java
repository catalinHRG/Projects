/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Catalin H
 */
public class ArtificialNeuron {

          ArrayList<Double> currentWeights = new ArrayList();
          ArrayList<Double> previousWeights = new ArrayList();

          ArrayList<ArrayList<Double>> firstClass = new ArrayList();
          ArrayList<ArrayList<Double>> secondClass = new ArrayList();

          ArrayList<ArrayList<Double>> patterns = new ArrayList();

          ArrayList<Double> expectedValues = new ArrayList();

          double _NET, learningConstant;
          int netCounter = 1, epochCounter = 1, conditionalCounter = 1;

          public ArtificialNeuron(double learningConstant) {

                    this.learningConstant = learningConstant;

          }

          void triggerHebbianLearning() {

                    for (int e = 0; e < 3; e++) {

                              System.out.println("\n---------------- Invatarea Hebbiana Epoca nr " + (e + 1) + " -----------------------\n");

                              for (int i = 0; i < patterns.size(); i++) {

                                        _NET = dotProduct(currentWeights, patterns.get(i)); // current NET value 
                                        System.out.println("NET nr " + netCounter + " este " + _NET);

                                        currentWeights = vectorAddition(currentWeights, scalarProduct(activationFunction(_NET), patterns.get(i))); // next weight vector
                                        System.out.println("Vectorul pondere nr " + (netCounter + 1) + " este " + currentWeights);
                                        netCounter++;

                              }
                    }

          }

          void triggerPerceptronLearning() {

                    System.out.println("\n---------------- Invatarea prin regula Perceptronului -- Epoca nr " + epochCounter + " -----------------------\n");

                    for (int i = 0; i < patterns.size(); i++) {

                              _NET = dotProduct(currentWeights, patterns.get(i)); // current NET value 
                              previousWeights = currentWeights;

                              if (expectedValues.get(i) != activationFunction(_NET)) {

                                        currentWeights = vectorAddition(currentWeights, scalarProduct((expectedValues.get(i) - (activationFunction(_NET))), patterns.get(i))); // next weight vector
                                        System.out.println("NET nr " + netCounter + " este " + _NET + " valoarea asteptata AF este " + expectedValues.get(i) + "\n");
                                        System.out.println("Vectorul pondere nr " + (netCounter + 1) + " este " + currentWeights + "\n");
                                        System.out.println("Pattern-ul corespondent iteratiei curente este X" + (i + 1) + " anume " + patterns.get(i) + "\n");
                                        System.out.println("-------------------------------------------------");
                                        netCounter++;
                                        conditionalCounter = 1;

                                        firstClass = new ArrayList(); // only when we get the expected values , we pile up the correct patterns for this class , since changes have been made , we reset
                                        secondClass = new ArrayList(); // BIS

                              } else {

                                        System.out.println("NET nr " + netCounter + " este " + _NET + " valoarea asteptata AF este " + expectedValues.get(i) + "\n");
                                        System.out.println("Vectorul pondere nr " + (netCounter + 1) + " este " + currentWeights + "\n");
                                        System.out.println("Pattern-ul corespondent iteratiei curente este X" + (i + 1) + " anume " + patterns.get(i) + "\n");
                                        System.out.println("-------------------------------------------------");
                                        netCounter++;
                                        conditionalCounter++;
                                        
                                        if (activationFunction(_NET) == 1) {
                                                  firstClass.add(patterns.get(i));
                                        } else {
                                                  secondClass.add(patterns.get(i));
                                        }
                              }
                    }

                    epochCounter++;

                    if (previousWeights.equals(currentWeights) & conditionalCounter > patterns.size() /* I.E. if the weights throughout one epoch remain unchanged */) {

                              System.out.println("Am gasit ponderile finale! " + currentWeights);

                              System.out.println("Clasa A : ");
                              for (int i = 0; i < firstClass.size(); i++) {
                                        System.out.println(firstClass.get(i));
                              }

                              System.out.println("Clasa B : ");
                              for (int i = 0; i < secondClass.size(); i++) {
                                        System.out.println(secondClass.get(i));
                              }

                    } else {

                              triggerPerceptronLearning();
                    }

          }

          void initWeights(double... weights) {

                    for (int i = 0; i < weights.length; i++) {

                              this.currentWeights.add(weights[i]);
                    }

          }

          void initExpectedValues(double... expectedValues) {

                    for (int i = 0; i < expectedValues.length; i++) {

                              this.expectedValues.add(expectedValues[i]);
                    }

          }

          void initPattern(double... sampleValues) {
                    
                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < sampleValues.length; i++) {

                              temp.add(sampleValues[i]);

                    }

                    this.patterns.add(temp);
          }

          void readFileContent() { // pattern reader tailor-made for the specific file -- iris.csv

                    String buffer;

                    File content = new File("C:\\Users\\Catalin H\\Desktop\\TXT files\\iris.csv");
                    FileReader contentRead;

                    try {

                              contentRead = new FileReader(content);
                              BufferedReader reader = new BufferedReader(contentRead);

                              while ((buffer = reader.readLine()) != null) {

                                        String[] rows = buffer.split(",");
                                        ArrayList<Double> temp = new ArrayList();

                                        for (int j = 0; j < rows.length; j++) {

                                                  if (j < 4) {

                                                            temp.add(Double.parseDouble(rows[j]));
                                                  }
                                        }

                                        patterns.add(temp);
                              }

                    } catch (FileNotFoundException e) {

                              System.out.println("Could not find .csv file !! ");

                    } catch (IOException e) {

                              System.out.println(" I/O Exception ");
                    }

          }

          void readPatterns(String filePath) { // the more generic function for reading patterns from a plaint .txt file with PATTERNS ONLY per row , comma separated values per pattern

                    String buffer;

                    File content = new File(filePath);
                    FileReader contentRead;

                    try {

                              contentRead = new FileReader(content);
                              BufferedReader reader = new BufferedReader(contentRead);

                              while ((buffer = reader.readLine()) != null) {

                                        String[] rows = buffer.split(",");
                                        ArrayList<Double> temp = new ArrayList();

                                        for (int j = 0; j < rows.length; j++) {

                                                  temp.add(Double.parseDouble(rows[j]));

                                        }

                                        patterns.add(temp);
                              }

                    } catch (FileNotFoundException e) {

                              System.out.println("Could not find .csv file !! ");

                    } catch (IOException e) {

                              System.out.println(" I/O Exception ");
                    }
          }

          double dotProduct(ArrayList<Double> weights, ArrayList<Double> pattern) {

                    double temp = 0.0;

                    for (int i = 0; i < weights.size(); i++) {

                              temp += weights.get(i) * pattern.get(i);
                    }

                    return temp;
          }

          double activationFunction(double net_Input) {

                    if (net_Input < 0) {
                              return -1;
                    } else if (net_Input > 0) {
                              return 1;
                    } else {

                              System.out.println("Functia nu este definita pentru valoarea 0 !");
                              System.exit(0);
                              return 0; // irelevant
                    }
          }

          ArrayList<Double> vectorAddition(ArrayList<Double> weights, ArrayList<Double> pattern) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < weights.size() | i < pattern.size(); i++) {

                              temp.add(weights.get(i) + pattern.get(i));
                    }

                    return temp;
          }

          ArrayList<Double> vectorSubtraction(ArrayList<Double> weights, ArrayList<Double> pattern) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < weights.size(); i++) {

                              temp.add(weights.get(i) - pattern.get(i));
                    }

                    return temp;
          }

          ArrayList<Double> scalarProduct(double /* The value resulted from the activationFunction */ afr, ArrayList<Double> pattern) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < pattern.size(); i++) {

                              temp.add(learningConstant * afr * pattern.get(i));
                    }

                    return temp;
          }

          public static void main(String[] args) {

                    ArtificialNeuron aN1 = new ArtificialNeuron(1 /* <--- This is the learning constant*/);
                    aN1.initWeights(1, -1, 0, 0.5); // W
                    aN1.initPattern(1, -2, 1.5, 0); // X1
                    aN1.initPattern(1, -0.5, -2, -1.5); // X2
                    aN1.initPattern(0, 1, -1, 1.5); // X3
                    //aN1.triggerHebbianLearning();

                    ArtificialNeuron aN2 = new ArtificialNeuron(1 /* <--- This is the learning constant*/);
                    aN2.initWeights(1, -1); // W
                    aN2.initPattern(1, -2); // X1
                    aN2.initPattern(0, 1); // X2
                    aN2.initPattern(2, 3); // X3
                    aN2.initPattern(1, -1); // X4
                    //aN2.triggerHebbianLearning();

                    ArtificialNeuron aN3 = new ArtificialNeuron(0.1 /* <--- This is the learning constant*/);
                    aN3.initWeights(1, -1, 0, 0.5);
                    aN3.initPattern(1, -2, 0, -1); // X1
                    aN3.initPattern(0, 1.5, -0.5, -1); // X2
                    aN3.initPattern(-1, 1, 0.5, -1); // X3
                    aN3.initExpectedValues(-1, -1, 1);
                    //aN3.triggerPerceptronLearning();

                    ArtificialNeuron aN4 = new ArtificialNeuron(1 /* <--- This is the learning constant*/);
                    for (int i = 0; i < 150; i++) { // First 50 patters are expected to be of class 1 and the rest of class -1

                              if (i < 50) {
                                        aN4.expectedValues.add(1D);
                                        /* expected values for the first 50 patterns , setosa -- class 1 */ } else {
                                        aN4.expectedValues.add(-1D);
                                        /* expected values for the remaining 100 patterns , versicolor & virginica -- class -1*/ }
                    }
                    aN4.initWeights(3, 4, -2, 0.5);
                    aN4.readFileContent();
                    aN4.triggerPerceptronLearning();

          }

}
