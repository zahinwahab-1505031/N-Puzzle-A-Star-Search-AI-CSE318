/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch_n_puzzle;

/**
 *
 * @author Zahin
 */
public class Position {
    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public String toString(){
        return "Row: " + row + " Column: "+ col;
    }
}
