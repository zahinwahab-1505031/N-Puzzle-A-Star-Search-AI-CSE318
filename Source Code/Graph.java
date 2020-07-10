/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch_n_puzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Zahin
 */
public class Graph {

    Node startNode;
    Node goalNode;
    //   int k;
    int BFS_expandedSize;
    int BFS_exploredSize;

    public Graph(Node startNode, Node goalNode) {
        this.startNode = startNode;
        this.goalNode = goalNode;

    }

    public Node BestFirstSearch() throws CloneNotSupportedException {

        if (this.startNode.equals(goalNode)) {
            return startNode;
        }
        HashMap<Matrix, Integer> hmap = new HashMap<Matrix, Integer>();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(5, new NodeComparator());
        ArrayList<Node> expanded = new ArrayList<>();
        this.startNode.g_cost = 0;
        hmap.put(new Matrix(startNode.matrix), 0);
        this.startNode.f_cost = this.startNode.g_cost + CalculateHeuristic.getHeuristicCost(startNode, 1);
        queue.add(this.startNode);
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            expanded.add(current);
            if (current.equals(this.goalNode)) {
                BFS_expandedSize = expanded.size();
                BFS_exploredSize = queue.size() + expanded.size();
                return current;
            } else {
                List<Node> children = current.get();
                if (!children.isEmpty()) {

                    for (Node n : children) {

                        if (expanded.contains(n)) {
                            continue;
                        }

                        int newCost = current.g_cost + 1;

                        if (!queue.contains(n)) {
                            n.parent = current;
                            n.g_cost = newCost;
                            n.f_cost = n.g_cost + CalculateHeuristic.getHeuristicCost(n, Main.TYPE);
                            
                              queue.add(n);
                              hmap.put(new Matrix(n.matrix), n.g_cost);
                             

 
                         

                        } else if(queue.contains(n)) {
                            
                            int prevCost = hmap.get(new Matrix(n.matrix));
                           
                            if (newCost < prevCost) {
                                queue.remove(n);
                                hmap.remove(n.matrix);
                                n.parent = current;
                                n.g_cost = newCost;
                                n.f_cost = n.g_cost + CalculateHeuristic.getHeuristicCost(n, Main.TYPE);
                                 hmap.put(new Matrix(n.matrix), n.g_cost);
                                queue.add(n);

                            
                              }

                        }
                    }

                }
            }

        }
        BFS_expandedSize = expanded.size();
        BFS_exploredSize = queue.size() + expanded.size();
        return null;
    }
}
