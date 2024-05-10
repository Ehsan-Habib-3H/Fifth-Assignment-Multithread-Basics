package sbu.cs;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplication {

    public static List<List<Integer>> tempMatrixProduct = new ArrayList<List<Integer>>();

    // You are allowed to change all code in the BlockMultiplier class
    public static class BlockMultiplier implements Runnable {
        int a1, a2, b1, b2;
        List<List<Integer>> matrix_A, matrix_B;

        public BlockMultiplier(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B, int a1, int a2, int b1, int b2) {
            this.a1 = a1;
            this.a2 = a2;
            this.b1 = b1;
            this.b2 = b2;
            this.matrix_A = matrix_A;
            this.matrix_B = matrix_B;
        }

        @Override
        public void run() {
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
            for (int i = a1; i <= a2; i++) {
                for (int j = b1; j <= b2; j++) {
                    int sum = 0;
                    for (int k = 0; k < matrix_B.size(); k++) {
                        sum += matrix_A.get(i).get(k) * matrix_B.get(k).get(j);
                    }
                    tempMatrixProduct.get(i).set(sum, j);
                }
            }
        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B) {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        BlockMultiplier q1 = new BlockMultiplier(matrix_A, matrix_B, 0, matrix_A.size() / 2 - 1, 0, matrix_B.get(0).size() / 2 - 1);
        BlockMultiplier q2 = new BlockMultiplier(matrix_A, matrix_B, 0, matrix_A.size() / 2 - 1, matrix_B.get(0).size() / 2 - 1, matrix_B.get(0).size() - 1);
        BlockMultiplier q3 = new BlockMultiplier(matrix_A, matrix_B, matrix_A.size() / 2, matrix_A.size() - 1, 0, matrix_B.get(0).size() - 1);
        BlockMultiplier q4 = new BlockMultiplier(matrix_A, matrix_B, matrix_A.size() / 2, matrix_A.size() - 1, matrix_B.get(0).size() / 2, matrix_B.get(0).size());
        Thread Tq1 = new Thread(q1);
        Thread Tq2 = new Thread(q2);
        Thread Tq3 = new Thread(q3);
        Thread Tq4 = new Thread(q4);
        Tq1.start();
        Tq2.start();
        Tq3.start();
        Tq4.start();
        try {
            Tq1.join();
            Tq2.join();
            Tq3.join();
            Tq4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return tempMatrixProduct;
    }

    public static void main(String[] args) {
        // Test your code here
//        for(int i = 0;i<tempMatrixProduct.size();i++)
//        {
//            for(int j = 0; j<tempMatrixProduct.get(0).size();j++)
//            {
//                System.out.print(tempMatrixProduct.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
    }
}
