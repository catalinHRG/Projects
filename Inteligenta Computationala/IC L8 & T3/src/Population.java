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
import java.util.List;
import java.util.Random;

/**
 *
 * @author Catalin H
 */

// SE DAU 10 CARTI -- NUMEROTATE DE LA 1 LA 10
// SE SE ARANJEZE CELE 10 CARTI IN DOUA PACHETE ASTFEL INCAT SUMA CARTILOR DIN PRIMUL PACHET SA FIE CAT MAI APROAPE DE 36 IAR PRODUSUL CARTILOR DIN
// AL DOILEA PACHET SA FIE CAT MAI APROAPE DE 360
// CODIFICARE -- POPULATIE FORMATA DE 50 DE INDIVIZI , FIECARE INDIVID AVAND UN CROMOZOM , FORMAT DIN 10 BITI , INDEXATI DE LA 0 LA 9
// C[I] = 1 -- > CARTEA NUMEROTATA CU I FACE PARTE DIN PACHETUL 2
// C[I] = 0 -- > CARTEA NUMEROTATA CU I FACE PARTE DIN PACHETUL 1
// triggerEvolution este functia responsabila pentru rularea algoritmului genetic pentru cerinta aceasta



// -------------------------------------------------------------------------------------------------------------
// INSTRUIREA UNUI NEURON ARTIFICIAL FOLOSIND UN ALGORITM GENETIC
// trainNeuronViaGA este functia responsabila pentru rularea algoritmului genetic pentru cerinta aceasta


public class Population {

          ArrayList<Individual> individuals; // each individual has a chromosom , fitness , likelyhood to be selected in the next generation I.E. probability
          ArtificialNeuron aN1;
          
          int CROSS_OVER_CONSTANT = 5; // used to decide at which bit the cut will be made in order to do the crossover
          
          int randomMaxRange;
          static int PARTITION_CONSTANT = 3 ; // used to devide the 12 bit chromozom into 3 4bit sublists , each sublists used to obtain one of the weight vector components
          static int NR_OF_INDIVIDUALS ;
          static double MAX_FITNESS ; // used as a stoping condition for the GA
          static int MUTATION_RATE ; // given a range of 0 - 1000 , you pick a number in that range to dictate what will the mutation rate be -- 
                                                          // EXAMPLE : for MUTATION_RATE = 500 , there is a 0.5 % chance per bit for a mutation to occur -- I.E. 500 / 1000 = 0.5 %
          
          int numberOfGenerations = 1; 
          boolean foundTheOne = false; // flag to stop the loop when atleast one individual has the desired fitness
          

          public Population(int numberOfIndividuals , int mutationRate, int chromozomSize , double maxFitness , int randomRange) {
                    
                    NR_OF_INDIVIDUALS = numberOfIndividuals;
                    MUTATION_RATE = mutationRate;
                    MAX_FITNESS  = maxFitness ;
                    randomMaxRange = randomRange;
                    
                    individuals = new ArrayList();

                    for (int i = 0; i < NR_OF_INDIVIDUALS; i++) {

                              individuals.add(new Individual(chromozomSize));

                    }
          }

          void readPatterns(String filePath, ArtificialNeuron aN) { // the file should look like this : per row you have a pattern like 3,4,2,3,4,5 ; 
                    //NOTE : the last number is the desired output ; in our case the desired output is 1 or -1 , due to the nature of the treshold function

                    String buffer;

                    File content = new File(filePath);
                    FileReader contentRead;

                    try {

                              contentRead = new FileReader(content);
                              BufferedReader reader = new BufferedReader(contentRead);

                              while ((buffer = reader.readLine()) != null) {

                                        aN1.loadPattern(buffer);
                              }

                    } catch (FileNotFoundException e) {

                              System.out.println("Could not find .txt file !! ");

                    } catch (IOException e) {

                              System.out.println(" I/O Exception ");
                    }
          }

