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
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author Catalin H
 */
public class Clusterer {

          ArrayList<ArrayList<Double>> centroids = new ArrayList();
          ArrayList<Pattern> patterns = new ArrayList();

          int indexPointer; // used to refer to the closest centroid 
          int epochs;
          double learningConstant; // used for WTA Clustering only

          class Pattern { // Addaptation for the patterns in order to keep track of what cluster they fall into 

                    ArrayList<Double> coordinates;
                    int clusterID;

                    public Pattern(String... numericValues) {

                              this.coordinates = new ArrayList();

                              for (int i = 0; i < numericValues.length; i++) {

                                        this.coordinates.add(Double.parseDouble(numericValues[i]));
                              }
                    }

          }

          public Clusterer(int epochs) { // used for KM Clustering Object

                    this.epochs = epochs;

          }

          public Clusterer(double learningConstant, int epochs) { // used for WTA Clustering Object

                    this.epochs = epochs;
                    this.learningConstant = learningConstant;
          }

          void triggerKM_Clustering() {

                    int clusterSize;
                    ArrayList<Double> temp;
                    ArrayList<Double> distances;

                    for (int e = 0; e < epochs; e++) {

                              System.out.println("------------ KM Clustering ------------ Epoca nr " + (e + 1) + " ------------ ");

                              System.out.println("Prima parte -- Afilierea pattern urilor cu un anume cluster ! ");
                              for (int i = 0; i < patterns.size(); i++) {

                                        distances = new ArrayList(); // for each pattern , we need a fresh vector

                                        for (int j = 0; j < centroids.size(); j++) {

                                                  distances.add(computeDistance(patterns.get(i).coordinates, centroids.get(j)));
                                        }

                                        indexPointer = getMinimumDistanceIndex(distances);
                                        patterns.get(i).clusterID = indexPointer; // found at which index the closest centroid is and we assign the pattern with the respectiv cluster
                                        System.out.println("Pattern : " + patterns.get(i).coordinates);
                                        System.out.println("Centroizi sunt : " + centroids);
                                        System.out.println("Distantele sunt : " + distances);
                                        System.out.println("Pattern ul " + patterns.get(i).coordinates + " face parte din cluster ul " + (patterns.get(i).clusterID + 1));
                                        System.out.println(" -------------------------------------------------- ");

                              }

                              System.out.println("Partea a doua -- Calcularea noilor centroizi , pe baza pattern urile aflate in clusterele aferente .");
                              for (int i = 0; i < centroids.size(); i++) {

                                        clusterSize = 0;
                                        temp = new ArrayList();
                                        for (int s = 0; s < centroids.get(i).size(); s++) {

                                                  temp.add(0D); // needed when doing the summation of all patterns found in current centroid
                                        }

                                        System.out.println("Centroid ul nr" + (i + 1) + " este " + centroids.get(i));
                                        for (int j = 0; j < patterns.size(); j++) {

                                                  if (patterns.get(j).clusterID == i) {

                                                            System.out.println("                   Pattern ul " + patterns.get(j).coordinates + " este in clusterul " + (i + 1));
                                                            temp = vectorAddition(patterns.get(j).coordinates, temp);
                                                            clusterSize++;
                                                            System.out.println("                   Suma pattern urilor pana acum este  " + temp + " , in total avem " + clusterSize + " pattern uri .");
                                                            System.out.println("                   -------------------------------------------------- ");
                                                  }

                                        }

                                        if (clusterSize > 0) {
                                                  System.out.println("                            Cluster ul dat de centroidul " + centroids.get(i) + " este format din " + clusterSize + " pattern uri");
                                                  centroids.set(i, scalarDivision(temp, clusterSize));
                                                  System.out.println("                            Centroid ul actualizat este " + centroids.get(i));
                                                  System.out.println("                            -------------------------------------------------- ");
                                        }
                              }
                    }

                    for (int i = 0; i < centroids.size(); i++) {

                              System.out.println("Clusterul nr" + (i + 1) + " dat de centroid ul " + centroids.get(i) + " este forma din urmatoarele pattern uri :");
                              for (int j = 0; j < patterns.size(); j++) {

                                        if (patterns.get(j).clusterID == i) {

                                                  System.out.println(patterns.get(j).coordinates);
                                        }

                              }

                    }
          }

