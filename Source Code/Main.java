/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch_n_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Zahin
 */
public class Main {

    public static int N;
    public static int TYPE;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException, FileNotFoundException {
        // TODO code application logic here
        Scanner scanner = new Scanner(new File("input.txt"));
int [] arr = new int [100];
int p = 0;
while(scanner.hasNextInt()){
   arr[p++] = scanner.nextInt();
}
      
        int a= arr[0];
        N = (int)Math.sqrt(a+1);
        int[][] matrix = new int[Main.N][Main.N];
         System.out.println("Enter the matrix(represent blank space by 0:");
         int k=1;
         for(int i=0;i<Main.N;i++){
             for(int j=0;j<Main.N;j++) {
                 matrix[i][j] = arr[k++];
             }
         }
       
       
            System.out.println("Enter 1 for Hamming Distance,2 for Manhattan Distance and 3 for Linear Conflicts");
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
            TYPE = t;
        if(t==1) System.out.println("==================Using Hamming Distance==================");
       else if(t==2) System.out.println("==================Using Manhattan Distance==================");
       else if(t==3) System.out.println("==================Using Linear Conflicts==================");
        
        
         HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
         int inversions = 0;
         int blankRow = 0;
         for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                if(matrix[i][j]==0) blankRow = i;
                hmap.put(matrix[i][j], i*Main.N+j);
              
            }
        }
         for(int i=1;i<Main.N*Main.N;i++) {
           for(int j=i+1;j<Main.N*Main.N;j++) {
         int pos1 =  hmap.get(i);
          int pos2 =  hmap.get(j);
          if(pos1>pos2) inversions++;
         }
         }
         
         if(Main.N%2==1 && inversions%2==1) {
             System.out.println("Inversions: "+inversions);
             System.out.println("Given matrix is not solvable");
             return;
         }
         else if(Main.N%2==0&& (blankRow+inversions)%2==0) {
             System.out.println("Sum: "+blankRow+inversions);
             System.out.println("Given matrix is not solvable");
             return;
         }
        int[][] goalmatrix = new int[Main.N][Main.N];
        int num = 1;
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                if (num == (Main.N*Main.N)) {
                    num = 0;
                    
                }
                goalmatrix[i][j] = num;
                num++;
            }
        }
        Node initialNode = new Node(matrix, null);
        Node goalNode = new Node(goalmatrix, null);
        Graph graph = new Graph(initialNode, goalNode);
        long startTime = System.nanoTime();
        Node n = graph.BestFirstSearch();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        
        Stack<String> res = new Stack<>();
        int stepCount = 0;
        while (n != null) {
            res.push(n.toString());
            if(n.move!=null) {
                res.push(n.move);
                 stepCount++;
            }
            n = n.parent;

        }
        while (!res.isEmpty()) {
            System.out.println(res.pop());
        }
            if(t==1) System.out.println("==================Stats Using Hamming Distance==================");
       else if(t==2) System.out.println("==================Stats Using Manhattan Distance==================");
       else if(t==3) System.out.println("==================Stats Using Linear Conflicts==================");
     
        System.out.println("Time taken: "+totalTime/1000+"us");
        System.out.println("#Explored Nodes: "+graph.BFS_exploredSize);
        System.out.println("#Expanded Nodes: "+graph.BFS_expandedSize);
        System.out.println("Steps: "+stepCount);
    }
    }


