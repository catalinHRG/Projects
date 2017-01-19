
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Catalin H
 */
public class NeuralNet {

          private final int Nr_Of_ArtificialNeurons;
          private final int patternSize;
          private int epochCounter = 1;
          private final int MIN_RANGE = -1, MAX_RANGE = 1;

          private final double learningRate;
          private final double BIAS = -1;
          private final double maxError;
          private double cumulativeError;

          private final String filePath;

          private ArrayList< ArrayList<Double>> weights = new ArrayList();
          private ArrayList< ArrayList<Double>> actualOutputs = new ArrayList();
          private ArrayList<ArrayList<Double>> patterns = new ArrayList();
          private final ArrayList<ArrayList<Double>> desiredOutputs = new ArrayList();

          public NeuralNet(int Nr_Of_ArtificialNeurons, double learningRate, int patternSize, double errorMargin, String filePath) {

                    this.Nr_Of_ArtificialNeurons = Nr_Of_ArtificialNeurons;
                    this.learningRate = learningRate;
                    this.patternSize = patternSize;
                    this.maxError = errorMargin * Nr_Of_ArtificialNeurons ;
                    this.filePath = filePath;

          }

          public void trainNeuralNet(String mode) {

                    readPatterns(filePath);
                    weights = initWeights(patternSize + 1);

                    if (mode.equals("Normalize")) {

                              patterns = normalizePatterns(patterns);
                    }
                    
                    
//                    System.out.println("Pattern urile normalizate sunt : ");
//                    printField(patterns);
//                    
//                    System.out.println("Pattern urile sunt : ");
//                    printField(patterns);
//                    System.out.println("Ponderile initiale sunt : ");
//                    printField(weights);
//                    System.out.println("iesirile dorite sunt : ");
//                    printField(desiredOutputs);

                    while (true) {

                              System.out.println("--------------------------------------");
                              System.out.println("EPOCA NR " + epochCounter);
                              System.out.println("--------------------------------------");

                              cumulativeError = 0;

                              triggerDeltaRuleLearning(weights);

                              if (cumulativeError < maxError) {

                                        System.out.println("---------------------------------------------------------");
                                        System.out.println("Ponderile finale sunt : ");
                                        printField(weights);
                                        System.out.println("Iesirile dorite sunt : ");
                                        printField(desiredOutputs);
                                        System.out.println("Iesirile actuale sunt : ");
                                        printField(actualOutputs);
                                        break;

                              }

                              System.out.println("---------------------------------------------------------");
                              System.out.println("---------------------------------------------------------");
                              System.out.println(" ******************* Eroarea cumulativa la sfarsitul epocii este : " + cumulativeError + " iar eroarea maxima admista este : " + maxError);
                              System.out.println("---------------------------------------------------------");
                              System.out.println("---------------------------------------------------------");
                              epochCounter++;
                    }
          }

          public void triggerDeltaRuleLearning(ArrayList<ArrayList<Double>> _Weights) {

                    actualOutputs = new ArrayList();

                    for (int i = 0; i < patterns.size(); i++) {

                              System.out.println("-----------------------------------------------------------------------");
                              System.out.println("Pattern-ul " + (i+1) + ". " + patterns.get(i) + " --- avem urmatoarea iesire actuala : ");
                              System.out.println("-----------------------------------------------------------------------");

                              actualOutputs.add(computeActualOutputs(patterns.get(i), _Weights));

                              printField(actualOutputs.get(i));

                              System.out.println("Iesire asteptata : ");

                              printField(desiredOutputs.get(i));

                              System.out.println("Ponderile inainte de actualizare sunt : ");
                              printField(weights);

                              weights = computeWeights(patterns.get(i), actualOutputs.get(i), desiredOutputs.get(i));

                              System.out.println("Ponderile actualizate sunt : ");
                              printField(weights);

                              cumulativeError += updateCumulativeError(actualOutputs.get(i), desiredOutputs.get(i));

                              System.out.println("Eroarea cumulative este : " + cumulativeError + " Eroarea maxima este : " + maxError);
                    }

          }
          
          
          // de scris o functie care pe baza ponderilor obtinute in urma instruirii , sa faca clasfificari .

          public double updateCumulativeError(ArrayList<Double> aO, ArrayList<Double> dO) {

                    double temp = 0D;

                    for (int i = 0; i < dO.size(); i++) {

                              temp += Math.pow(aO.get(i) - dO.get(i), 2);
                    }

                    return temp;
          }