          void triggerWTA_Clustering() {

                    ArrayList<Double> distances;

                    for (int e = 0; e < epochs; e++) {

                              System.out.println("----------------------- Regula de invatare WTA Epoca nr : " + (e + 1) + " ----------------------------------");

                              for (int i = 0; i < patterns.size(); i++) {

                                        distances = new ArrayList();

                                        for (int j = 0; j < centroids.size(); j++) {

                                                  System.out.println("Pattern - ul X" + (i + 1) + " anume " + patterns.get(i).coordinates + " , Centroidul C" + (j + 1) + " anume " + centroids.get(j));
                                                  distances.add(computeDistance(patterns.get(i).coordinates, centroids.get(j)));
                                        }

                                        System.out.println("----------------");
                                        indexPointer = getMinimumDistanceIndex(distances); // returns the index of the minimum distance

                                        System.out.println("Distantele sunt : " + distances);
                                        System.out.println("Centroidul castigator este : " + centroids.get(indexPointer));

                                        centroids.set(indexPointer, vectorAddition(centroids.get(indexPointer), scalarProduct(1,
                                                vectorSubtraction(patterns.get(i).coordinates, centroids.get(indexPointer)))));

                                        patterns.get(i).clusterID = indexPointer; // assigning the pattern to the closest cluster

                                        System.out.println("Pattern ul " + patterns.get(i).coordinates + " se afla in clusterul " + (patterns.get(i).clusterID + 1));
                                        System.out.println("Centroidul castigator actualizat este : " + centroids.get(indexPointer));
                                        System.out.println("----------------");

                              }

                    }

                    for (int i = 0; i < centroids.size(); i++) {

                              System.out.println("Clusterul nr" + (i + 1) + " dat de centroid ul " + centroids.get(i) + " este forma din urmatoarele pattern uri :");
                              for (int j = 0; j < patterns.size(); j++) {

                                        if (patterns.get(j).clusterID == i) {

                                                  System.out.println(patterns.get(j).coordinates);
                                        }

                              }

                    }

          }

          void initCentroid(double... centroid) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < centroid.length; i++) {

                              temp.add(centroid[i]);
                    }

                    centroids.add(temp);

          }

          void readPatterns(String filePath) {
                    String buffer;

                    File content = new File(filePath);
                    FileReader contentRead;

                    try {

                              contentRead = new FileReader(content);
                              BufferedReader reader = new BufferedReader(contentRead);

                              while ((buffer = reader.readLine()) != null) {

                                        String[] rows = buffer.split(",");
                                        Pattern temp = new Pattern(rows);

                                        patterns.add(temp);
                              }

                    } catch (FileNotFoundException e) {

                              System.out.println("Could not find .csv file !! ");

                    } catch (IOException e) {

                              System.out.println(" I/O Exception ");
                    }
          }

          int getMinimumDistanceIndex(ArrayList<Double> distances) {

                    double bucket = distances.get(0);
                    int index = 0;

                    for (int i = 0; i < distances.size(); i++) {

                              if (distances.get(i) < bucket) {

                                        bucket = distances.get(i);
                                        index = i;
                              }
                    }

                    return index;
          }

          double computeDistance(ArrayList<Double> pattern, ArrayList<Double> centroid) {

                    double temp = 0;

                    for (int i = 0; i < pattern.size(); i++) {

                              temp += pow(centroid.get(i) - pattern.get(i), 2);
                    }

                    return sqrt(temp);
          }

          ArrayList<Double> scalarProduct(double scalar, ArrayList<Double> pattern) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < pattern.size(); i++) {

                              temp.add(learningConstant * scalar * pattern.get(i));
                    }

                    return temp;
          }

          ArrayList<Double> vectorAddition(ArrayList<Double> pattern, ArrayList<Double> centroid) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < pattern.size(); i++) {

                              temp.add(centroid.get(i) + pattern.get(i));
                    }

                    return temp;
          }

          ArrayList<Double> vectorSubtraction(ArrayList<Double> pattern, ArrayList<Double> centroid) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < pattern.size(); i++) {

                              temp.add(pattern.get(i) - centroid.get(i));
                    }

                    return temp;
          }

          ArrayList<Double> scalarDivision(ArrayList<Double> centroid, int clusterSize) {

                    ArrayList<Double> temp = new ArrayList();

                    for (int i = 0; i < centroid.size(); i++) {

                              temp.add(centroid.get(i) / clusterSize);
                    }

                    return temp;
          }

          public static void main(String[] args) {

                    Clusterer c1 = new Clusterer(3 /* <----- this is the number of epochs*/);
                    c1.initCentroid(50, 43);
                    c1.initCentroid(43, 87);
                    c1.initCentroid(195, 41);
                    c1.readPatterns("D:/pat.txt");
                    c1.triggerKM_Clustering();

                    Clusterer c2 = new Clusterer(0.5, 5);
                    c2.initCentroid(59, 55);
                    c2.initCentroid(75, 55);
                    c2.initCentroid(160, 75);
                    c2.readPatterns("D:/pat.txt");
                    c2.triggerWTA_Clustering();

          }

}
