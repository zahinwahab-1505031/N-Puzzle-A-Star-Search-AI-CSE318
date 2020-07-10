/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch_n_puzzle;

import java.util.Arrays;

/**
 *
 * @author Zahin
 */
public class Matrix {
    public int [][]matrix;

   public Matrix( int[][] m){
        matrix = new int[Main.N][Main.N];

 for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
               this.matrix[i][j] = m[i][j];
                 
                
            }
        }
    }
     @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Arrays.deepHashCode(this.matrix);
        hash = 53 * hash + Main.N;
        return hash;
    }
      @Override
    public boolean equals(Object o){
        if(o==null) return false;
        else if(!(o instanceof Matrix)) return false;
        Matrix st = (Matrix) o;
        for(int i = 0; i<Main.N; i++){
            for(int j = 0; j<Main.N; j++){
                if(this.matrix[i][j] != st.matrix[i][j]){
                    return false;
                }
            }
            
        }
        return true;
    }
}
