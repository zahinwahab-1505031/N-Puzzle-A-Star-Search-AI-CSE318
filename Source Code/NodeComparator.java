/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch_n_puzzle;

import java.util.Comparator;

class NodeComparator implements Comparator<Node>{ 
              
            // Overriding compare()method of Comparator  
                        // for descending order of cgpa 
            @Override
            public int compare(Node n1, Node n2) { 
                if (n1.f_cost>n2.f_cost) return 1;
                else if (n1.f_cost < n2.f_cost)return -1;
                else return 0;
            }
        } 