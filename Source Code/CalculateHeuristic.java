/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch_n_puzzle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Zahin
 */
public class CalculateHeuristic {

    public static HashMap makeHash() {
        HashMap<Integer, Position> hmap = new HashMap<Integer, Position>();
        int num = 1;
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                Position position = new Position(i, j);
                if (num == (Main.N*Main.N)) {
                    num = 0;
                }
                hmap.put(num, position);
                num++;
            }
        }

        /*     Set set = hmap.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
         System.out.println(mentry.getValue());
      }*/
        return hmap;
    }

    public static int HammingDistance(Node initial) {
        int [][]mat= new int[Main.N][Main.N];
        Node goal = new Node(mat,null);
         int num = 1;
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                if (num == (Main.N*Main.N)) {
                    num = 0;
                    
                }
                goal.matrix[i][j] = num;
                num++;
            }
        }
        int cost = 0;
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                
                if (initial.matrix[i][j] != 0 && initial.matrix[i][j] != goal.matrix[i][j]) {
                    cost++;
                }
            }

        }
        return cost;
    }

    public static int ManhattanDistance(Node initial) {
        HashMap<Integer, Position> hmap = new HashMap<Integer, Position>();
        hmap = makeHash();
        int cost = 0;
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                if (initial.matrix[i][j] != 0) {

                    Position pos = hmap.get(initial.matrix[i][j]);
                    cost += (Math.abs(pos.row - i) + Math.abs(pos.col - j));
                }
            }

        }
        return cost;
    }

    public static int LinearConflict(Node initial) {
        //iterating each row
        int linearConflict = 0;
        HashMap<Integer, Position> hmap = new HashMap<Integer, Position>();
        hmap = makeHash();
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                if (initial.matrix[i][j] != 0) {
                    for (int k = j + 1; k < Main.N; k++) {
                        Position pos1 = hmap.get(initial.matrix[i][j]);
                        Position pos2 = hmap.get(initial.matrix[i][k]);
                        if (pos1.row == i && pos2.row == i && pos1.col > pos2.col) {
                            linearConflict++;
                        }
                    }
                }
            }
        }

        for (int j = 0; j < Main.N; j++) {
            for (int i = 0; i < Main.N; i++) {
                if (initial.matrix[i][j] != 0) {
                    for (int k = i + 1; k < Main.N; k++) {
                        Position pos1 = hmap.get(initial.matrix[i][j]);
                        Position pos2 = hmap.get(initial.matrix[k][j]);
                        if (pos1.col == j && pos2.col == j && pos1.row > pos2.row) {
                            linearConflict++;
                        }
                    }
                }
            }
        }
        return ManhattanDistance(initial)+2*linearConflict;
    }
    public static int getHeuristicCost(Node n,int type){
        if(type==1) return HammingDistance(n);
        else if(type==2) return ManhattanDistance(n);
        else return LinearConflict(n);
    }
}