          ArrayList<Double> computeWeights(Individual indiv , int partitionConst) {

                    ArrayList<Double> temp = new ArrayList();
                    
                    for (int i = 0 ; i < PARTITION_CONSTANT ; i ++) {
                              
                              temp.add( computeWeight ( convertBinaryToDecimal (indiv.chromosom.subList( ( PARTITION_CONSTANT + 1 ) * i , ( PARTITION_CONSTANT + 1 ) * (i+1) ) ) ) ); // creates a partionConst bit sublist from the initial array
                    }
                    
                    return temp ;
          }
          
          double convertBinaryToDecimal (List<Integer> subList) {
                    
                    int decimal = 0;
                    int power = 1 ;
                    
                    for (int i = subList.size()-1  ; i >=0 ; i--) {
                              
                              decimal += subList.get(i) * power;
                              power = power * 2 ;
                    }
                    
                    return decimal;
          }
          
          double computeWeight (double param) {
                    
                    return (param - 8 ) / 2;
          }

          void trainNeuronViaGA() {

                    aN1 = new ArtificialNeuron(); // the AN contains all the patterns , desired and actual outputs , as well as the weight that are constantly updated wirh each iterration
                    double temp;
                    Individual tempIndividual;
                    readPatterns("D:/pattern.txt", aN1); // loading both the patterns and the desired outputs for them

                    while (true) {
                              
                              System.out.println("---------------------------------------------------------");
                              System.out.println("GNERATIA NR " + numberOfGenerations);
                              System.out.println("---------------------------------------------------------");
                              
                              for (int i = 0; i < individuals.size(); i++) {
                                        
                                        temp = 0D;
                                        aN1.updateWeights(computeWeights(individuals.get(i) , PARTITION_CONSTANT)); // based on the chromozom of this individual , splited at the 'partitionConstant' marker ,  we compute the weight vector and update it in the artificial neuron
                                        aN1.triggerPerceptronLearning(); // with the current weight vector , we compute the acual output for each pattern

                                        for (int j = 0; j < aN1.patterns.size(); j++) {
                                                  
                                                  temp += Math.abs(aN1.patterns.get(j).actualOutput - aN1.patterns.get(j).desiredOutput); // summing the differences between the actual and desired outputs , taken in absolute value
                                                  
                                        }

                                        tempIndividual = individuals.get(i);
                                        tempIndividual.fitness = 1000D / (temp + 1); // based on the actual outputs of the perceptron , we update the FITNESS of the current individual
                                        individuals.set(i, tempIndividual);
                              }

                              Individual theOne = checkForMaxFitness(individuals); // checking if the individual fits the description -- Changes the foundTheOne flag implicitly to TRUE

                              if (foundTheOne) {

                                        System.out.println("Am gasit individul perfect dupa " + numberOfGenerations + " generatii ; el are cromozomul : " + theOne.chromosom);
                                        System.out.println("Fitness ul este " + theOne.fitness);
                                        System.out.println("Ponderile generate de cromozomul individului acesta sunt : " + computeWeights(theOne , PARTITION_CONSTANT));
                                        
                                        aN1.updateWeights(computeWeights(theOne, PARTITION_CONSTANT));
                                        aN1.triggerPerceptronLearning();
                                        
                                        break;
                              }
                              
                              // if we did not find the individual with the desired fitness , the GA continues
                              individuals = mutation(crossOver(randomSelection(computeProbabilities(individuals))));
                              numberOfGenerations++;
                    }

          }

