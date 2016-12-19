/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static java.lang.Math.sqrt;

/**
 *
 * @author Catalin H
 */
public class Classifier {

          void runApp() {

                    System.out.println("Magnitudinea vectorului " + printVector3D(p0) + " este : " + vectorMagnitude(p0));
                    System.out.println("Marimea unghiului dintre vectorul " + printVector3D(p0) + " respectiv " + printVector3D(p1) + " este : " + cos(p0, p1));
                    System.out.println("Produsul scalar dintre vectorul " + printVector3D(p0) + " respectiv " + printVector3D(p1) + " este : " + dotProduct(p0, p1));
                    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    
                    triggerClassifier(p0);
                    triggerClassifier(p1);
                    triggerClassifier(p2);
                    triggerClassifier(p3);
                    triggerClassifier(p4);
                    triggerClassifier(p5);
                    triggerClassifier(p6);
                    triggerClassifier(p7);

                    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    System.out.println("Iesirea stabila P6 : " + printVector3D(p6));

                    System.out.print("\nValoare distorsionata a lui P6 , anume P2 : " + printVector3D(p2));
                    triggerMemoryNetwork(p2);

                    System.out.print("\nValoare distorsionata a lui P6 , anume P4 : " + printVector3D(p4));
                    triggerMemoryNetwork(p4);

                    System.out.print("\nValoare distorsionata a lui P6 , anume P7 : " + printVector3D(p7));
                    triggerMemoryNetwork(p7);

                    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    System.out.println("Iesirea stabila P1 : " + printVector3D(p1));

                    System.out.print("\nValoare distorsionata a lui P1 , anume P0 : " + printVector3D(p0));
                    triggerMemoryNetwork(p0);

                    System.out.print("\nValoare distorsionata a lui P1 , anume P3 : " + printVector3D(p3));
                    triggerMemoryNetwork(p3);

                    System.out.print("\nValoare distorsionata a lui P1 , anume P5 : " + printVector3D(p5));
                    triggerMemoryNetwork(p5);

                    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    System.out.println("P6 respectiv P1 sunt iesiri stabile. ");

          }

          class Vector2D {

                    int x1, x2;

                    public Vector2D(int a1, int a2) {

                              this.x1 = a1;
                              this.x2 = a2;
                    }
          }

          class Vector3D {

                    int x1, x2, x3;

                    public Vector3D(int a1, int a2, int a3) {

                              this.x1 = a1;
                              this.x2 = a2;
                              this.x3 = a3;
                    }
          }

          Vector3D p0 = new Vector3D(-1, -1, -1);
          Vector3D p1 = new Vector3D(-1, -1, 1);
          Vector3D p2 = new Vector3D(-1, 1, -1);
          Vector3D p3 = new Vector3D(-1, 1, 1);
          Vector3D p4 = new Vector3D(1, -1, -1);
          Vector3D p5 = new Vector3D(1, -1, 1);
          Vector3D p6 = new Vector3D(1, 1, -1);
          Vector3D p7 = new Vector3D(1, 1, 1);

          Vector2D i1 = new Vector2D(-1, 1);
          Vector2D i2 = new Vector2D(1, -1);
          Vector2D i3 = new Vector2D(-1, -1);

          int s1, s2, s3;

          void triggerMemoryNetwork(Vector3D distortedValues) {

                    s1 = i1.x1 * distortedValues.x3 + i1.x2 * distortedValues.x2;
                    s2 = i2.x1 * distortedValues.x1 + i2.x2 * distortedValues.x3;
                    s3 = i3.x1 * distortedValues.x1 + i3.x2 * distortedValues.x2;

                    Vector3D restoredValues = new Vector3D(0, 0, 0);

                    restoredValues.x1 = signum(s1, "Unit 1", distortedValues);
                    restoredValues.x2 = signum(s2, "Unit 2", distortedValues);
                    restoredValues.x3 = signum(s3, "Unit 3", distortedValues);

                    System.out.println(" , in urma restaurarii de catre reteaua de memorie , rezulta iesirea stabila : " + printVector3D(restoredValues) + " , pe care o va memora.");

          }

          int signum(int summationResult, String unit, Vector3D v) {

                    if (summationResult < 0) {
                              return -1;
                    } else if (summationResult > 0) {
                              return 1;
                    } else {

                              switch (unit) {

                                        case "Unit 1":
                                                  return v.x1;
                                        case "Unit 2":
                                                  return v.x2;
                                        case "Unit 3":
                                                  return v.x3;
                                        default: {
                                                  System.out.println("Huston we have a problem !! -- ORICUM NU VA INTRA NICIODATA PE CAZUL ASTA @@ ");
                                                  return 0;
                                        }
                              }
                    }

          }

          String printVector3D(Vector3D v) {

                    return ("[ " + v.x1 + " , " + v.x2 + " , " + v.x3 + " ]");

          }

          void triggerClassifier(Vector3D v) {

                    if ((v.x1 + v.x2 + v.x3) > 0) {
                              System.out.println("Punctul de coordonate : " + printVector3D(v) + " face parte din clasa 1");
                    } else {
                              System.out.println("Punctul de coordonate : " + printVector3D(v) + " face parte din clasa 2");
                    }

          }

          double cos(Vector3D v1, Vector3D v2) {

                    return (dotProduct(v1, v2) / (vectorMagnitude(v1) * vectorMagnitude(v2)));
          }

          double vectorMagnitude(Vector3D v) {

                    return (sqrt(v.x1 * v.x1 + v.x2 * v.x2 + v.x3 * v.x3));
          }

          int dotProduct(Vector3D v1, Vector3D v2) {

                    return (v1.x1 * v2.x1 + v1.x2 * v2.x2 + v1.x3 * v2.x3);

          }

          public static void main(String[] args) {

                    Classifier c1 = new Classifier();
                    c1.runApp();
          }

}
