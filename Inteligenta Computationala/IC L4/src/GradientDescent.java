
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


public class GradientDescent {

          static double current1DX = 0.0;

          static double current2DX = 0.0;
          static double current2DY = 0.0;

          double learningConstant1D = 0.04; // Given the [-5000 , 5000] range , the learning constant has to be in the range [ 0 , 0.08 ]
          double learningConstant2D = 0.04;
          double learningConstant2D_II = 0.00001;

          int counter1D = 1;
          int counter2D = 1;
          int counter2D_II = 1;

          // Two-variable polynomials
          
          void runGradient2D_I (double previous2DX, double previous2DY) {

                    current2DX = previous2DX - learningConstant2D * partialX_I(previous2DX);
                    current2DY = previous2DY - learningConstant2D * partialY_I(previous2DY);
                    System.out.println("Iteratia nr : " + counter2D + " X curent = " + current2DX + " Y curent = " + current2DY);
                    counter2D ++;
                    
                    if( ( previous2DX - current2DX > 0.0001 & previous2DY - current2DY > 0.0001 ) | ( previous2DX - current2DX < -(0.0001) & previous2DY - current2DY < -(0.0001) ) 
                            | ( previous2DX - current2DX > 0.0001 & previous2DY - current2DY < -(0.0001) ) | (previous2DX - current2DX < -(0.0001) & previous2DY - current2DY > 0.0001) ) {
                              
                              runGradient2D_I (current2DX , current2DY);
                    }

          }

          void runGradient2D_II (double previous2DX, double previous2DY) {

                    current2DX = previous2DX - learningConstant2D_II * partialX_II (previous2DX , previous2DY);
                    current2DY = previous2DY - learningConstant2D_II * partialY_II (previous2DY , previous2DY);
                    System.out.println("Iteratia nr : " + counter2D_II + " X curent = " + current2DX + " Y curent = " + current2DY);
                    counter2D_II ++;
                    
                    if( ( previous2DX - current2DX > 0.0001 & previous2DY - current2DY > 0.0001 ) | ( previous2DX - current2DX < -(0.0001) & previous2DY - current2DY < -(0.0001) ) 
                            | ( previous2DX - current2DX > 0.0001 & previous2DY - current2DY < -(0.0001) ) | (previous2DX - current2DX < -(0.0001) & previous2DY - current2DY > 0.0001) ) {
                              
                              runGradient2D_II(current2DX , current2DY);
                    }

          } // -400y * (x-y^2)          202x-200y^2-2

          double partialX_I (double currentX2D) {
                    
                    return (2 * currentX2D);
          }

          double partialY_I (double currentY2D) {
                    
                    return (4 * currentY2D);
          }
          
          double partialX_II (double currentX2D , double currentY2D) {
                    
                    return ( 202 * currentX2D - 200 * (currentY2D * currentY2D) - 2);
          }
          
          double partialY_II (double currentX2D , double currentY2D) {
                    
                    return (  -400 * currentY2D * ( currentX2D - (currentY2D * currentY2D)  ) );
          }

          // One-variable polynomials
          void runGradient1D (double currentX1D){
                    
                    current1DX = currentX1D - learningConstant1D * functionDerivative(currentX1D);
                    System.out.println("Iteratia nr : " + counter1D + " X curent = " + current1DX);
                    counter1D ++ ;
                    
                    if(currentX1D - current1DX > 0.0001 | currentX1D - current1DX < -(0.0001) ){
                              runGradient1D(current1DX);
                    }
          }
          
          double functionDerivative (double x) {

                    return (12 * x - 12);
          }

          public static void main (String[] args) {

                    GradientDescent g1 = new GradientDescent();
                    Random randomGenerator = new Random();

                    double startingValue1D = randomGenerator.nextDouble() * 10000 - 5000; // random starting floating point number between -5000 and 5000
                    System.out.println("Pornim de la valoarea X = " + startingValue1D + " pentru functia de o variabila.\n");

                    g1.runGradient1D(startingValue1D);

                    System.out.println("Valoarea minima a functiei 6 * X ^ 2 - 12 * X + 1 este in punctul : " + current1DX + " anume : " + (6 * (current1DX * current1DX) - 12 * current1DX + 1));
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    double startingValue2DX = randomGenerator.nextDouble() * 300 - 150;
                    double startingValue2DY = randomGenerator.nextDouble() * 300 - 150;
                    
                    
                    double sv1 = randomGenerator.nextDouble() * 50 - 25;
                    double sv2 = randomGenerator.nextDouble() * 50 - 25;

                    System.out.println("Pornim de la valorile X =  " + startingValue2DX + " respectiv Y = " + startingValue2DY + " pentru prima functia de doua variabile.\n");

                    g1.runGradient2D_I (startingValue2DX, startingValue2DY);
                    System.out.println("Valoarea minima a functiei X ^ 2 + 2 Y ^ 2 este in punctul de coordonate : " + "[ " + current2DX + " , " + current2DY + " ]" + " anume : "
                            + ((current2DX * current2DX) + 2 * (current2DY * current2DY)));
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    
                    
                    System.out.println("Pornim de la valorile X = " + sv1 + " respectiv Y = " + sv2 + " pentru cea dea doua functia de doua variabile.\n");
                    g1.runGradient2D_II (sv1 , sv2); 
                    System.out.println( "Valoarea minima a functiei (1 - X ) ^ 2 + 100 * ( X - Y ^ 2) ^ 2 este in punctul de coordonate : " + "[ " + current2DX + " , " + current2DY + " ]" 
                    + " anume : " +  ( ( (1 - current2DX ) * (1 - current2DX ) ) + 100 * ( ( current2DX - (current2DY * current2DY) ) * ( current2DX - (current2DY * current2DY) ) ) )  );
          }

}
