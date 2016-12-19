
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Iris {

          // A = [a, a1, a2, a3] ; B = [b, b1, b2, b3] 
          // Produs scalar: [a*b + a1*b1 + a2*b2 + a3*b3] --
          // Produs vectorial: [a*b, *b2,a1*b1, a2 a3*b3]
          // Normalizare: ( X - X(mediat) ) / sigma
          // X = a, a1, a2, a3
          // X(mediat) = M(X) = (a + a1 + a2 + a3)/4
          // sigma = M(X^2) - [M(X)]^2
          // M(X^2) = (a*a + a1*a1 + a2*a2 + a3*a3)/4
          // [M(X)]^2 = X(mediat)*X(mediat)
          // Scalare: (X-Xmin) / (Xmax-Xmin);
          // Xmin este valoarea minima dintre valorile dintr-o multime
          // Xmax este valoarea maxima dintre valorile dintr-o multime
          // De ex, avem A = [2.3, 0.4, 4.5, 1.9]; Xmin = 0.4; Xmax = 4.5;
          
          static Vector<Double> numeralValues = new Vector();
          static Vector<Double> firstRow = new Vector();
          static Vector<Double> secondRow = new Vector();

          

          public double mediaM(Vector<Double> v1) {

                    double result = 0.0;

                    for (int i = 0; i < v1.size(); i++) {

                              result += v1.get(i);
                    }

                    return (result / v1.size());
          }

          public double mediaMdeXlaPatrat(Vector<Double> v1) {

                    double result = 0.0;

                    for (int i = 0; i < v1.size(); i++) {

                              result += (v1.get(i) * v1.get(i));
                    }

                    return (result / v1.size());
          }

          public double mediaMdeXtotulPatrat(Vector<Double> v1) {

                    return (mediaM(v1) * mediaM(v1));
          }

          public Vector<Double> normalizare(Vector<Double> v1) {

                    Vector<Double> result = new Vector();
                    Vector<Double> temp = new Vector();
                    double rezultatMediat = mediaM(v1);
                    double rezultatSigma = sigma(v1);

                    for (int i = 0; i < v1.size(); i++) {

                              temp.add(v1.get(i) - rezultatMediat);
                    }

                    for (int i = 0; i < v1.size(); i++) {

                              result.add(temp.get(i) / 4);
                    }

                    return result;
          }

          public Vector<Double> scalare(Vector<Double> v1) {
                    
                    Vector<Double> result = new Vector();
                    Vector<Double> temp = new Vector();
                    
                    for (int i = 0 ; i < v1.size(); i++) {
                              
                              temp.add(v1.get(i) - min(v1));
                    }
                    
                    for(int i = 0 ; i < temp.size() ; i++) {
                              
                              result.add( temp.get(i) / ( max(v1) - min(v1) ) );
                    }
                    
                    return result;
          }

          public double min(Vector<Double> v1) {

                    double bucket = v1.firstElement();

                    for (int i = 0; i < v1.size(); i++) {

                              if (v1.get(i) < bucket) {

                                        bucket = v1.get(i);
                              }
                    }

                    return bucket;
          }

          public double max(Vector<Double> v1) {

                    double bucket = v1.firstElement();

                    for (int i = 0; i < v1.size(); i++) {

                              if (v1.get(i) > bucket) {

                                        bucket = v1.get(i);
                              }
                    }

                    return bucket;
          }
          

          public double sigma(Vector<Double> v1) {

                    return (mediaMdeXlaPatrat(v1) - mediaMdeXtotulPatrat(v1));
          }

          public double produsScalar(Vector<Double> v1, Vector<Double> v2) {

                    double result = 0.0;

                    for (int i = 0; i < v1.size(); i++) {
                              result += (v1.get(i)) * (v2.get(i));
                    }

                    return result;

          }

          public Vector<Double> produsVectorial(Vector<Double> v1, Vector<Double> v2) {
                    
                    System.out.println(v1);
                    Vector<Double> result = new Vector();

                    for (int i = 0; i < 4; i++) {

                              result.add(v1.get(i) * v2.get(i));
                    }

                    return result;

          }

          public void readFileContent() {

                    String buffer; String[] rows = null;

                    File content = new File("C:\\Users\\Catalin H\\Desktop\\TXT files\\iris.csv");
                    FileReader contentRead;

                    try {

                              contentRead = new FileReader(content);
                              BufferedReader reader = new BufferedReader(contentRead);

                              while ((buffer = reader.readLine()) != null) {

                                        rows = buffer.split(",");

                                        for (int j = 1; j < rows.length + 1; j++) {
                                                  if (j % 5 != 0) {
                                                            numeralValues.add( Double.parseDouble( rows[j-1] ) );
                                                  } else {
                                                            break;
                                                  }
                                        }
                              } 

                              for (int i = 0; i < 8; i++) {

                                        if (i < 4) {
                                                  firstRow.add(numeralValues.get(i));
                                        } else {
                                                  secondRow.add(numeralValues.get(i));
                                        }
                              }
                              
                              for (int i = 0 ; i < rows.length ; i ++) {
                                        System.out.println(rows[i]);
                              }

                    } catch (FileNotFoundException e) {

                              System.out.println("Could not find .csv file !! ");

                    } catch (IOException e) {

                              System.out.println(" I/O Exception ");
                    }

          }

          public static void main(String[] args) {

                    Iris app = new Iris();


                    app.readFileContent();

                    System.out.println("Rezultatul in urma produsului scalar este : " + app.produsScalar(firstRow, secondRow) + "\n");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------\n");

                    System.out.println("Vectorul rezultat in urma produsului vectorial este : " + app.produsVectorial(firstRow, secondRow));
                    System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------\n");

                    System.out.println("Vectorul rezultat din elementele de pe primul rand  , NORMALIZAT , este : " + app.normalizare(firstRow));
                    System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------\n");

                    System.out.println("Vectorul format din elementele de pe randul al doilea , NORMALIZAT , este : " + app.normalizare(secondRow));
                    System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    
                    System.out.println("Vectorul format din elementele de pe primul rand , dupa SCALARE , este : " + app.scalare(firstRow));
                    System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    
                    System.out.println("Vectorul format din elementele de pe randul al doilea, dupa SCALARE , este : " + app.scalare(secondRow));
                    
                    

          }

}
