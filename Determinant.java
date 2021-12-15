package determinants;

import java.util.Scanner;

public class Determinant {

	static int[][] matrixA;
	int rowIndex;
	public Determinant() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter nxn dimension of matrix");
		int numRows= scan.nextInt();
		
		matrixA= new int[numRows][numRows];
		
		System.out.println("Please enter numbers for the matrix.");
		
		for (int i=0; i < numRows; i++) {
			for (int j= 0; j < numRows; j++) {
				matrixA[i][j]= scan.nextInt();
				System.out.print(matrixA[i][j] + " ");
			}
			System.out.println();
		}
		
		
		scan.close();
		
		System.out.println("This matrix has a determinant of " + determinant(matrixA));
		
	}
	
	//determine best row
	static int bestRow(int[][] matrix) {
		
		
		
		int count, currentBest= 0;
		int bestRow= 0;
		
		//find row or column with most 0's and 1's
		for (int i= 0; i < matrix.length; i++) {
			count= 0;
			for (int j=0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0 || matrix[i][j] == 1) {
					count++;
				}
				if (count > currentBest) {
					currentBest= count;
					bestRow= i;
				}
			}
		}
		
		return bestRow;
		
	}
	
	
	
	static int determinant(int[][] matrix) {
		int result= 0;
		int row= bestRow(matrix);
		
		//base case
		if (matrix.length == 2) {
			return (matrix[0][0] * matrix [1][1]) 
					- (matrix[0][1] * matrix[1][0]);
		}
		
		else {
			//go along row adding cofactor expansions to result
			for (int i= 0; i < matrix.length; i++) {
				result += laplaceExpansion(matrix, row, i);
			}
		}
		
		
		return result;
	}
	
	static int laplaceExpansion (int[][] matrix, int row, int column) {
		
		
		
		//build submatrix
		int[][] newMatrix= new int[matrix.length-1][matrix.length-1];
		
		for (int i= 0; i < matrix.length; i++) {
			for (int j= 0; j < matrix.length; j++) {
				
				if (i < row) {
					if (j < column)
						newMatrix[i][j]= matrix[i][j];
					
					else if (j > column)
						newMatrix[i][j-1]= matrix[i][j];
					
					//if j == column it is skipped
				}
				
				else if (i > row) {
					
					if (j < column)
						newMatrix[i-1][j]= matrix[i][j];
					
					else if (j > column)
						newMatrix[i-1][j-1]= matrix[i][j];
					
					//if j == column it is skipped
				}
				
				//if i == row it is skipped			
			}
		}
		//meat of cofactor expansion
		return (int)Math.pow(-1, row+column) * matrix[row][column] 
				* determinant(newMatrix);
		
	}

}
