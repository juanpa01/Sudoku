/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

/**
 *
 * @author ADMIN
 */
public class Solver {
    
    int[][] zeros = {
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
 
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
             
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0}
    };
    
    public Solver(SudokuModel model, int index){
        
        switch(index){
            case 1: Backtracking solver = new Backtracking(model); 
                    solver.solve(0, 0); 
                    break;
            case 2: Restriction solver2 = new Restriction(model); 
                    solver2.solve(); 
                    int aux[][] = zeros;
                    aux = solver2.getMatriz();
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            model.cells[i][j].setValue(aux[i][j]);
                        }
                    }
                    break;
           
        }
    }
    
    
    
}

    