          void triggerEvolution() {

                    while (true) {

                              System.out.println("-------------------------------------------------------------------------------------------------");
                              System.out.println("GENERATIA NR " + numberOfGenerations + "-----------------------------------");
                              System.out.println("-------------------------------------------------------------------------------------------------");

                              individuals = computeFitness(individuals);

                              Individual theOne = checkForMaxFitness(individuals);

                              if (foundTheOne) {

                                        int sumBucket = 0;
                                        int productBucket = 1;
                                        ArrayList<Integer> firstDeck = new ArrayList();
                                        ArrayList<Integer> secondDeck = new ArrayList();

                                        for (int i = 0; i < theOne.chromosom.size(); i++) {

                                                  if (theOne.chromosom.get(i) == 0) {
                                                            sumBucket += (i + 1);
                                                            firstDeck.add(i + 1);
                                                  } else {
                                                            productBucket *= (i + 1);
                                                            secondDeck.add(i + 1);
                                                  }
                                        }

                                        System.out.println("-------------------------------------------------------------------------------------------------");
                                        System.out.println("Am gasit individul corespuzator dupa " + numberOfGenerations + " generatii. ");
                                        System.out.println("Cartile din primul packet sunt : " + firstDeck);
                                        System.out.println("Cartile din al doilea packet sunt : " + secondDeck);
                                        System.out.println("Suma cartilor din primul packet este " + sumBucket + " iar produsul cartilor din al doilea pachet este " + productBucket);
                                        System.out.println("-------------------------------------------------------------------------------------------------");
                                        break;
                              }

                              numberOfGenerations++;

                              individuals = computeProbabilities(individuals);
                              individuals = randomSelection(individuals);
                              individuals = crossOver(individuals);
                              individuals = mutation(individuals);

                    }

          }

          Individual checkForMaxFitness(ArrayList<Individual> currentGeneration) {

                    int temp = 0;

                    for (int i = 0; i < currentGeneration.size(); i++) {

                              if (currentGeneration.get(i).fitness >= MAX_FITNESS) {

                                        temp = i;
                                        foundTheOne = true;
                                        break;
                              }
                    }

                    return currentGeneration.get(temp);
          }

          ArrayList<Individual> mutation(ArrayList<Individual> currentGeneration) {

                    Random rand = new Random();
                    int mutate;
                    Individual temp;

                    for (int i = 0; i < currentGeneration.size(); i++) {

                              temp = currentGeneration.get(i);

                              for (int j = 0; j < temp.chromosom.size(); j++) {

                                        mutate = rand.nextInt(1001); // what are the chances of getting MUTATION_RATE amount of numbers on a random roll !? ANSWER : MUTATION_RATE / 1000

                                        if (mutate < MUTATION_RATE && temp.chromosom.get(j) == 0) {
                                                  System.out.println("Mutatia a avut loc la individul " + (i + 1) + " " + temp.chromosom + " la bitul numarul " + (j + 1));
                                                  temp.chromosom.set(j, 1);
                                                  System.out.println("Individul dupa mutatie este =      " + temp.chromosom);
                                        } else if (mutate < MUTATION_RATE && temp.chromosom.get(j) == 1) {
                                                  System.out.println("Mutatia a avut loc la individul " + (i + 1) + " " + temp.chromosom + " la bitul numarul " + (j + 1));
                                                  temp.chromosom.set(j, 0);
                                                  System.out.println("Individul dupa mutatie este =      " + temp.chromosom);
                                        }

                              }

                              currentGeneration.set(i, temp);
                    }

                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println("Populatia dupa ce a avut loc mutatia este : ");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    for (int i = 0; i < individuals.size(); i++) {

                              System.out.println("Individul nr " + (i + 1) + " =  " + currentGeneration.get(i).chromosom);
                    }
                    System.out.println("-----------------------------------------------");
                    return currentGeneration;
          }

