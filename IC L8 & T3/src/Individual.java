/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Catalin H
 */
public class Individual {

          ArrayList<Integer> chromosom;
          double fitness;
          double probability;
          double cumulativeProbability; // used for when you randomly pick the individuals for the next generation , based on their probabilities
          
          public Individual(ArrayList<Integer> chromosom, double fitness, double probability, double cumulativeProbability) {

                    this.chromosom = new ArrayList(chromosom);
                    this.fitness = fitness;
                    this.probability = probability;
                    this.cumulativeProbability = cumulativeProbability;

          }

          public Individual(int chromozomSize) { // generating a random chromosom for THIS individual

                    chromosom = new ArrayList();

                    Random rand = new Random();

                    for (int i = 0; i < chromozomSize; i++) {

                              chromosom.add(rand.nextInt(2)); // generates 1 or 0
                    }

                    probability = 0D;
                    fitness = 0D;
                    cumulativeProbability = 0D;
          }

}
