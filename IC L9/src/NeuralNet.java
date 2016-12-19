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
import java.util.Random;

/**
 *
 * @author Catalin H
 */
public class NeuralNet {

          double BIAS = -1;
          int MIN_RANGE = -1, MAX_RANGE = 1; // for the weight initialization
          int NUMBER_OF_PERCEPTRONS = 3;
          int PATTERN_SIZE = 2; 

          int epochCounter = 1;
          double learningRate = 3.7;

          double ERROR_MARGIN = 0.001;
          double MAX_ERROR ; 
          double CUMULATIVE_ERROR;

          ArrayList<ArrayList<Double>> weights, patterns  , normalizedPatterns , actualOutputs , desiredOutputs;
          
          public NeuralNet() {

                    patterns = new ArrayList();
                    weights = new ArrayList();
                    desiredOutputs = new ArrayList();
                    actualOutputs = new ArrayList();
                    normalizedPatterns = new ArrayList();
          }

          void trainNeuralNet() {

                    readPatterns("C:\\Users\\Catalin H\\Desktop\\TXT files\\Furniture.txt");
                    normalizePatterns();
                    initWeights(PATTERN_SIZE++);
                    MAX_ERROR = ERROR_MARGIN * NUMBER_OF_PERCEPTRONS * (PATTERN_SIZE++);

                    while (true) {

                              System.out.println("--------------------------------------");
                              System.out.println("EPOCA NR " + epochCounter);
                              System.out.println("--------------------------------------");

                              CUMULATIVE_ERROR = 0;
                              triggerPerceptronLearning();

                              if (CUMULATIVE_ERROR < MAX_ERROR) {

                                        System.out.println("Ponderile finale sunt : ");
                                        printField(weights);
                                        System.out.println("Iesirile dorite sunt : ");
                                        printField(desiredOutputs);
                                        System.out.println("Iesirile actuale sunt : ");
                                        printField(actualOutputs);
                                        break;

                              }

                              epochCounter++;
                    }
          }

          void triggerPerceptronLearning() {

                    actualOutputs = new ArrayList();

                    for (int i = 0; i < patterns.size(); i++) {

                              System.out.println("-----------------------------------------------------------------------");
                              //System.out.println("Pattern ul nr" + (i + 1) + " este " + normalizedPatterns.get(i) + " are urmatoarele iesiri dorite " + desiredOutputs.get(i));
                              //System.out.println("Pentru ponderile ");
                              //printField(weights);
                              System.out.println("Avem urmatoarele iesiri actuale : ");
                              computeActualOutputs(normalizedPatterns.get(i));
                              System.out.println(actualOutputs.get(i));
                              updateWeights(normalizedPatterns.get(i), actualOutputs.get(i), desiredOutputs.get(i));
                              updateCumulativeError(actualOutputs.get(i), desiredOutputs.get(i));
                              System.out.println("Eroarea cumulative este : " + CUMULATIVE_ERROR + " Eroarea maxima este : " + MAX_ERROR);
                    }
          }

          void updateCumulativeError(ArrayList<Double> aO, ArrayList<Double> dO) {

                    for (int i = 0; i < aO.size(); i++) {

                              CUMULATIVE_ERROR += Math.pow(aO.get(i) - dO.get(i), 2);
                    }
          }

          void updateWeights(ArrayList<Double> pattern, ArrayList<Double> aO, ArrayList<Double> dO) {

                    ArrayList<Double> temp;

                    for (int i = 0; i < weights.size(); i++) {

                              temp = new ArrayList();

                              for (int j = 0; j < weights.get(i).size(); j++) {
                                        temp.add(
                                                weights.get(i).get(j)
                                                + (learningRate * (dO.get(i) - aO.get(i)) * (1 - Math.pow(aO.get(i), 2)) * pattern.get(j))
                                        );
                              }

                              weights.set(i, temp);
                    }

                    //System.out.println("Ponderile dupa actualizare sunt : ");
                    //printField(weights);
          }

          void computeActualOutputs(ArrayList<Double> pattern) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < weights.size(); i++) {

                              temp.add(sigmoid(dotProduct(pattern, weights.get(i))));
                    }

                    actualOutputs.add(temp);

                    //System.out.println("Iesirile actuale sunt : ");
                    //printField(actualOutputs);
          }

          void normalizePatterns() {

                    ArrayList<Double> normalizedPattern;
                    for (int i = 0; i < patterns.size(); i++) {

                              normalizedPattern = new ArrayList();
                              double temp = computeMagnitude(patterns.get(i));

                              for (int j = 0; j < patterns.get(i).size(); j++) {

                                        normalizedPattern.add(patterns.get(i).get(j) / temp);
                              }

                              normalizedPatterns.add(normalizedPattern);
                    }

          }

          double computeMagnitude(ArrayList<Double> pattern) {

                    double temp = 0;

                    for (int i = 0; i < pattern.size(); i++) {

                              temp += Math.pow(pattern.get(i), 2);
                    }

                    return Math.sqrt(temp);
          }

          void initWeights(int patternSize) {

                    Random rdg = new Random();
                    ArrayList<Double> temp;

                    for (int i = 0; i < NUMBER_OF_PERCEPTRONS; i++) {

                              temp = new ArrayList();

                              for (int j = 0; j < patternSize; j++) {

                                        temp.add(MIN_RANGE + (MAX_RANGE - MIN_RANGE) * rdg.nextDouble());
                              }

                              weights.add(temp);
                    }

          }

          void readPatterns(String filePath) {

                    String buffer;

                    File content = new File(filePath);
                    FileReader contentRead;

                    try {

                              contentRead = new FileReader(content);
                              BufferedReader reader = new BufferedReader(contentRead);

                              while ((buffer = reader.readLine()) != null) {

                                        loadPattern(buffer, 2);
                              }

                    } catch (FileNotFoundException e) {

                              System.out.println("Could not find .txt file !! ");

                    } catch (IOException e) {

                              System.out.println(" I/O Exception ");
                    }
          }

          void loadPattern(String rowElements, int patternSize) {

                    String[] pattern = rowElements.split(",");

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < patternSize; i++) {

                              temp.add(Double.parseDouble(pattern[i])); // converting the pattern elements into an integer array
                    }

                    temp.add(BIAS);
                    patterns.add(temp); // loading the pattern

                    temp = new ArrayList();
                    for (int i = patternSize; i < pattern.length; i++) {

                              temp.add(Double.parseDouble(pattern[i])); // converting the desired outputs into an integer array
                    }

                    desiredOutputs.add(temp); // loading the desired outputs associated with this pattern

          }

          double sigmoid(double current_NET) {

                    return ((2 / (1 + Math.pow(Math.E, (-1 * current_NET)))) - 1);
          }

          double dotProduct(ArrayList<Double> pattern, ArrayList<Double> weights) {

                    double temp = 0.0;

                    for (int i = 0; i < weights.size(); i++) {

                              temp += weights.get(i) * pattern.get(i);
                    }
                    return temp;
          }

          void printField(ArrayList<?> field) {

                    for (int i = 0; i < field.size(); i++) {

                              System.out.println(" Elementul nr " + i +  " este " + field.get(i));
                              
                    }

                    //System.out.println("-------------------------------------");
          }

          public static void main(String[] args) {

                    NeuralNet nn = new NeuralNet();
                    nn.trainNeuralNet();

          }

}