          public ArrayList<ArrayList<Double>> computeWeights(ArrayList<Double> pattern, ArrayList<Double> aO, ArrayList<Double> dO) {

                    ArrayList<Double> temp1; // the actual weight that geos into the weight list
                    ArrayList<ArrayList<Double>> temp2 = new ArrayList(); // the weight list

                    for (int i = 0; i < weights.size(); i++) {

                              temp1 = new ArrayList();

                              for (int j = 0; j < weights.get(i).size(); j++) {

                                        temp1.add(
                                                weights.get(i).get(j)
                                                + (learningRate * (dO.get(i) - aO.get(i)) * (1 - Math.pow(aO.get(i), 2)) * pattern.get(j))
                                        );
                              }

                              temp2.add(temp1);

                    }

                    return temp2;
          }

          public ArrayList<Double> computeActualOutputs(ArrayList<Double> pattern, ArrayList<ArrayList<Double>> _Weights) {

                    ArrayList<Double> newActualOutputs = new ArrayList();

                    for (int j = 0; j < _Weights.size(); j++) {

                              newActualOutputs.add(sigmoid(dotProduct(pattern, _Weights.get(j))));
                    }

                    return newActualOutputs;
          }

          public ArrayList<ArrayList<Double>> normalizePatterns(ArrayList<ArrayList<Double>> _Patterns) {

                    ArrayList<Double> normalizedPattern;
                    ArrayList<ArrayList<Double>> temp2 = new ArrayList(); // the array made up of all the normalized patterns , that will be returned at the end .

                    for (int i = 0; i < patterns.size(); i++) {

                              normalizedPattern = new ArrayList();
                              double temp1 = computeMagnitude(_Patterns.get(i));

                              for (int j = 0; j < _Patterns.get(i).size(); j++) {

                                        normalizedPattern.add(_Patterns.get(i).get(j) / temp1);
                              }

                              temp2.add(normalizedPattern);
                    }

                    return temp2;
          }

          public ArrayList<ArrayList<Double>> initWeights(int _PatternSize) {

                    Random rdg = new Random();

                    ArrayList<Double> temp1;
                    ArrayList<ArrayList<Double>> temp2 = new ArrayList();

                    for (int i = 0; i < Nr_Of_ArtificialNeurons; i++) {

                              temp1 = new ArrayList();

                              for (int j = 0; j < _PatternSize; j++) {

                                        temp1.add(MIN_RANGE + (MAX_RANGE - MIN_RANGE) * rdg.nextDouble());
                              }

                              temp2.add(temp1);
                    }

                    return temp2;

          }

          void readPatterns(String filePath) {

                    String buffer;

                    File content = new File(filePath);
                    FileReader contentRead;

                    try {

                              contentRead = new FileReader(content);
                              BufferedReader reader = new BufferedReader(contentRead);

                              while ((buffer = reader.readLine()) != null) {

                                        loadPattern(buffer, patternSize);
                              }

                    } catch (FileNotFoundException e) {

                              System.out.println("Could not find .txt file !! ");

                    } catch (IOException e) {

                              System.out.println(" I/O Exception ");
                    }
          }

          void loadPattern(String rowElements, int _PaternSize) {

                    String[] pattern = rowElements.split(",");

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < _PaternSize; i++) {

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

          public double sigmoid(double NET) {

                    return ((2 / (1 + Math.pow(Math.E, (-1 * NET)))) - 1);
          }

          public double dotProduct(ArrayList<Double> pattern, ArrayList<Double> weight) {

                    double temp = 0D;

                    for (int i = 0; i < weight.size(); i++) {

                              temp += weight.get(i) * pattern.get(i);
                    }

                    return temp;

          }

          public double computeMagnitude(ArrayList<Double> pattern) {

                    double temp = 0;

                    for (int i = 0; i < pattern.size(); i++) {

                              temp += Math.pow(pattern.get(i), 2);
                    }

                    return Math.sqrt(temp);
          }

          void printField(ArrayList<?> field) {

                    for (int i = 0; i < field.size(); i++) {

                              System.out.println((i + 1) + ". " + field.get(i));
                    }
          }

          public static void main(String[] args) {
                    
                    /* fisierul .txt arata in felul urmator : 
                    
                              ** pe fiecare rand , ai atat elementele vectorului pattern cat si elementele vectorului iesirilor asteptate , despartite de virgula
                              ** programul stie sa citeasca pattern ul si iesirile dorite respective , trebuie doar mentionat in constructor cate elemente are pattern ul
                              
                                        !! setul de ponderi va fi generat de program in functie de cati neuroni pe layer ul ascuns are reteaua ( primul parametru din constructor ) 
                    
                    */
                    
                    NeuralNet n1 = new NeuralNet(1, 0.5 , 9, 0.00001 , "C:\\Users\\Catalin H\\Desktop\\TXT files\\Letters.txt");
                    n1.trainNeuralNet("Default");
//                    
//                    NeuralNet n2 = new NeuralNet(3 /* neurons*/, 11.4 /* learning rate */ , 2 /* pattern size */ , 0.001 /* error margin */, "C:\\Users\\Catalin H\\Desktop\\TXT files\\Furniture.txt");
//                    n2.trainNeuralNet("Normalize");
          }

}
