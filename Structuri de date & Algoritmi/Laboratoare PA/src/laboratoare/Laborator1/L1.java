package laboratoare.Laborator1;

import java.util.ArrayList;

public class L1 {

	int[] selectionSort(int[] a) {

		for (int i = 0; i < (a.length - 1); i++) {

			int minj = i;
			int minx = a[i];

			for (int j = (i + 1); j < a.length; j++) {

				if (a[j] < minx) {

					minj = j;
					minx = a[j];

					a[minj] = a[i];
					a[i] = minx;
				}

			}
		}

		return a;
	}

	int[] insertionSort(int[] a) {

		for (int j = 1; j < a.length; j++) {

			int key = a[j];
			int i = j - 1;

			while (i >= 0 && a[i] > key) {

				a[i + 1] = a[i];
				i = i - 1;

			}

			a[i + 1] = key;
		}

		return a;
	}

	int fibonacci(int n) {

		if (n < 2) {

			return n;
		} else {

			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

	int[][] matrixMultiplication(int[][] m1, int[][] m2) {

		if (m1[0].length != m2.length) {

			System.out.println("n != p , nu se poate realiza inmultirea celor doua matrici");
			System.exit(0);

		} else if (iregularityCheck(m1) || iregularityCheck(m2)) {

			System.out.println(
					"Avem de a face cu o matrice neregulata , nu se poate realiza inmultirea celor doua matrici");
			System.exit(0);
		}

		int[][] result = new int[m1.length][m2[0].length];

		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m1[0].length; k++) {

					result[i][j] = result[i][j] + m1[i][k] * m2[k][j];
				}
			}
		}

