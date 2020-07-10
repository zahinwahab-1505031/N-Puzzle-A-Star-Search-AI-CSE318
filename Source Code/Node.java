/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch_n_puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zahin
 */
public class Node {

    public int[][] matrix;
    public int g_cost;
    public int f_cost;
    public Node parent;
    //left,top,right,up
    int[] moveAlongX = {-1, 0, 1, 0};
    int[] moveAlongY = {0, -1, 0, 1};
    public List<Node> childrenNode = new ArrayList<Node>();
    Node[] arr = new Node[4];
    // Position position_of_blank;
    String move;
    public Node(int[][] matrix, Node parent) {
        this.matrix = matrix;
        this.parent = parent;

    }

    public void getChildren() throws CloneNotSupportedException {
        Position pos = null;

        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                //  newMatrix[i][j] = matrix[i][j];
                if (matrix[i][j] == 0) {
                    pos = new Position(i, j);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            int[][] newMatrix = new int[Main.N][Main.N];
            for (int k = 0; k < Main.N; k++) {
                for (int j = 0; j < Main.N; j++) {
                    newMatrix[k][j] = matrix[k][j];

                }
            }
            if (isValid(pos.row + moveAlongX[i], pos.col + moveAlongY[i])) {
                 String str = "<<Swapping " + newMatrix[pos.row + moveAlongX[i]][pos.col + moveAlongY[i]]+" with the blank space>>\n";
              
                int t = newMatrix[pos.row][pos.col];
                newMatrix[pos.row][pos.col] = newMatrix[pos.row + moveAlongX[i]][pos.col + moveAlongY[i]];
                newMatrix[pos.row + moveAlongX[i]][pos.col + moveAlongY[i]] = t;
                Node node = new Node(newMatrix, this);
                node.move = str;
                //s node.g_cost = -9999;
                //  System.out.println("we added:->"+ node);
                childrenNode.add(node);

            }
        }

    }

    public List get() throws CloneNotSupportedException {

        getChildren();
        return childrenNode;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < Main.N && y < Main.N;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        Node s = (Node) obj;
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                if (matrix[i][j] != s.matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                str += (Integer.toString(matrix[i][j]) + " ");
            }
            str += "\n";
        }
        return str + "-----------------------------------------\n";
    }

}