          ArrayList<Individual> crossOver(ArrayList<Individual> currentGeneration) {

                    //System.out.println("Se realizeaza incrucisarile !!");
                    Individual firstParent;
                    Individual secondParent;
                    Individual child;

                    for (int i = 0; i < currentGeneration.size(); i += 2) {

                              firstParent = currentGeneration.get(i);
                              secondParent = currentGeneration.get(i + 1);

                              //System.out.println("Are loc prima incrucisare intre Individul " + (i + 1) + " = " + firstParent.chromosom + " si Individul " + (i + 2) + " = " + secondParent.chromosom);
                              child = new Individual(firstCrossOver(firstParent.chromosom, secondParent.chromosom), 0D, 0D, 0D);
                              currentGeneration.set(i, child);
                              //System.out.println("Individul resultat este " + currentGeneration.get(i).chromosom);
                              //System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

                              //System.out.println("Are loc a doua incrucisare intre Individul " + (i + 1) + " = " + firstParent.chromosom + " si individul " + (i + 2) + " = " + secondParent.chromosom);
                              child = new Individual(secondCrossOver(firstParent.chromosom, secondParent.chromosom), 0D, 0D, 0D);
                              currentGeneration.set((i + 1), child);
                              //System.out.println("Individul resultat este " + currentGeneration.get(i + 1).chromosom);
                              //System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

                    }

                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println("Populatia dupa incrucisari este : ");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    printPopulation(currentGeneration);
                    return currentGeneration;
          }

          ArrayList<Integer> firstCrossOver(ArrayList<Integer> c1, ArrayList<Integer> c2) { // cel mai probabil va trebui instantiat un arraylist cu lista obtinuta ca parametru
                    
                    Random rand = new Random();
                    CROSS_OVER_CONSTANT =  rand.nextInt(randomMaxRange);
                    
                    ArrayList<Integer> firstHalf = new ArrayList(c1.subList(0, CROSS_OVER_CONSTANT));
                    ArrayList<Integer> secondHalf = new ArrayList(c2.subList(CROSS_OVER_CONSTANT, c2.size()));
                    firstHalf.addAll(secondHalf);

                    return firstHalf;
          }

          ArrayList<Integer> secondCrossOver(ArrayList<Integer> c1, ArrayList<Integer> c2) {
                    
                    Random rand = new Random();
                    CROSS_OVER_CONSTANT =  rand.nextInt(randomMaxRange);
                    
                    ArrayList<Integer> firstHalf = new ArrayList(c2.subList(0, CROSS_OVER_CONSTANT));
                    ArrayList<Integer> secondHalf = new ArrayList(c1.subList(CROSS_OVER_CONSTANT, c2.size()));
                    firstHalf.addAll(secondHalf);

                    return firstHalf;
          }

          ArrayList<Individual> randomSelection(ArrayList<Individual> currentGeneration) { // suprascriu campul cumulativeProbability din fiecare obiect si passez lista mai departe

//                    System.out.println("-------------------------------------------------------------------------------------------------");
//                    System.out.println("Are loc selectia pentru urmatoare generatie !!!!!!!!!!!!!!!!!!!!!!");
//                    System.out.println("-------------------------------------------------------------------------------------------------");
                    ArrayList<Individual> sortedGeneration = new ArrayList(bubbleSort(currentGeneration));
                    ArrayList<Individual> temp = new ArrayList(computeCumulativeProbability(sortedGeneration));

                    ArrayList<Individual> newGeneration = new ArrayList();

                    Random randomDoubleGenerator = new Random();

                    for (int i = 0; i < temp.size(); i++) {

                              double rand = randomDoubleGenerator.nextDouble();

                              for (int j = 0; j < temp.size(); j++) {

                                        if (temp.get(j).cumulativeProbability > rand) {

                                                  newGeneration.add(temp.get(j));
                                                  break;

                                        }
                              }
                    }

                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println("Populatia dupa ce a avut loc procesul de selectie aleatoare este : ");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    printPopulationFitness(newGeneration);
                    return newGeneration;

          }

          ArrayList<Individual> computeCumulativeProbability(ArrayList<Individual> sortedGeneration) {

                    for (int i = 0; i < sortedGeneration.size(); i++) {

                              Individual temp = sortedGeneration.get(i);

                              for (int j = 0; j <= i; j++) {

                                        temp.cumulativeProbability += sortedGeneration.get(j).probability;
                              }

                              sortedGeneration.set(i, temp);
                    }

                    return sortedGeneration;
          }