		return result;
	}

	boolean iregularityCheck(int[][] m) {

		boolean iregularMatrixFlag = false;

		for (int i = 0; i < m.length; i++) {

			for (int j = i + 1; j < m.length; j++) {

				if (m[i].length != m[j].length) {

					iregularMatrixFlag = true;
				}
			}
		}

		return iregularMatrixFlag;
	}

	void doTowers(int topN, char from, char inter, char to) {

		if (topN == 1) {

			System.out.println("Discul 1 de la " + from + " la " + to);

		} else {

			doTowers(topN - 1, from, to, inter);
			System.out.println("Discul " + topN + " de la " + from + " la " + to);
			doTowers(topN - 1, inter, from, to);
		}
	}

	int findIndexOf(int[] array, int n) {

		int index = -1;

		for (int i = 0; i < array.length; i++) {

			if (array[i] == n) {
				index = i;
			}
		}

		return index;
	}

	int[] binaryAddition(int[] firstBinaryString, int[] secondBianryString) {

		int carry = 0;
		int[] result = new int[firstBinaryString.length + 1];

		for (int i = result.length - 1; i >= 0; i--) {

			if (i == 0) {

				result[i] = carry;

			} else {

				result[i] = firstBinaryString[i - 1] ^ secondBianryString[i - 1] ^ carry;
				carry = (firstBinaryString[i - 1] & carry) | (secondBianryString[i - 1] & carry)
						| (firstBinaryString[i - 1] & secondBianryString[i - 1]);

			}

		}

		return result;
	}

	int returnMaxLenght(int[] firstBinaryString, int[] secondBinaryString) {

		if (firstBinaryString.length > secondBinaryString.length) {
			return firstBinaryString.length;
		} else
			return secondBinaryString.length;
	}

	void printArray(int[] array) {

		for (int i = 0; i < array.length; i++) {

			System.out.print(array[i] + " ");
		}
	}

	void printMatrix(int[][] array) {

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {

				System.out.print(array[i][j] + " ");
				if (j == array[i].length - 1) {
					System.out.println();
				}
			}
		}
	}

	int cmmdc(int m, int n) {
		
		int temp;

		while (n != 0) {
			
			temp = n;
			n = m % n;
			m = temp;
		}
		
		return m;
	}

	int aLaRusse(int a , int b){

	    int[] x, y;
	    int i = 1 , prod = 0;
	    x = new int[1000];
	    y = new int[1000];
	    x[1] = a;
	    y[1] = b;

	    while(x[i] > 1){
	        x[i+  1] = x[i] / 2;
	        y[i + 1] = y[i] + y[i];
	        i = i + 1;
	    }

	    while(i > 0){
	        if( (x[i] % 2) != 0) prod = prod + y[i];
	        i = i - 1;
	    }

	    return prod;
	}

	void computePolynomial(ArrayList<Double> coeficients , double x){
		
		ArrayList<Double> temp = new ArrayList<Double>();
		temp.add(coeficients.get(0));
		
		for (int i = 0 ; i < coeficients.size()- 1; i++){
			
			
			temp.add(temp.get(i) * x + coeficients.get(i+1));
			
		}
		
		int lastElementReferrence = temp.size() - 1;
		System.out.println("Valoarea polinomului in punctul x = " + 
		x + " este = " + temp.get(lastElementReferrence));
	}
	
	public static void main(String[] args) {

		L1 app = new L1();

		int[] a1 = { 2, 3, 1, 5, 4 };
		int[] sa1 = app.selectionSort(a1);
		System.out.print("Arrayul sortat cu selection sort este : ");
		app.printArray(sa1);

		System.out.println("\n");

		int[] a2 = { 4, 3, 2, 1, 4, 5, 6, 0 };
		int[] sa2 = app.insertionSort(a2);
		System.out.print("Arrayul sortat cu insertion sort este : ");
		app.printArray(sa2);

		System.out.println("\n");

		int n = 30;
		System.out.println("Fibonacci de " + n + " este " + app.fibonacci(n));

		System.out.println();

		System.out.println("Rezultatul inmultirii celor doua matrici este : ");
		int[][] m1 = { { 1, 2 }, { 3, 4 }, { 5, 2 }, { 23, 2 }, { 4, 23 } };
		int[][] m2 = { { 5, 2, 3, 4 }, { 2, 1, 5, 23, } };
		int[][] result = app.matrixMultiplication(m1, m2);
		app.printMatrix(result);

		System.out.println();

		int disks = 3;
		System.out.println("Turnurile din Hanoi pentru " + disks + " discuri : ");
		app.doTowers(disks, 'A', 'B', 'C');

		System.out.println();

		int value = 3;
		int[] array = { 4, 2, 3, 5, 6, 2, 1 };
		System.out.println("Indexul la care se afla " + value + " este : " + app.findIndexOf(array, value));

		System.out.println();

		int[] bin1 = { 1, 1, 1, 1, 1, 1 };
		int[] bin2 = { 1, 1, 1, 1, 1, 1 };
		int[] binaryAdditionResult = app.binaryAddition(bin1, bin2);
		System.out.print("rezultatul adunarii in binar este : ");
		app.printArray(binaryAdditionResult);
		
		System.out.println("\n");
		
		int n1 = 520;
		int n2 = 20;
		System.out.print("Cel mai mic divizor comun dintre " + n1 + " si " + n2 + " este : ");
		System.out.println(app.cmmdc(n1, n2) + "\n");
		
		System.out.print("Rezultatul inmultirii a la russe a lui " + n1 + " cu " + n2 + 
				" este : " + app.aLaRusse(n1, n2) + "\n\n");
		
		ArrayList<Double> polynomialCoeficients = new ArrayList<Double>();
		polynomialCoeficients.add(5.2);
		polynomialCoeficients.add(5.7);
		polynomialCoeficients.add(3.4);
		polynomialCoeficients.add(2.2);
		polynomialCoeficients.add(7.2);
		polynomialCoeficients.add(9.7);
		polynomialCoeficients.add(3.2);
		
		app.computePolynomial(polynomialCoeficients, 2.3);
		
		

	}

}