          ArrayList<Individual> bubbleSort(ArrayList<Individual> currentGeneration) {

                    boolean keepGoing = true;
                    Individual temp;

                    while (keepGoing) {

                              keepGoing = false;

                              for (int i = 0; i < currentGeneration.size() - 1; i++) {

                                        if (currentGeneration.get(i).probability > currentGeneration.get(i + 1).probability) {

                                                  temp = currentGeneration.get(i);
                                                  currentGeneration.set(i, currentGeneration.get(i + 1));
                                                  currentGeneration.set(i + 1, temp);
                                                  keepGoing = true;
                                        }
                              }
                    }

                    //System.out.println("Generatia curenta sortata este : ");
                    //printPopulation(currentGeneration);
                    return currentGeneration;

          }

          ArrayList<Individual> computeProbabilities(ArrayList<Individual> currentGeneration) {
//
//                    System.out.println("-------------------------------------------------------------------------------------------------");
//                    System.out.println("Calculez probabilitatile indivizilor !!!!!!!!!!!!!!!!! ");
//                    System.out.println("-------------------------------------------------------------------------------------------------");

                    int sum = 0;
                    Individual temp;

                    for (int i = 0; i < currentGeneration.size(); i++) {

                              sum += currentGeneration.get(i).fitness;
                    }

                    for (int i = 0; i < currentGeneration.size(); i++) {

                              temp = currentGeneration.get(i);
                              temp.probability = (double) temp.fitness / sum;
                              currentGeneration.set(i, temp);

                    }

                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println("Probabilitatile indivizilor pentru a fi selectatati in urmatoarea generatie sunt : ");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    printPopulation(currentGeneration);

                    return currentGeneration;
          }

          ArrayList<Individual> computeFitness(ArrayList<Individual> currentGeneration) {
                    
                    Individual temp;

                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println("Fitness ul fiecarui individ este ");
                    System.out.println("-------------------------------------------------------------------------------------------------");

                    for (int i = 0; i < currentGeneration.size(); i++) {

                              int sumBucket = 0;
                              int productBucket = 1;

                              for (int j = 0; j < currentGeneration.get(i).chromosom.size(); j++) {

                                        if (currentGeneration.get(i).chromosom.get(j) == 0) {

                                                  sumBucket += (j + 1);

                                        } else {

                                                  productBucket *= (j + 1);
                                        }
                              }

                              temp = currentGeneration.get(i);
                              temp.fitness = (double) 1000 / (Math.abs(36 - sumBucket) + Math.abs(360 - productBucket) + 1); // compute fitness of individual
                              currentGeneration.set(i, temp);
                              System.out.print("Individ " + (i + 1) + " este " + currentGeneration.get(i).chromosom + " -------------------------- F = " + currentGeneration.get(i).fitness);
                              System.out.println("  ---------->  Suma = " + sumBucket + "     Produs = " + productBucket);
                    }

                    //printPopulationFitness(currentGeneration);
                    return currentGeneration;
          }

          void printPopulationFitness(ArrayList<Individual> indiv) {

                    for (int i = 0; i < indiv.size(); i++) {

                              System.out.println("Individul nr " + (i + 1) + " =  " + indiv.get(i).chromosom + " F =  " + indiv.get(i).fitness);
                    }

                    System.out.println("------------------------------------------------");
          }

          void printPopulation(ArrayList<Individual> indiv) {

                    for (int i = 0; i < indiv.size(); i++) {

                              System.out.println("Individul nr " + (i + 1) + " =  " + indiv.get(i).chromosom + " F =  " + indiv.get(i).fitness + " ------ P = " + indiv.get(i).probability);
                    }

                    System.out.println("------------------------------------------------");
          }

          public static void main(String[] args) {

                    Population population = new Population(50 , 500 , 10, 1000 , 8);
                    population.triggerEvolution(); // LABORATOR 8
                    
                    //Population p2 = new Population(10 , 5, 12 , 980 , 12); // param #1 - number of individuals , param #2 mutation rate , param #3 chromozom size , param #4 max fitness I.E. stopping condition
                    //p2.trainNeuronViaGA(); // TEMA 3
                    
          }

}
